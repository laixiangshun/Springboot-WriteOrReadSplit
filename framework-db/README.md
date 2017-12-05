Framework-DB-Routing 数据库读写分离
此模块可通过注解实现数据库动态路由（读写分离），支持JPA，JDBC

spring boot read/write split

使用说明
1.实现主库(MasterDataSource)和从库(SlaveDataSource)的数据源配置接口

MasterDataSource

@Configuration
public class WriteDataSourceConfig implements MasterDataSource {

    @Bean(name = "writeDataSource")
    @Qualifier("writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Override
    public DataSource masterDataSource() {
        return writeDataSource();
    }
}

SlaveDataSource

@Configuration
public class ReadDataSourceConfig implements SlaveDataSource {

    @Bean(name = "readDataSource")
    @Qualifier("readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource readDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Override
    public DataSource slaveDataSource() {
        return readDataSource();
    }
}

2.在程序主方法上启用数据库动态路由 @EnableDataDynamicRouting

3.在需要用到从库的方法上加上 @TargetDataSource(DbType.SLAVE) ，在没有使用此注解的数据库路由默认为主库(MASTER)

@TargetDataSource(DbType.SLAVE)
public CardConfigParam findByParamName(String paramName) {
    return cardConfigParamRepository.findByParamName(paramName);
}