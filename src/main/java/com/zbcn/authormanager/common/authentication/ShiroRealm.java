package com.zbcn.authormanager.common.authentication;

import com.zbcn.authormanager.author.entity.TAuthRole;
import com.zbcn.authormanager.author.entity.TAuthUser;
import com.zbcn.authormanager.author.service.IRoleService;
import com.zbcn.authormanager.author.service.IUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName ShiroRealm.java
 * @Description 权限校验,包括认证和授权
 * @createTime 2019年08月03日 14:51:00
 */
@Component
public class ShiroRealm extends AuthorizingRealm{

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;
    /**
     *  授权模块，获取用户角色和权限
     * Retrieves the AuthorizationInfo for the given principals from the underlying data store.  When returning
     * an instance from this method, you might want to consider using an instance of
     * {@link SimpleAuthorizationInfo SimpleAuthorizationInfo}, as it is suitable in most cases.
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return the AuthorizationInfo associated with this principals.
     * @see SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        TAuthUser user = (TAuthUser)SecurityUtils.getSubject().getPrincipal();
        String userName = user.getUsername();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取用户角色集
        List<TAuthRole> roleList = this.roleService.findUserRole(userName);
        if(CollectionUtils.isNotEmpty(roleList)){
            Set<String> permissionSet = roleList.stream().map(TAuthRole::getRoleName).collect(Collectors.toSet());
            simpleAuthorizationInfo.setStringPermissions(permissionSet);
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     * Retrieves authentication data from an implementation-specific datasource (RDBMS, LDAP, etc) for the given
     * authentication token.
     * <p/>
     * For most datasources, this means just 'pulling' authentication data for an associated subject/user and nothing
     * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
     * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified token.
     *
     * @param token the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取用户输入的用户名和密码
        String username = (String)token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        TAuthUser user = userService.findByName(username);
        if(Objects.isNull(user)){
            throw new UnknownAccountException("用户名错误！");
        }
        if(!StringUtils.equals(password, user.getPassword())){
            throw new IncorrectCredentialsException("密码错误！");
        }
        if(TAuthUser.STATUS_LOCK.equals(user.getStatus())){
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        return new SimpleAuthenticationInfo(user,password,getName());
    }
}
