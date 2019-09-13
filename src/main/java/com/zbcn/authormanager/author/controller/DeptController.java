package com.zbcn.authormanager.author.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import com.zbcn.authormanager.author.entity.TAuthDept;
import com.zbcn.authormanager.author.service.IDeptService;
import com.zbcn.authormanager.common.anntation.Log;
import com.zbcn.authormanager.common.entity.DeptTree;
import com.zbcn.authormanager.common.entity.QueryRequest;
import com.zbcn.authormanager.common.exception.AuthorException;
import com.zbcn.authormanager.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName DeptController.java
 * @Description 部门控制
 * @createTime 2019年08月03日 19:56:00
 */
@Slf4j
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("select/tree")
    public List<DeptTree<TAuthDept>> getDeptTree() throws AuthorException {
        try {
            return this.deptService.findDepts();
        } catch (Exception e) {
            String message = "获取部门树失败";
            log.error(message, e);
            throw new AuthorException(message);
        }
    }

    @GetMapping("tree")
    public ResponseResult getDeptTree(TAuthDept dept) throws AuthorException {
        try {
            List<DeptTree<TAuthDept>> depts = this.deptService.findDepts(dept);
            return ResponseResult.success(depts);
        } catch (Exception e) {
            String message = "获取部门树失败";
            log.error(message, e);
            throw new AuthorException(message);
        }
    }

    @Log("新增部门")
    @PostMapping
    @RequiresPermissions("dept:add")
    public ResponseResult addDept(@Valid TAuthDept dept) throws AuthorException {
        try {
            this.deptService.createDept(dept);
            return ResponseResult.success("添加成功");
        } catch (Exception e) {
            String message = "新增部门失败";
            log.error(message, e);
            throw new AuthorException(message);
        }
    }

    @Log("删除部门")
    @GetMapping("delete/{deptIds}")
    @RequiresPermissions("dept:delete")
    public ResponseResult deleteDepts(@NotBlank(message = "{required}") @PathVariable String deptIds) throws AuthorException {
        try {
            String[] ids = deptIds.split(StringPool.COMMA);
            this.deptService.deleteDepts(ids);
            return ResponseResult.success("删除部门成功");
        } catch (Exception e) {
            String message = "删除部门失败";
            log.error(message, e);
            throw new AuthorException(message);
        }
    }

    @Log("修改部门")
    @PostMapping("update")
    @RequiresPermissions("dept:update")
    public ResponseResult updateDept(@Valid TAuthDept dept) throws AuthorException {
        try {
            this.deptService.updateDept(dept);
            return ResponseResult.success("更新部门成功");
        } catch (Exception e) {
            String message = "修改部门失败";
            log.error(message, e);
            throw new AuthorException(message);
        }
    }

    @GetMapping("excel")
    @RequiresPermissions("dept:export")
    public void export(TAuthDept dept, QueryRequest request, HttpServletResponse response) throws AuthorException {
        try {
            List<TAuthDept> depts = this.deptService.findDepts(dept, request);
            ExcelKit.$Export(TAuthDept.class, response).downXlsx(depts, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new AuthorException(message);
        }
    }
}
