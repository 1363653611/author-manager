package com.zbcn.authormanager.common.base.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zbcn.authormanager.author.entity.TAuthUser;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 基础控制类
 * @Author: zbcn8
 * @Date: 2019/7/21 09:45
 */
public class BaseController {

    /**
     * 获取subject
     * @return
     */
    protected static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 获取登录用户
     * @return
     */
    protected TAuthUser getCurrentUser(){
        return (TAuthUser)getSubject().getPrincipal();
    }

    /**
     * 获取session
     * @return
     */
    protected static Session getSession(){
        return getSubject().getSession();
    }

    /**
     * 是否创建session 如果当前没有session
     * @param create
     * @return
     */
    protected  static Session getSession(Boolean create){
        return getSubject().getSession(create);
    }

    /**
     * 登录
     * @param token
     */
    protected void login(AuthenticationToken token){
        getSubject().login(token);
    }

    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("rows", pageInfo.getRecords());
        data.put("total", pageInfo.getTotal());
        return data;
    }
}
