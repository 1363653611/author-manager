package com.zbcn.authormanager.author.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zbcn.authormanager.author.entity.TAuthDept;
import com.zbcn.authormanager.common.entity.DeptTree;
import com.zbcn.authormanager.common.entity.QueryRequest;

import java.util.List;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName IDeptService.java
 * @Description
 * @createTime 2019年08月03日 20:00:00
 */
public interface IDeptService extends IService<TAuthDept> {

    /**
     * 获取部门树（下拉选使用）
     *
     * @return 部门树集合
     */
    List<DeptTree<TAuthDept>> findDepts();

    /**
     * 获取部门列表（树形列表）
     *
     * @param dept 部门对象（传递查询参数）
     * @return 部门树
     */
    List<DeptTree<TAuthDept>> findDepts(TAuthDept dept);


    /**
     * 获取部门树（供Excel导出）
     *
     * @param dept    部门对象（传递查询参数）
     * @param request QueryRequest
     * @return List<Dept>
     */
    List<TAuthDept> findDepts(TAuthDept dept, QueryRequest request);


    /**
     * 新增部门
     *
     * @param dept 部门对象
     */
    void createDept(TAuthDept dept);

    /**
     * 修改部门
     *
     * @param dept 部门对象
     */
    void updateDept(TAuthDept dept);

    /**
     * 删除部门
     *
     * @param deptIds 部门 ID集合
     */
    void deleteDepts(String[] deptIds);
}
