package com.sisyphe.framework.db.routing.config;

import com.sisyphe.framework.db.routing.SlaveDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by lailai on 2017/12/5.
 * 使用字库的数据源配置接口
 */
@Configuration
public class ReadDataSourceConfig implements SlaveDataSource{
    @Override
    public DataSource salveDataSource() {
        return readDataSource();
    }

    @Bean(name = "readDataSource")
    @Qualifier("readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource readDataSource(){
        return DataSourceBuilder.create().build();
    }
}
