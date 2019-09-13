package com.zbcn.authormanager.author.controller;

import com.zbcn.authormanager.author.entity.TAuthUser;
import com.zbcn.authormanager.author.service.IUserService;
import com.zbcn.authormanager.common.authentication.ShiroHelper;
import com.zbcn.authormanager.common.base.controller.BaseController;
import com.zbcn.authormanager.common.constant.AuthorConstant;
import com.zbcn.authormanager.utils.AuthorUtils;
import com.zbcn.authormanager.utils.DateUtil;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName ViewController.java
 * @Description 页面展示controller
 * @createTime 2019年08月11日 20:52:00
 */
@Controller("systemView")
public class ViewController extends BaseController{

    @Autowired
    private IUserService userService;
    @Autowired
    private ShiroHelper shiroHelper;

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request){

        if(AuthorUtils.isAjaxRequest(request)){
            throw new ExpiredSessionException();
        }else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(AuthorUtils.view("login"));
            return mav;
        }
    }

    /**
     * 未认证页面
     * @return
     */
    @GetMapping("unauthorized")
    public String unauthorized() {
        return AuthorUtils.view("error/403");
    }

    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model){
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        TAuthUser user = super.getCurrentUser();
        user.setPassword("It's a secret");
        model.addAttribute("user", userService.findByName(user.getUsername())); // 获取实时的用户信息
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles",authorizationInfo.getRoles());
        return "index";
    }

    @GetMapping(AuthorConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return AuthorUtils.view("layout");
    }

    /**
     * 用户密码更新
     * @return
     */
    @GetMapping(AuthorConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return AuthorUtils.view("system/user/passwordUpdate");
    }

    /**
     * 用户简介
     * @return
     */
    @GetMapping(AuthorConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return AuthorUtils.view("system/user/userProfile");
    }

    /**
     * 用户头像
     * @return
     */
    @GetMapping(AuthorConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return AuthorUtils.view("system/user/avatar");
    }

    /**
     * 简介更新页面
     * @return
     */
    @GetMapping(AuthorConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return AuthorUtils.view("system/user/profileUpdate");
    }

    @GetMapping(AuthorConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return AuthorUtils.view("system/user/user");
    }


    /**
     * 添加用户
     * @return
     */
    @GetMapping(AuthorConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return AuthorUtils.view("system/user/userAdd");
    }

    @GetMapping(AuthorConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return AuthorUtils.view("system/user/userDetail");
    }


    @GetMapping(AuthorConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return AuthorUtils.view("system/user/userUpdate");
    }

    @GetMapping(AuthorConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return AuthorUtils.view("system/role/role");
    }

    @GetMapping(AuthorConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return AuthorUtils.view("system/menu/menu");
    }

    @GetMapping(AuthorConstant.VIEW_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return AuthorUtils.view("system/dept/dept");
    }

    @RequestMapping(AuthorConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return AuthorUtils.view("index");
    }

    @GetMapping(AuthorConstant.VIEW_PREFIX + "404")
    public String error404() {
        return AuthorUtils.view("error/404");
    }

    @GetMapping(AuthorConstant.VIEW_PREFIX + "403")
    public String error403() {
        return AuthorUtils.view("error/403");
    }

    @GetMapping(AuthorConstant.VIEW_PREFIX + "500")
    public String error500() {
        return AuthorUtils.view("error/500");
    }


    private void resolveUserModel(String username, Model model, Boolean transform) {

        TAuthUser user = userService.findByName(username);
        model.addAttribute("user", user);
        if (transform) {
            String ssex = user.getGender();
            if (TAuthUser.SEX_MALE.equals(ssex)) {
                user.setGender("男");
            }else if (TAuthUser.SEX_FEMALE.equals(ssex)) {
                user.setGender("女");
            }else {
                user.setGender("保密");
            }
        }
        if (user.getLastLoginTime() != null){
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        }
    }




}
