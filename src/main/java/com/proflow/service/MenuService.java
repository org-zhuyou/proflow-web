package com.proflow.service;

import com.proflow.entity.Menu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author Leonid
 * @since 2018-08-06
 */
public interface MenuService extends IService<Menu> {

    List<Menu> findMenusByUserId(Long userId) throws Exception;



}
