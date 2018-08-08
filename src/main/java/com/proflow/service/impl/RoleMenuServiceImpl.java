package com.proflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.proflow.entity.RoleMenu;
import com.proflow.mapper.RoleMenuMapper;
import com.proflow.service.RoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public boolean removeRoleMenuRelation(Long roleId) throws Exception {
        if (null == roleId) throw new IllegalArgumentException();

        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);

        EntityWrapper<RoleMenu> wrapper = new EntityWrapper<>();
        wrapper.setEntity(roleMenu);

        return this.delete(wrapper);
    }

}
