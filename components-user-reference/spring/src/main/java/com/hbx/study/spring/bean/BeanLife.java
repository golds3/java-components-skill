package com.hbx.study.spring.bean;

import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

/**
 * bean 生命周期
 * 1. 实例化
 * 2. 初始化
 * 3. 注册Destruction回调
 * 4. bean使用
 * 5. 销毁
 * <p>
 * 创建的逻辑都在AbstractAutowireCapableBeanFactory
 * 销毁的逻辑都在DisposableBeanAdapter
 */
public class BeanLife{

    public static void main(String[] args) {
        /**
         * 初始化逻辑主要是在step1 {@link  AbstractAutowireCapableBeanFactory#createBeanInstance}
         * 实例化的逻辑主要是 step2-7 {@link AbstractAutowireCapableBeanFactory#initializeBean}
         * 销毁的逻辑主要是 step8-10 {@link DisposableBeanAdapter}
         1.step 1 创建bean实例
         {@link AbstractAutowireCapableBeanFactory#createBeanInstance}
         AbstractAutowireCapableBeanFactory#createBean#doCreateBean#createBeanInstance
         2.step 2 设置属性值
         {@link AbstractAutowireCapableBeanFactory#populateBean}
         AbstractAutowireCapableBeanFactory#createBean#doCreateBean#populateBean
         3.step 3 检查是否有实现Aware接口的bean，调用对于的方法
         {@link AbstractAutowireCapableBeanFactory#invokeAwareMethods}
         AbstractAutowireCapableBeanFactory#createBean#doCreateBean#initializeBean#invokeAwareMethods
         4.step 4 调用实现了BeanPostProcessor的前置处理方法(postProcessBeforeInitialization)
         {@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization}
         AbstractAutowireCapableBeanFactory#createBean#doCreateBean#initializeBean#applyBeanPostProcessorsBeforeInitialization
         5.step 5 调用InitializingBean的afterPropertiesSet方法
         {@link AbstractAutowireCapableBeanFactory#invokeInitMethods}
         AbstractAutowireCapableBeanFactory#createBean#doCreateBean#initializeBean#invokeInitMethods
         6.setp 6 对于xml方式配置的bean，调用自定义的init-method 方法 invokeCustomInitMethod
         {@link AbstractAutowireCapableBeanFactory#invokeCustomInitMethod}
         AbstractAutowireCapableBeanFactory#createBean#doCreateBean#initializeBean##invokeInitMethods#invokeCustomInitMethod
         7.step 7 调用实现了BeanPostProcessor的后置处理方法(postProcessAfterInitialization)
         {@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization}
         AbstractAutowireCapableBeanFactory#createBean#doCreateBean#initializeBean#applyBeanPostProcessorsAfterInitialization
         8.step 8 注册销毁回调方法，实现了DisposableBean接口的bean，或者xml方式配置的bean的destroy-method方法
         {@link AbstractAutowireCapableBeanFactory#registerDisposableBeanIfNecessary}
         9.step 9 使用bean
         10 step 10 调用实现了DisposableBean接口的bean的destroy方法
          {@link DisposableBeanAdapter#destroy}
         11 step 11 调用xml方式配置的bean的destroy-method方法
         {@link DisposableBeanAdapter#destroy}
         */
    }
}
