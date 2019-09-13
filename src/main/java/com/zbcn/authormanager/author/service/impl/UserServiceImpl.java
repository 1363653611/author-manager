package com.zbcn.authormanager.author.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbcn.authormanager.author.entity.TAuthUser;
import com.zbcn.authormanager.author.entity.TUserRole;
import com.zbcn.authormanager.author.mapper.UserMapper;
import com.zbcn.authormanager.author.service.ILoginLogService;
import com.zbcn.authormanager.author.service.IUserRoleService;
import com.zbcn.authormanager.author.service.IUserService;
import com.zbcn.authormanager.common.constant.LoginUserConst;
import com.zbcn.authormanager.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description user 操作的具体service
 * @createTime 2019年07月21日 13:48:00
 */
@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper,TAuthUser> implements IUserService {

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private ILoginLogService loginLogService;
    /**
     * 依据名称获取用户
     *
     * @param username
     * @return
     */
    @Override
    public TAuthUser findByName(String username) {
        return baseMapper.findByName(username);
    }

    /**
     * 注册用户
     *
     * @param username 用户名
     * @param password 密码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String username, String password) {
        TAuthUser user = new TAuthUser();
        user.setPassword(MD5Util.encrypt(username, password));
        user.setUsername(username);
        user.setCreatedTime(new Date());
        user.setStatus(LoginUserConst.STATUS_VALID);
        user.setGender(LoginUserConst.SEX_UNKNOW);
        user.setAvatar(LoginUserConst.DEFAULT_AVATAR);
        user.setTheme(LoginUserConst.THEME_BLACK);
        user.setIsTab(LoginUserConst.TAB_OPEN);
        user.setDescription("注册用户");
        this.save(user);

        TUserRole ur = new TUserRole();
        ur.setUserId(user.getUserId());
        // 注册用户角色 ID
        ur.setRoleId(2L);
        this.userRoleService.save(ur);
    }

    /**
     * 用户登录信息
     *
     * @param username
     * @return
     */
    @Override
    public Map<String, Object> index(@NotBlank(message = "{required}") String username) {
        updateLoginTime(username);
        Map<String, Object> data = new HashMap<>();
        // 获取系统访问记录
        Long totalVisitCount = this.loginLogService.findTotalVisitCount();
        Long todayVisitCount = this.loginLogService.findTodayVisitCount();
        Long todayIp = this.loginLogService.findTodayIp();
        data.put("totalVisitCount", totalVisitCount);
        data.put("todayVisitCount", todayVisitCount);
        data.put("todayIp", todayIp);
        // 获取近期系统访问记录
        List<Map<String, Object>> lastSevenVisitCount = this.loginLogService.findLastSevenDaysVisitCount(null);
        data.put("lastSevenVisitCount", lastSevenVisitCount);
        TAuthUser param = new TAuthUser();
        param.setUsername(username);
        List<Map<String, Object>> lastSevenUserVisitCount = this.loginLogService.findLastSevenDaysVisitCount(param);
        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
        return data;
    }

    /**
     * 更新用户登录时间
     *
     * @param username 用户名
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLoginTime(String username) {
        TAuthUser user = new TAuthUser();
        user.setLastLoginTime(new Date());
        this.baseMapper.update(user, new LambdaQueryWrapper<TAuthUser>().eq(TAuthUser::getUsername, username));
    }
}
