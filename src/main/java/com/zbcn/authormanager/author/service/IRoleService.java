package com.zbcn.authormanager.author.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbcn.authormanager.author.entity.TAuthRole;
import com.zbcn.authormanager.common.entity.QueryRequest;

import java.util.List;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName IRoleService.java
 * @Description 角色控制service
 * @createTime 2019年08月03日 19:25:00
 */
public interface IRoleService extends IService<TAuthRole> {

    /**
     * 通过用户名查找用户集合
     * @param username 用户名
     * @return 角色集合
     */
    List<TAuthRole> findUserRole(String username);

    /**
     * 查找所有角色
     *
     * @param role 角色对象（用于传递查询条件）
     * @return 角色集合
     */
    List<TAuthRole> findRoles(TAuthRole role);

    /**
     * 查找所有角色（分页）
     *
     * @param role    角色对象（用于传递查询条件）
     * @param request request
     * @return IPage
     */
    IPage<TAuthRole> findRoles(TAuthRole role, QueryRequest request);

    /**
     * 通过角色名称查找相应角色
     *
     * @param roleName 角色名称
     * @return 角色
     */
    TAuthRole findByName(String roleName);

    /**
     * 新增角色
     *
     * @param role 待新增的角色
     */
    void createRole(TAuthRole role);

    /**
     * 修改角色
     *
     * @param role 待修改的角色
     */
    void updateRole(TAuthRole role);

    /**
     * 删除角色
     *
     * @param roleIds 待删除角色的 id
     */
    void deleteRoles(String roleIds);


}
