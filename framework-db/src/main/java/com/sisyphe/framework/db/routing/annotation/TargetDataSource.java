package com.sisyphe.framework.db.routing.annotation;

import com.sisyphe.framework.db.routing.DbType;

import java.lang.annotation.*;

/**
 * Created by lailai on 2017/12/5.
 * 生命一个注解：指定数据库
 * 在需要用到从库的方法上加上 @TargetDataSource(DbType.SLAVE) ，在没有使用此注解的数据库路由默认为主库(MASTER)
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    DbType value() default DbType.MASTER;
}
