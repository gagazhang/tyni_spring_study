package com.iflytek.spring.study.factory;

import com.iflytek.spring.study.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : wei
 * @date : 2018/3/5
 */
public abstract class AbstractBeanFactory implements BeanFactory{

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String,BeanDefinition>();

    @Override
    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name,beanDefinition);
    }

    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
