package com.zbcn.authormanager.author.controller;

import com.wuwenze.poi.ExcelKit;
import com.zbcn.authormanager.author.entity.TAuthRole;
import com.zbcn.authormanager.author.service.IRoleService;
import com.zbcn.authormanager.common.anntation.Log;
import com.zbcn.authormanager.common.base.controller.BaseController;
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
import java.util.Map;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName RoleController.java
 * @Description 角色控制
 * @createTime 2019年08月03日 19:24:00
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseResult<TAuthRole> getRole(TAuthRole role){
        List<TAuthRole> roles = roleService.findRoles(role);
        return ResponseResult.success(roles);
    }

    @GetMapping("/list")
    @RequiresPermissions("role:view")
    public ResponseResult roleList(TAuthRole role, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.roleService.findRoles(role, request));
        return ResponseResult.success(dataTable);
    }

    @Log("新增角色")
    @PostMapping
    @RequiresPermissions("role:add")
    public ResponseResult addRole(@Valid TAuthRole role) {
        try {
            this.roleService.createRole(role);
            return ResponseResult.success("新增角色成功");
        } catch (Exception e) {
            String message = "新增角色失败";
            log.error(message, e);
            return ResponseResult.fail(message);
        }
    }

    @Log("删除角色")
    @GetMapping("delete/{roleIds}")
    @RequiresPermissions("role:delete")
    public ResponseResult deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds) {
        try {
            this.roleService.deleteRoles(roleIds);
            return ResponseResult.success("删除角色成功");
        } catch (Exception e) {
            String message = "删除角色失败";
            log.error(message, e);
            return ResponseResult.fail(message);
        }
    }

    @Log("修改角色")
    @PostMapping("update")
    @RequiresPermissions("role:update")
    public ResponseResult updateRole(TAuthRole role) {
        try {
            this.roleService.updateRole(role);
            return ResponseResult.success("更新角色成功");
        } catch (Exception e) {
            String message = "修改角色失败";
            log.error(message, e);
            return ResponseResult.fail(message);
        }
    }

    @GetMapping("excel")
    @RequiresPermissions("role:export")
    public void export(QueryRequest queryRequest, TAuthRole role, HttpServletResponse response) throws AuthorException {
        try {
            List<TAuthRole> roles = this.roleService.findRoles(role, queryRequest).getRecords();
            ExcelKit.$Export(TAuthRole.class, response).downXlsx(roles, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new AuthorException(message);
        }
    }

}
