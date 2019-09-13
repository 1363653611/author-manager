package com.zbcn.authormanager.author.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zbcn.authormanager.author.entity.TAuthUser;
import com.zbcn.authormanager.author.entity.log.TLoginLog;

import java.util.List;
import java.util.Map;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName ILoginLogService.java
 * @Description 登录日志service
 * @createTime 2019年07月21日 12:18:00
 */
public interface ILoginLogService extends IService<TLoginLog> {

    /**
     * 保存登录日志
     *
     * @param loginLog 登录日志
     */
    void saveLoginLog(TLoginLog loginLog);

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
