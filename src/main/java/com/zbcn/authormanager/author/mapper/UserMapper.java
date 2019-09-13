package com.zbcn.authormanager.author.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zbcn.authormanager.author.entity.TAuthUser;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName UserMapper.java
 * @Description 用户操作类
 * @createTime 2019年07月21日 13:51:00
 */
public interface UserMapper extends BaseMapper<TAuthUser> {
    /**
     * 一句用户名获取用户
     * @param username
     * @return
     */
    TAuthUser findByName(String username);
}
