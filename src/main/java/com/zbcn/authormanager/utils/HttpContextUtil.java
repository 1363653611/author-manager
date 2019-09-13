package com.zbcn.authormanager.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName HttpContextUtil.java
 * @Description TODO
 * @createTime 2019年07月21日 12:08:00
 */
public class HttpContextUtil {
    private HttpContextUtil(){

    }
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
