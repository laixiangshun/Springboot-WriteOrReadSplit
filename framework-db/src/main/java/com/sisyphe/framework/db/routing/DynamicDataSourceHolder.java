package com.sisyphe.framework.db.routing;

/**
 * Created by lailai on 2017/12/5.
 * 数据库切换器
 */
public class DynamicDataSourceHolder {
    /**
     * 使用ThreadLocal把数据库与当前线程绑定
     */
    private static final ThreadLocal<DbType> DATA_SOURCE=new ThreadLocal<>();

    public static void setDataSource(DbType dbType){
        if(dbType==null){
            dbType=DbType.MASTER;
        }
        DATA_SOURCE.set(dbType);
    }

    public static DbType getDataScource(){
        return DATA_SOURCE.get()==null ? DbType.MASTER : DATA_SOURCE.get();
    }

    public static void clearDataSource(){
        DATA_SOURCE.remove();
    }
}
