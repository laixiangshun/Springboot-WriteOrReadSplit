package com.sisyphe.framework.db.routing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lailai on 2017/12/5.
 * 数据库动态切换路由
 */
@Primary
@Component
public class DynamicRoutingDataSource extends AbstractRoutingDataSource{

    private static final Logger logger= LoggerFactory.getLogger(DynamicRoutingDataSource.class);

    @Autowired
    private MasterDataSource masterDataSource;

    @Autowired
    private SlaveDataSource slaveDataSource;

    @Override
    protected Object determineCurrentLookupKey() {
        DbType dbType=DynamicDataSourceHolder.getDataScource();
        logger.info("当前数据库为: {}",dbType);
        return dbType;
    }

    /**
     * 初始化数据库配置
     */
    @PostConstruct
    public void init(){
        Map<Object,Object> targetDataSource=new HashMap<>(2);
        targetDataSource.put(DbType.MASTER,masterDataSource.masterDataSource());
        targetDataSource.put(DbType.SLAVE,slaveDataSource.salveDataSource());
        this.setTargetDataSources(targetDataSource);
        //默认是主库
        this.setDefaultTargetDataSource(masterDataSource.masterDataSource());
    }
}
