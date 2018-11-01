package com.proflow.mapper;

import com.proflow.entity.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findMenusByUserId(Long userId);

    List<Menu> findMenusByRoleId(Long roleId);

}
