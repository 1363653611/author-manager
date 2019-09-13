package com.zbcn.authormanager.generator.entity;

import lombok.Data;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName Column.java
 * @Description 字段
 * @createTime 2019年08月03日 10:25:00
 */
@Data
public class Column {

    /**
     * 名称
     */
    private String name;

    /**
     * 是否为主键
     */
    private Boolean isKey;

    /**
     * 类型
     */
    private String type;

    /**
     * 注释
     */
    private String remark;
    /**
     * 属性名称
     */
    private String field;
}
