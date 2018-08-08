package com.proflow.service;

import com.proflow.entity.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface RoleService extends IService<Role> {

    List<Role> findRolesByUserId(Long userId) throws Exception;

}
