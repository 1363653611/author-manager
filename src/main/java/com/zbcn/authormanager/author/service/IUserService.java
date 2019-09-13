package com.zbcn.authormanager.author.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zbcn.authormanager.author.entity.TAuthUser;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName IUserService.java
 * @Description 用户操作service
 * @createTime 2019年07月21日 13:46:00
 */
public interface IUserService extends IService<TAuthUser> {

    /**
     * 依据名称获取用户
     * @param username
     * @return
     */
    TAuthUser findByName(String username);

    /**
     * 注册用户
     *
     * @param username 用户名
     * @param password 密码
     */
    void register(String username, String password);

    /**
     * 用户登录信息
     * @param username
     * @return
     */
    Map<String,Object> index(@NotBlank(message = "{required}") String username);

    /**
     * 更新用户登录时间
     *
     * @param username 用户名
     */
    void updateLoginTime(String username);
}
