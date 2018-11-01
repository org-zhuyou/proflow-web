package com.proflow.web.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.proflow.annotation.NoAuth;
import com.proflow.entity.Menu;
import com.proflow.entity.Role;
import com.proflow.entity.vo.MenuVO;
import com.proflow.service.MenuService;
import com.proflow.web.form.ResultForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Leonid on 2018/10/31.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @NoAuth
    @PostMapping("/findMenusByRoleId")
    public Object findMenusByRoleId(Long roleId) {
        ResultForm<?> resultForm = null;
        try {
            List<Menu> menuList = menuService.findMenusByRoleId(roleId);
            resultForm = ResultForm.createSuccess("查询成功",menuList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth
    @PostMapping("/getAllMenus")
    public Object getAllMenus() {
        ResultForm<?> resultForm = null;
        try {
            List<Role> roleList = menuService.selectList(Condition.EMPTY);
            resultForm = ResultForm.createSuccess("查询成功",roleList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth
    @PostMapping("/treeMenus")
    public Object treeMenus() {
        ResultForm<?> resultForm = null;
        try {
            List<MenuVO> menuVOS = menuService.treeMenus();
            resultForm = ResultForm.createSuccess("查询成功",menuVOS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }
    @NoAuth
    @PostMapping("/treeMenusByUserId")
    public Object treeMenusByUserId(Long userId) {
        ResultForm<?> resultForm = null;
        try {
            List<MenuVO> menuVOS = menuService.treeMenusByUserId(userId);
            resultForm = ResultForm.createSuccess("查询成功",menuVOS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultForm = ResultForm.createError(e.getMessage());
        }
        return resultForm;
    }

}
