package com.iflytek.spring.study.factory;

import com.iflytek.spring.study.BeanDefinition;

/**
 * @author : wei
 * @date : 2018/3/5
 */
public interface BeanFactory {

    Object getBean(String beanName) throws Exception;

    void registerBeanDefinition(String bean, BeanDefinition beanDefinition) throws Exception;

}
