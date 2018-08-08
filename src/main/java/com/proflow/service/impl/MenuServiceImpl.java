package com.proflow.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.proflow.entity.Menu;
import com.proflow.entity.RoleMenu;
import com.proflow.mapper.MenuMapper;
import com.proflow.service.MenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.proflow.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Override
    public List<Menu> findMenusByUserId(Long userId) throws Exception {
        if (ObjectUtil.isNull(userId)) {
            throw new IllegalArgumentException();
        }

        return null;
    }



}
