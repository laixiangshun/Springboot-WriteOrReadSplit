package com.sisyphe.framework.db.routing.annotation;

import com.common.bootstrap.AutoConfigurationImportSelector;
import com.sisyphe.framework.db.routing.DbType;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by lailai on 2017/12/5.
 * 生命一个注解：启用数据库动态路由
 * 在程序主方法上启用数据库动态路由 @EnableDataDynamicRouting
 */
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AutoConfigurationImportSelector.class)
public @interface EnableDataDynamicRouting {

}
