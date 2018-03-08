package com.iflytek.spring.study.test;

import com.iflytek.spring.study.beans.BeanPostProcessor;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public class BeanInitializationLogger implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        System.out.println("Initialize bean " + beanName + " start!");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        System.out.println("Initialize bean " + beanName + " end!");
        return bean;
    }
}
