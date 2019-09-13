package com.zbcn.authormanager.author.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbcn.authormanager.author.entity.TAuthRole;
import com.zbcn.authormanager.author.mapper.RoleMapper;
import com.zbcn.authormanager.author.service.IRoleService;
import com.zbcn.authormanager.common.entity.QueryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName RoleServiceImpl.java
 * @Description TODO
 * @createTime 2019年08月03日 19:30:00
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,TAuthRole> implements IRoleService {
    /**
     * 通过用户名查找用户集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    @Override
    public List<TAuthRole> findUserRole(String username) {
        return null;
    }

    /**
     * 查找所有角色
     *
     * @param role 角色对象（用于传递查询条件）
     * @return 角色集合
     */
    @Override
    public List<TAuthRole> findRoles(TAuthRole role) {
        return null;
    }

    /**
     * 查找所有角色（分页）
     *
     * @param role    角色对象（用于传递查询条件）
     * @param request request
     * @return IPage
     */
    @Override
    public IPage<TAuthRole> findRoles(TAuthRole role, QueryRequest request) {
        return null;
    }

    /**
     * 通过角色名称查找相应角色
     *
     * @param roleName 角色名称
     * @return 角色
     */
    @Override
    public TAuthRole findByName(String roleName) {
        return null;
    }

    /**
     * 新增角色
     *
     * @param role 待新增的角色
     */
    @Override
    public void createRole(TAuthRole role) {

    }

    /**
     * 修改角色
     *
     * @param role 待修改的角色
     */
    @Override
    public void updateRole(TAuthRole role) {

    }

    /**
     * 删除角色
     *
     * @param roleIds 待删除角色的 id
     */
    @Override
    public void deleteRoles(String roleIds) {

    }
}
