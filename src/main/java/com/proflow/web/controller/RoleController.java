package com.proflow.web.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.proflow.annotation.NoAuth;
import com.proflow.entity.Role;
import com.proflow.service.RoleService;
import com.proflow.web.form.ResultForm;
import com.proflow.web.form.RoleForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * 角色相关
 * Created by Leonid on 2018/8/8.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;


    /**
     * 通过用户id 获取角色信息
     * @param userId
     * @return
     */
    @NoAuth
    @PostMapping("/getRolesByUserId")
    public Object getRolesByUserId(Long userId) {
        ResultForm<?> resultForm = null;

        try {
            List<Role> roles = roleService.findRolesByUserId(userId);
            resultForm = ResultForm.createSuccess("查询成功", roles);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth
    @PostMapping("/test")
    public Object test() {
        Role role = roleService.selectById(1);
        RoleForm roleForm = new RoleForm();
        BeanUtil.copyProperties(role, roleForm);
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2l);
        roleForm.setMenus(ids);
        System.out.println(JSON.toJSONString(roleForm));
        return JSON.toJSONString(roleForm);
    }

    @NoAuth
    @PostMapping("/save")
    public Object save(@RequestBody RoleForm roleForm) {
        ResultForm<?> resultForm = null;
        try {
            Role role = roleService.save(roleForm);
            resultForm = ResultForm.createSuccess("保存成功", role);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth
    @PostMapping("/findById")
    public Object findById(Long id) {
        ResultForm<?> resultForm = null;
        try {
            Role role = roleService.selectById(id);
            resultForm = ResultForm.createSuccess("查询成功",role);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth
    @PostMapping("/findAll")
    public Object findAll() {
        ResultForm<?> resultForm = null;
        try {
            List<Role> roleList = roleService.selectList(new EntityWrapper<>());
            resultForm = ResultForm.createSuccess("查询成功",roleList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth
    @PostMapping("/delete")
    public Object delete(Long id) {
        ResultForm<?> resultForm = null;
        try {
            roleService.remove(id);
            resultForm = ResultForm.createSuccess("删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

}
