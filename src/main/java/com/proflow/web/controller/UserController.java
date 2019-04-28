package com.proflow.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.proflow.annotation.NoAuth;
import com.proflow.entity.Menu;
import com.proflow.entity.Role;
import com.proflow.entity.User;
import com.proflow.entity.vo.UserVO;
import com.proflow.service.MenuService;
import com.proflow.service.RoleService;
import com.proflow.service.UserService;
import com.proflow.web.form.PageForm;
import com.proflow.web.form.ResultForm;
import com.proflow.web.form.UserForm;
import com.proflow.web.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户相关
 * Created by Leonid on 2018/8/8.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @PostMapping("/test")
    public Object test() {
        User user = userService.selectById(1);
        UserForm userForm = new UserForm();
        BeanUtil.copyProperties(user, userForm);
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2l);
        userForm.setRoles(ids);
        System.out.println(JSON.toJSONString(userForm));
        return JSON.toJSONString(userForm);
    }

    @NoAuth(auth = {"ADMIN","BOSS"})
    @PostMapping("/getUserByRole")
    public Object getUserByRole(String roleCode) {

        ResultForm<?> resultForm = null;
        try {
            List<User> users = userService.getUsersByRoleCode(roleCode);
            resultForm = ResultForm.createSuccess("查询成功", users);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }

        return resultForm;
    }

    @NoAuth(auth = {"ADMIN","BOSS"})
    @PostMapping("/getMenusByUserId")
    public Object getMenusByUserId(Long userId) {
        ResultForm<?> resultForm = null;
        try {
            List<Menu> menus = menuService.findMenusByUserId(userId);
            resultForm = ResultForm.createSuccess("查询成功", menus.stream().map(e -> e.getId()).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }

        return resultForm;
    }

    @NoAuth(auth = {"ADMIN","BOSS"})
    @PostMapping("/save")
    public Object save(@RequestBody UserForm userForm, HttpServletRequest request) {
        ResultForm<?> resultForm = null;
        try {
            UserVO currentUser = SessionUtil.getCurrentUser(request);
            User user = userService.save(userForm);
            resultForm = ResultForm.createSuccess("保存成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth(auth = {"ADMIN","BOSS"})
    @PostMapping("/delete")
    public Object delete(Long id) {
        ResultForm<?> resultForm = null;
        try {
            if (userService.deleteById(id)) {
                resultForm = ResultForm.createSuccess("删除成功", null);
            } else {
                resultForm = ResultForm.createError("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth(auth = {"ADMIN","BOSS"})
    @PostMapping("/findById")
    public Object findById(Long id) {
        ResultForm<?> resultForm = null;
        try {
            User user = userService.selectById(id);
            if (null == user) {
                throw new Exception("用户不存在");
            }
            List<Role> roles = roleService.findRolesByUserId(user.getId());
            Map<String, Object> params = new HashMap<>();
            params.put("user", user);
            params.put("roles", roles);
            resultForm = ResultForm.createSuccess("查询成功", params);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth(auth = {"ADMIN","BOSS"})
    @PostMapping("/page")
    public Object page(User user, PageForm<User> pageForm) {
        ResultForm<?> resultForm = null;
        try {
            EntityWrapper<User> wrapper = new EntityWrapper<>();
            if (StrUtil.isBlank(user.getName())) {
                user.setName(null);
            }
            if (StrUtil.isBlank(user.getMobile())) {
                user.setMobile(null);
            }
            if (StrUtil.isBlank(user.getPosition())) {
                user.setPosition(null);
            }
            if (StrUtil.isBlank(user.getDept())) {
                user.setDept(null);
            }
            wrapper.setEntity(user);
            Page<User> page = userService.selectPage(pageForm.createPage(), wrapper);
            resultForm = ResultForm.createSuccess("查询成功", page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }



}
