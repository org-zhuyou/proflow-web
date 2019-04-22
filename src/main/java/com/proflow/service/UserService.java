package com.proflow.service;

import com.proflow.entity.User;
import com.baomidou.mybatisplus.service.IService;
import com.proflow.entity.vo.UserVO;
import com.proflow.web.form.UserForm;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface UserService extends IService<User> {
    // 登录
    UserVO login(String username, String password) throws Exception;

    UserVO getUserVOById(Long userId) throws Exception;

    User findUserByUsername(String username) throws Exception;

    User save(UserForm userForm) throws Exception;
    
    List<User> getUsersByRoleCode(String roleCode) throws Exception;

    boolean delete(Long id) throws Exception;

}
