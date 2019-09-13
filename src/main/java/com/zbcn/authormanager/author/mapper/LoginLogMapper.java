package com.zbcn.authormanager.author.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zbcn.authormanager.author.entity.TAuthUser;
import com.zbcn.authormanager.author.entity.log.TLoginLog;

import java.util.List;
import java.util.Map;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName LoginLogMapper.java
 * @Description 登录操作数据库
 * @createTime 2019年07月21日 12:36:00
 */
public interface LoginLogMapper extends BaseMapper<TLoginLog> {

    /**
     * 获取系统总访问次数
     *
     * @return Long
     */
    Long findTotalVisitCount();

    /**
     * 获取系统今日访问次数
     *
     * @return Long
     */
    Long findTodayVisitCount();

    /**
     * 获取系统今日访问 IP数
     *
     * @return Long
     */
    Long findTodayIp();

    /**
     * 获取系统近七天来的访问记录
     *
     * @param user 用户
     * @return 系统近七天来的访问记录
     */
    List<Map<String, Object>> findLastSevenDaysVisitCount(TAuthUser user);
}
