package com.activiti6.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * activiti 配置类
 * liuzhize 2019年3月7日下午3:26:56
 */
@Configuration
public class ActivitiConfig {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 初始化配置
     *
     * @return
     */
    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        // 执行工作流对应的数据源
        configuration.setDataSource(dataSource);
        // 是否自动创建流程引擎表
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //管理线程池异步激活
        configuration.setAsyncExecutorActivate(false);
        // 流程历史等级
        configuration.setHistoryLevel(HistoryLevel.FULL);
        //设置全局eventlister集合
//        configuration.setEventListeners()//
        //流程部署缓存 最多10条
//        configuration.setProcessDefinitionCacheLimit(10);
        // 流程图字体
        configuration.setActivityFontName("宋体");
        configuration.setAnnotationFontName("宋体");
        configuration.setLabelFontName("宋体");
        //事务处理器
        configuration.setTransactionManager(transactionManager);
        return configuration;
    }
}
