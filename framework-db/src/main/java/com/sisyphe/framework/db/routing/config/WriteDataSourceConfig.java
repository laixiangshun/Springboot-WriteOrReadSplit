package com.sisyphe.framework.db.routing.config;

import com.sisyphe.framework.db.routing.MasterDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by lailai on 2017/12/5.
 * 使用主库的数据源配置接口
 */
@Configuration
public class WriteDataSourceConfig implements MasterDataSource{
    @Override
    public DataSource masterDataSource() {
        return writeDataSource();
    }

    @Bean(name = "writeDataSource")
    @Qualifier("writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource writeDataSource(){
        return DataSourceBuilder.create().build();
    }
}
