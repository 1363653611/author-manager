package com.zbcn.authormanager.author.controller;

import com.wf.captcha.Captcha;
import com.zbcn.authormanager.author.entity.TAuthUser;
import com.zbcn.authormanager.author.entity.log.TLoginLog;
import com.zbcn.authormanager.author.service.ILoginLogService;
import com.zbcn.authormanager.author.service.IUserService;
import com.zbcn.authormanager.common.anntation.Limit;
import com.zbcn.authormanager.common.base.controller.BaseController;
import com.zbcn.authormanager.common.exception.AuthorException;
import com.zbcn.authormanager.common.response.ResponseResult;
import com.zbcn.authormanager.utils.CaptchaUtil;
import com.zbcn.authormanager.utils.MD5Util;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 登录controller
 * @Author: zbcn8
 * @Date: 2019/7/20 18:40
 */
@RestController
@Validated
public class LoginController extends BaseController{

    @Autowired
    private ILoginLogService loginLogService;

    @Autowired
    private IUserService userService;

    @GetMapping("/test")
    public String test(){
        return "权限管理系统！";
    }

    /**
     * 登录入口
     * @param username
     * @param password
     * @param verifyCode
     * @param rememberMe
     * @param request
     * @return
     * @throws AuthorException
     */
    @Limit(key = "login", period = 60, count = 20, name = "登录接口", prefix = "limit")
    @PostMapping(path = "login")
    public ResponseResult<Object> login(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password,
            @NotBlank(message = "{required}") String verifyCode,
            boolean rememberMe,
            HttpServletRequest request
    ) throws AuthorException {

        if(!CaptchaUtil.verify(verifyCode,request)){
            throw new AuthorException("验证码错误！");
        }

        try {
            password = MD5Util.encrypt(username.toLowerCase(), password);
            //生成token
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);

            super.login(token);
            // 保存登录日志
            TLoginLog loginLog = new TLoginLog();
            loginLog.setUsername(username);
            loginLog.setSystemBrowserInfo();
            this.loginLogService.saveLoginLog(loginLog);
            return ResponseResult.success("登录成功");
        }catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e){
            throw new AuthorException("认证失败！");
        }

    }

    /**
     * 注册用户信息
     * @param username
     * @param password
     * @return
     */
    @PostMapping("regist")
    public ResponseResult<Object> regist(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password
    ) throws AuthorException {
        //獲取用戶
        TAuthUser user = userService.findByName(username);
        if(Objects.nonNull(user)){
            throw new AuthorException("该用户名已存在");
        }
        userService.register(username, password);
        return ResponseResult.success("注册成功");
    }

    /**
     * 用户登录信息
     * @param username
     * @return
     */
    @GetMapping("index/{username}")
    public ResponseResult<Object> index( @NotBlank(message = "{required}") @PathVariable String username){

        Map<String, Object> data = userService.index(username);
        return ResponseResult.success(data);
    }

    @GetMapping("images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.outPng(110, 34, 4, Captcha.TYPE_ONLY_NUMBER, request, response);
    }
}
