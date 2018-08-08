package com.proflow.service;

import com.proflow.entity.User;
import com.baomidou.mybatisplus.service.IService;
import com.proflow.entity.vo.UserVO;

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

    User findUserByUsername(String username) throws Exception;

    User save(User user) throws Exception;

    boolean delete(Long id) throws Exception;

}
