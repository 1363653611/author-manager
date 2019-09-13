package com.zbcn.authormanager.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.zbcn.authormanager.author.entity.TAuthUser;
import com.zbcn.authormanager.common.constant.AuthorConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName AuthorUtils.java
 * @Description 工具类
 * @createTime 2019年08月03日 10:49:00
 */
public class AuthorUtils {

    /**
     * 駝峰轉下下劃綫
     * @param value
     * @return
     */
    public static String camelToUnderSore(String value){

        if (StringUtils.isBlank(value)) {
            return value;
        }

        String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
        if(arr.length == 0){
            return value;
        }
        StringBuilder builder = new StringBuilder();

        IntStream.range(0,arr.length).forEach(i->{
            if(i != arr.length-1){
                builder.append(arr[i]).append(StringPool.UNDERSCORE);
            }else {
                builder.append(arr[i]);
            }
        });
        return StringUtils.lowerCase(builder.toString());
    }

    /**
     * 下劃綫轉駝峰
     * @param value
     * @return
     */
    public static String underscoreToCamel(String value){
        StringBuilder builder = new StringBuilder();
        if(StringUtils.isBlank(value)){
            return value;
        }
        String[] arr = value.split("_");
        IntStream.range(0,arr.length).forEach(i -> {
            builder.append((String.valueOf(arr[i].charAt(0))).toUpperCase()).append(arr[i].substring(1));
        });
        return builder.toString();
    }

    /**
     * 判斷是否為ajax請求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request){
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }

    /**
     * 正则校验
     *
     * @param regex 正则表达式字符串
     * @param value 要匹配的字符串
     * @return 正则校验结果
     */
    public static boolean match(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * 获取当前登录用户
     *
     * @return User
     */
    public static TAuthUser getCurrentUser() {
        return (TAuthUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取视图路径
     * @param viewName
     * @return
     */
    public static String view(String viewName) {
        return AuthorConstant.VIEW_PREFIX + viewName;
    }
}
