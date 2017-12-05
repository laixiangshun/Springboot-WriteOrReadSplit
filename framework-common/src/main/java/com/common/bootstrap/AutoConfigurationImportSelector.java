package com.common.bootstrap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by lailai on 2017/12/5.
 */
@Order(Ordered.LOWEST_PRECEDENCE-1)
@Configuration
public class AutoConfigurationImportSelector implements DeferredImportSelector,BeanClassLoaderAware, ResourceLoaderAware,
        BeanFactoryAware,EnvironmentAware{

    private ConfigurableListableBeanFactory beanFactory;
    private Environment environment;
    private ClassLoader beanClassLoader;
    private ResourceLoader resourceLoader;

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> configurations=getCandidateConfigurations();
        configurations=removeDuplicates(configurations);
        return configurations.toArray(new String[configurations.size()]);
    }

    protected List<String> getCandidateConfigurations(){
        return SpringFactoriesLoader.loadFactoryNames(this.getClass(), getBeanClassLoader());
    }
    protected final <T> List<T> removeDuplicates(List<T> list){
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException{
        Assert.isInstanceOf(ConfigurableListableBeanFactory.class,beanFactory);
        this.beanFactory=(ConfigurableListableBeanFactory)beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
