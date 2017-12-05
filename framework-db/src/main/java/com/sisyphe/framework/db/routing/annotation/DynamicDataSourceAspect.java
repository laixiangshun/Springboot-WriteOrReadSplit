package com.sisyphe.framework.db.routing.annotation;

import com.sisyphe.framework.db.routing.DynamicDataSourceHolder;
import com.sisyphe.framework.db.routing.DynamicRoutingDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by lailai on 2017/12/5.
 */
@Aspect
@Component
public class DynamicDataSourceAspect implements Ordered{


    private static final Logger logger= LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Around("@annotation(targetDataSource)")
    public Object proceed(ProceedingJoinPoint point,TargetDataSource targetDataSource) throws Throwable{
        try {
            logger.info("切换数据库是: {}", targetDataSource.value());
            DynamicDataSourceHolder.setDataSource(targetDataSource.value());
            return point.proceed();
        }finally {
            DynamicDataSourceHolder.clearDataSource();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
