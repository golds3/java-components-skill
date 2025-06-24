package com.hbx.study.spring.bean.mybeans;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * todo 这里不应该实现 BeanPostProcessor，这回导致BeanA 的拦截方法pre、after要在BeanA初始化完后才生效，即会先输出afterPropertiesSet 再输出BeanA postProcessBefore
 */
@Component
public class BeanA implements ApplicationContextAware, InitializingBean, BeanPostProcessor, BeanFactoryPostProcessor, DisposableBean {
    private BeanB beanB;
    {
        System.out.println("BeanA create");
    }
    @Autowired
    public void setBeanB(BeanB beanB) {
        System.out.println("BeanA populate");
        this.beanB = beanB;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("BeanA aware");
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanA){
            System.out.println("BeanA postProcessBeforeInitialization");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("BeanA postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanA afterPropertiesSet");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanA){
            System.out.println("BeanA postProcessAfterInitialization");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanA destroy");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanA postProcessBeanFactory");
    }
}
