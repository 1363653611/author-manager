package com.zbcn.authormanager.author.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbcn.authormanager.author.entity.TUserRole;
import com.zbcn.authormanager.author.mapper.UserRoleMapper;
import com.zbcn.authormanager.author.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName UserRoleServiceImp.java
 * @Description 用户角色关系service
 * @createTime 2019年07月21日 14:17:00
 */
@Service("userRoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper,TUserRole> implements IUserRoleService {
}
