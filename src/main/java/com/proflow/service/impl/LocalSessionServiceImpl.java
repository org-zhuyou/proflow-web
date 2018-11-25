package com.proflow.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.proflow.entity.LocalSession;
import com.proflow.entity.User;
import com.proflow.entity.vo.UserVO;
import com.proflow.mapper.LocalSessionMapper;
import com.proflow.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Leonid on 2018/7/3.
 */
@Service
public class LocalSessionServiceImpl extends ServiceImpl<LocalSessionMapper, LocalSession> implements LocalSessionService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserVO login(String username, String password, long timeout) throws Exception {
        // 认证成功后
        UserVO userVO = userService.login(username, password);
        LocalSession session = LocalSession.create(timeout);
        session.setContextJson("{}");
        session.setUserId(userVO.getId());
        if (!this.insert(session)) {
            throw new Exception("系统繁忙请稍后再试");
        }
        userVO.setPassword(null);
        userVO.setLocalSession(session);
        return userVO;
    }


    @Override
    public boolean checkSessionState(String token) throws Exception {
        if (StrUtil.isBlank(token))
            return false;
        Date now = new Date();
        LocalSession localSession = localSessionByToken(token);
        if (localSession != null && localSession.getStatus().equals(LocalSession.VALID)) {
            if (localSession.getExpiryTime().after(now)) {
                return true;
            }
            this.deleteById(localSession.getId());
        }
        return false;
    }

    @Override
    public LocalSession getLocalSessionByToken(String token) throws Exception {
        if (StrUtil.isBlank(token)) {
            return null;
        }
        LocalSession localSession = localSessionByToken(token);
        Date now = new Date();
        if (localSession != null && localSession.getStatus().equals(LocalSession.VALID)) {
            if (localSession.getExpiryTime().after(now)) {
                return localSession;
            }
            this.deleteById(localSession.getId());
        }
        return null;
    }

    @Override
    public void logout(String token) throws Exception {
        if (StrUtil.isBlank(token)) {
            return;
        }
        this.delete(Condition.create().eq("token", token));
    }

    private LocalSession localSessionByToken(String token) throws Exception {
        LocalSession localSession = new LocalSession();
        localSession.setToken(token);
        EntityWrapper<LocalSession> wrapper = new EntityWrapper<>();
        wrapper.setEntity(localSession);
        return this.selectOne(wrapper);
    }

}
