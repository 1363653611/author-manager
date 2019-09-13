package com.zbcn.authormanager.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zbcn.authormanager.common.entity.QueryRequest;
import com.zbcn.authormanager.generator.entity.Column;
import com.zbcn.authormanager.generator.entity.Table;

import java.util.List;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName IGeneratorService.java
 * @Description 生成器service
 * @createTime 2019年08月03日 10:42:00
 */
public interface IGeneratorService {
    List<String> getDatabases(String databaseType);
    IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName);
    List<Column> getColumns(String databaseType, String schemaName, String tableName);
}
