package com.iflytek.spring.study.aop;

import com.iflytek.spring.study.beans.factory.BeanFactory;

/**
 * @author : wei
 * @date : 2018/3/8
 */
public interface BeanFactoryAware {

    /**
     * 注入BeanFactory 的实例
     * @param beanFactory
     * @throws Exception
     */
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
