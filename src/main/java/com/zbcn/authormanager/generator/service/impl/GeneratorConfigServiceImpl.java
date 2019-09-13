package com.zbcn.authormanager.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbcn.authormanager.generator.entity.GeneratorConfig;
import com.zbcn.authormanager.generator.mapper.GeneratorConfigMapper;
import com.zbcn.authormanager.generator.service.IGeneratorConfigService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zbcn8
 * @version 1.0.0
 * @ClassName GeneratorConfigServiceImpl.java
 * @Description 生成器配置service
 * @createTime 2019年08月03日 10:11:00
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class GeneratorConfigServiceImpl extends ServiceImpl<GeneratorConfigMapper,GeneratorConfig> implements IGeneratorConfigService {

    /**
     * 查询
     *
     * @return GeneratorConfig
     */
    @Override
    public GeneratorConfig findGeneratorConfig() {
        List<GeneratorConfig> generatorConfigs = this.baseMapper.selectList(null);
        return CollectionUtils.isNotEmpty(generatorConfigs) ? generatorConfigs.get(0) : null;
    }

    /**
     * 修改
     *
     * @param generatorConfig generatorConfig
     */
    @Override
    @Transactional
    public void updateGeneratorConfig(GeneratorConfig generatorConfig) {
        this.saveOrUpdate(generatorConfig);
    }
}
