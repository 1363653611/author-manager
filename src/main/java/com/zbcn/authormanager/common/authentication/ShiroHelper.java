package com.zbcn.authormanager.common.authentication;

import com.zbcn.authormanager.common.anntation.Helper;
import org.apache.shiro.authz.AuthorizationInfo;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName ShiroHelper.java
 * @Description TODO
 * @createTime 2019年08月11日 20:54:00
 */
@Helper
public class ShiroHelper extends ShiroRealm {

    /**
     *  获取当前用户的角色和权限集合
     * @return
     */
    public AuthorizationInfo getCurrentuserAuthorizationInfo(){
        return super.doGetAuthorizationInfo(null);
    }
}
