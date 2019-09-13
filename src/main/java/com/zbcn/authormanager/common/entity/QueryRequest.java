package com.zbcn.authormanager.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName QueryRequest.java
 * @Description 查询请求
 * @createTime 2019年08月03日 12:42:00
 */
@Data
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -513487390987468402L;

    /**
     * 当前页面数据量
     */
    private int pageSize = 10;
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序规则，asc升序，desc降序
     */
    private String order;
}
