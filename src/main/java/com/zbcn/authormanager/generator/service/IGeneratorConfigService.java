package com.zbcn.authormanager.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zbcn.authormanager.generator.entity.GeneratorConfig;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName IGeneratorConfigService.java
 * @Description 生成器配置
 * @createTime 2019年08月03日 10:10:00
 */
public interface IGeneratorConfigService extends IService<GeneratorConfig>{

    /**
     * 查询
     *
     * @return GeneratorConfig
     */
    GeneratorConfig findGeneratorConfig();

    /**
     * 修改
     *
     * @param generatorConfig generatorConfig
     */
    void updateGeneratorConfig(GeneratorConfig generatorConfig);
}
