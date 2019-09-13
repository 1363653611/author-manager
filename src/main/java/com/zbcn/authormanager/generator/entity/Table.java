package com.zbcn.authormanager.generator.entity;

import lombok.Data;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName Table.java
 * @Description 表名
 * @createTime 2019年08月03日 10:28:00
 */
@Data
public class Table {

    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据量（行）
     */
    private Long dataRows;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
}
