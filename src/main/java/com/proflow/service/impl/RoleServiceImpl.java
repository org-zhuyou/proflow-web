package com.proflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.proflow.entity.Role;
import com.proflow.entity.RoleMenu;
import com.proflow.mapper.RoleMapper;
import com.proflow.service.MenuService;
import com.proflow.service.RoleMenuService;
import com.proflow.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.proflow.service.UserRoleService;
import com.proflow.web.form.RoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<Role> findRolesByUserId(Long userId) throws Exception {
        if(ObjectUtil.isNull(userId)) {
            throw new IllegalArgumentException();
        }
        return this.baseMapper.findRolesByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role save(RoleForm roleForm) throws Exception {
        if (null == roleForm) {
            throw new IllegalArgumentException();
        }
        if (CollectionUtil.isEmpty(roleForm.getMenus())) {
            throw new Exception("权限菜单信息不能为空");
        }
        Role role = new Role();
        BeanUtil.copyProperties(roleForm, role);

        if (role.getId() == null) {
            role.setCreateTime(new Date());
        }
        role.setModifyTime(new Date());
        if (!this.insertOrUpdate(role)) {
            throw new Exception("保存角色信息失败");
        }

        // 删除菜单
        roleMenuService.removeRoleMenuRelation(role.getId());

        List<RoleMenu> list = new ArrayList<>();
        for (Long menuId : roleForm.getMenus()) {
            RoleMenu roleMenu = new RoleMenu(role.getId(), menuId);
            list.add(roleMenu);
        }

        if (!roleMenuService.insertBatch(list)) {
            throw new Exception("保存角色菜单关系失败");
        }

        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(Long id) throws Exception {
        if(ObjectUtil.isNull(id)) {
            throw new IllegalArgumentException();
        }
        userRoleService.removeUserRoleByRoleId(id);
        return this.deleteById(id);
    }


}
