package com.zbcn.authormanager.author.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbcn.authormanager.author.entity.TAuthUser;
import com.zbcn.authormanager.author.entity.log.TLoginLog;
import com.zbcn.authormanager.author.mapper.LoginLogMapper;
import com.zbcn.authormanager.author.service.ILoginLogService;
import com.zbcn.authormanager.utils.AddressUtil;
import com.zbcn.authormanager.utils.HttpContextUtil;
import com.zbcn.authormanager.utils.IPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName LoginServiceImpl.java
 * @Description 登录日志实现类
 * @createTime 2019年07月21日 12:21:00
 */
@Service("loginLogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoginServiceImpl extends ServiceImpl<LoginLogMapper, TLoginLog> implements ILoginLogService {
    /**
     * 保存登录日志
     *
     * @param loginLog 登录日志
     */
    @Override
    public void saveLoginLog(TLoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        this.save(loginLog);
    }

    /**
     * 获取系统总访问次数
     *
     * @return Long
     */
    @Override
    public Long findTotalVisitCount() {
        return baseMapper.findTotalVisitCount();
    }

    /**
     * 获取系统今日访问次数
     *
     * @return Long
     */
    @Override
    public Long findTodayVisitCount() {
        return baseMapper.findTotalVisitCount();
    }

    /**
     * 获取系统今日访问 IP数
     *
     * @return Long
     */
    @Override
    public Long findTodayIp() {
        return baseMapper.findTodayIp();
    }

    /**
     * 获取系统近七天来的访问记录
     *
     * @param user 用户
     * @return 系统近七天来的访问记录
     */
    @Override
    public List<Map<String, Object>> findLastSevenDaysVisitCount(TAuthUser user) {
        return baseMapper.findLastSevenDaysVisitCount(user);
    }


}
