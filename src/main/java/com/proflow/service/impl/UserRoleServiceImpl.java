package com.proflow.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.proflow.entity.UserRole;
import com.proflow.mapper.UserRoleMapper;
import com.proflow.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public boolean removeUserRoleByRoleId(Long roleId) throws Exception {
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        EntityWrapper<UserRole> wrapper = new EntityWrapper<>();
        wrapper.setEntity(userRole);
        return this.delete(wrapper);
    }

    @Override
    public boolean removeUserRoleByUserId(Long userId) throws Exception {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        EntityWrapper<UserRole> wrapper = new EntityWrapper<>();
        wrapper.setEntity(userRole);
        return this.delete(wrapper);
    }
}
