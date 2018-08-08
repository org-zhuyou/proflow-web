package com.proflow.web.controller;

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


/**
 * 角色相关
 * Created by Leonid on 2018/8/8.
 */
@RestController
@RequestMapping("role")
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

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

    @PostMapping("/findById")
    public Object findById(Long id) {
        ResultForm<?> resultForm = null;

        return resultForm;
    }

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
