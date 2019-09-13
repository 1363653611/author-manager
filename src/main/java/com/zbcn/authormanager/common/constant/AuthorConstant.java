package com.zbcn.authormanager.common.constant;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName AuthorConstant.java
 * @Description 常量类
 * @createTime 2019年08月03日 11:59:00
 */
public class AuthorConstant {

    // 排序规则：降序
    public static final String ORDER_DESC = "desc";
    // 排序规则：升序
    public static final String ORDER_ASC = "asc";

    // 前端页面路径前缀
    public static final String VIEW_PREFIX = "author/views/";

    // 验证码 Session Key
    public static final String CODE_PREFIX = "auth_captcha_";

    // 允许下载的文件类型，根据需求自己添加（小写）
    public static final String[] VALID_FILE_TYPE = {"xlsx", "zip"};
}
