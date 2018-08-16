package com.proflow.service;

import com.baomidou.mybatisplus.service.IService;
import com.proflow.entity.LocalSession;
import com.proflow.entity.vo.UserVO;

/**
 * Created by Leonid on 2018/7/3.
 */
public interface LocalSessionService extends IService<LocalSession> {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @param timeout
     * @return
     * @throws Exception
     */
    UserVO login(String username, String password, long timeout) throws Exception;

    /**
     * 判断当前会话状态
     * @param token
     * @return
     * @throws Exception
     */
    boolean checkSessionState(String token) throws Exception;

    /**
     * 通过token获取当前会话信息
     * @param token
     * @return
     * @throws Exception
     */
    LocalSession getLocalSessionByToken(String token) throws Exception;

}
