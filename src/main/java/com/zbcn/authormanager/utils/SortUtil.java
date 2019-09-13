package com.zbcn.authormanager.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zbcn.authormanager.common.constant.AuthorConstant;
import com.zbcn.authormanager.common.entity.QueryRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName SortUtil.java
 * @Description 处理排序工具类
 * @createTime 2019年08月11日 11:36:00
 */
public class SortUtil {


    /**
     * 处理排序 for mybatis-plus
     * @param request  QueryRequest
     * @param wrapper  wrapper
     * @param defaultSort 默认排序的字段
     * @param defaultOrder 默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper wrapper, String defaultSort, String defaultOrder, boolean camelToUnderscore){

        String sortField = request.getField();

        if (camelToUnderscore) {
            sortField = AuthorUtils.camelToUnderSore(sortField);
            defaultSort = AuthorUtils.camelToUnderSore(defaultSort);
        }
        if (StringUtils.isNotBlank(request.getField())
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(request.getField(), "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), AuthorConstant.ORDER_DESC)){
                wrapper.orderByDesc(sortField);
            }else {
                wrapper.orderByAsc(sortField);
            }

        } else {
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, AuthorConstant.ORDER_DESC)) {
                    wrapper.orderByDesc(defaultSort);
                }else {
                    wrapper.orderByAsc(defaultSort);
                }

            }
        }
    }
}
