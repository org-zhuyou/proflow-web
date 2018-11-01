package com.proflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.proflow.entity.Menu;
import com.proflow.entity.RoleMenu;
import com.proflow.entity.vo.MenuVO;
import com.proflow.mapper.MenuMapper;
import com.proflow.service.MenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.proflow.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Override
    public List<Menu> findMenusByUserId(Long userId) throws Exception {
        if (ObjectUtil.isNull(userId)) {
            throw new IllegalArgumentException();
        }

        return this.baseMapper.findMenusByUserId(userId);
    }

    @Override
    public List<Menu> findMenusByRoleId(Long roleId) throws Exception {
        if (ObjectUtil.isNull(roleId)) {
            throw new IllegalArgumentException();
        }

        return this.baseMapper.findMenusByRoleId(roleId);
    }



    @Override
    public List<MenuVO> treeMenus() throws Exception {
        List<Menu> menus = this.selectList(Condition.EMPTY);
        List<MenuVO> menuVOS = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getPid() == null || menu.getPid().equals(0L)) {
                // 父节点
                MenuVO menuVO = new MenuVO();
                BeanUtil.copyProperties(menu, menuVO);
                for (Menu menu2 : menus) {
                    if (menu2.getPid().equals(menu.getId())) {
                        menuVO.getChildren().add(menu2);
                    }
                }
                menuVOS.add(menuVO);
            }

        }
        return menuVOS;
    }

    @Override
    public List<MenuVO> treeMenusByUserId(Long userId) throws Exception {
        List<Menu> menus = findMenusByUserId(userId);
        List<MenuVO> menuVOS = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getPid() == null || menu.getPid().equals(0L)) {
                // 父节点
                MenuVO menuVO = new MenuVO();
                BeanUtil.copyProperties(menu, menuVO);
                for (Menu menu2 : menus) {
                    if (menu2.getPid().equals(menu.getId())) {
                        menuVO.getChildren().add(menu2);
                    }
                }
                menuVOS.add(menuVO);
            }

        }
        return menuVOS;
    }


}
