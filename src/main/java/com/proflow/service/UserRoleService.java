package com.proflow.service;

import com.proflow.entity.UserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface UserRoleService extends IService<UserRole> {

    boolean removeUserRoleByRoleId(Long roleId) throws Exception;

    boolean removeUserRoleByUserId(Long userId) throws Exception;

}
