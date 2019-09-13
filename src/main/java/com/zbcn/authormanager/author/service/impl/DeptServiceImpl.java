package com.zbcn.authormanager.author.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbcn.authormanager.author.entity.TAuthDept;
import com.zbcn.authormanager.author.mapper.DeptMapper;
import com.zbcn.authormanager.author.service.IDeptService;
import com.zbcn.authormanager.common.constant.AuthorConstant;
import com.zbcn.authormanager.common.entity.DeptTree;
import com.zbcn.authormanager.common.entity.QueryRequest;
import com.zbcn.authormanager.utils.SortUtil;
import com.zbcn.authormanager.utils.TreeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName DeptServiceImpl.java
 * @Description 部门相关业务类
 * @createTime 2019年08月03日 20:09:00
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
public class DeptServiceImpl extends ServiceImpl<DeptMapper,TAuthDept> implements IDeptService{

    /**
     * 获取部门树（下拉选使用）
     *
     * @return 部门树集合
     */
    @Override
    public List<DeptTree<TAuthDept>> findDepts() {
        List depts = baseMapper.selectList(new QueryWrapper());
        convertDepts(depts);
        return TreeUtil.buildDeptTree(depts);
    }


    /**
     * 获取部门列表（树形列表）
     *
     * @param dept 部门对象（传递查询参数）
     * @return 部门树
     */
    @Override
    public List<DeptTree<TAuthDept>> findDepts(TAuthDept dept) {
        QueryWrapper<TAuthDept> queryWrapper = new QueryWrapper<>();

        if(StringUtils.isNotBlank(dept.getDeptName())){
            queryWrapper.lambda().eq(TAuthDept::getDeptName, dept.getDeptName());
            queryWrapper.lambda().orderByAsc(TAuthDept :: getOrderNum);
        }
        List<TAuthDept> depts = this.baseMapper.selectList(queryWrapper);
        List<DeptTree<TAuthDept>> trees =  this.convertDepts(depts);
        return TreeUtil.buildDeptTree(trees);
    }

    /**
     * 获取部门树（供Excel导出）
     *
     * @param dept    部门对象（传递查询参数）
     * @param request QueryRequest
     * @return List<Dept>
     */
    @Override
    public List<TAuthDept> findDepts(TAuthDept dept, QueryRequest request) {
        QueryWrapper<TAuthDept> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(dept.getDeptName())){
            queryWrapper.lambda().eq(TAuthDept ::getDeptName,dept.getDeptName());
            SortUtil.handleWrapperSort(request, queryWrapper, "orderNum", AuthorConstant.ORDER_ASC, true);
        }

        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 新增部门
     *
     * @param dept 部门对象
     */
    @Override
    @Transactional
    public void createDept(TAuthDept dept) {
        Long parentId = dept.getParentId();
        if (parentId == null){
            dept.setParentId(0L);
        }
        dept.setCreatedTime(new Date());
        this.save(dept);
    }

    /**
     * 修改部门
     *
     * @param dept 部门对象
     */
    @Override
    @Transactional
    public void updateDept(TAuthDept dept) {
        dept.setUpdatedTime(new Date());
        this.baseMapper.updateById(dept);
    }

    /**
     * 删除部门
     *
     * @param deptIds 部门 ID集合
     */
    @Override
    public void deleteDepts(String[] deptIds) {
        this.delete(Arrays.asList(deptIds));
    }



    private  List<DeptTree<TAuthDept>> convertDepts(List<TAuthDept> depts) {

        List<DeptTree<TAuthDept>> trees = new ArrayList<>();

        depts.forEach(dept ->{
            DeptTree<TAuthDept> tree = new DeptTree<>();
            tree.setId(String.valueOf(dept.getDeptId()));
            tree.setParentId(String.valueOf(dept.getParentId()));
            tree.setName(dept.getDeptName());
            tree.setData(dept);
            trees.add(tree);
        });

        return trees;
    }

    private void delete(List<String> deptIds) {
        removeByIds(deptIds);

        LambdaQueryWrapper<TAuthDept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(TAuthDept::getParentId, deptIds);
        List<TAuthDept> depts = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(depts)) {
            List<String> deptIdList = new ArrayList<>();
            depts.forEach(d -> deptIdList.add(String.valueOf(d.getDeptId())));
            this.delete(deptIdList);
        }
    }
}
