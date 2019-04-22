package com.proflow.mapper;

import com.proflow.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> getUsersByRoleCode(String roleCode);

}
