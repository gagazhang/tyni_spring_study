package com.iflytek.spring.study.beans;

/**
 * @author : wei
 * @date : 2018/3/6
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
