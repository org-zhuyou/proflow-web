package com.proflow.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.proflow.entity.Role;
import com.proflow.mapper.RoleMapper;
import com.proflow.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Role> findRolesByUserId(Long userId) throws Exception {
        if(ObjectUtil.isNull(userId)) {
            throw new IllegalArgumentException();
        }
        return this.baseMapper.findRolesByUserId(userId);
    }


}
