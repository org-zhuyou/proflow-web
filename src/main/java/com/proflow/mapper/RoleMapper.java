package com.proflow.mapper;

import com.proflow.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findRolesByUserId(Long userId);

}
