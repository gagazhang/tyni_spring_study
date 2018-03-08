package com.iflytek.spring.study.beans;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
