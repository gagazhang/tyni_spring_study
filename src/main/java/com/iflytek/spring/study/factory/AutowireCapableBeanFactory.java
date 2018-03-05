package com.iflytek.spring.study.factory;

import com.iflytek.spring.study.BeanDefinition;

/**
 * @author : wei
 * @date : 2018/3/5
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory{



    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        return bean;
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }
}
