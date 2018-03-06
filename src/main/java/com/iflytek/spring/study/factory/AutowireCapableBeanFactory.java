package com.iflytek.spring.study.factory;

import com.iflytek.spring.study.BeanDefinition;
import com.iflytek.spring.study.PropertyValue;

import java.lang.reflect.Field;

/**
 * @author : wei
 * @date : 2018/3/5
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory{



    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        applyPropertyValues(bean,beanDefinition);
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException {
        for(PropertyValue proValue : beanDefinition.getPropertyValues().getPropertyValues()){
            Field declareField = bean.getClass().getDeclaredField(proValue.getName());
            declareField.setAccessible(true);
            declareField.set(bean,proValue.getValue());
        }
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }
}
