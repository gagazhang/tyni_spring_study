package com.iflytek.spring.study.factory;

import com.iflytek.spring.study.BeanDefinition;
import com.iflytek.spring.study.BeanReference;
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
        beanDefinition.setBean(bean);
        applyPropertyValues(bean,beanDefinition);
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        for(PropertyValue proValue : beanDefinition.getPropertyValues().getPropertyValues()){
            Field declareField = bean.getClass().getDeclaredField(proValue.getName());
            declareField.setAccessible(true);
            Object value = proValue.getValue();
            if(value instanceof BeanReference){
                BeanReference reference = (BeanReference) value;
                value = getBean(reference.getName());
            }
            declareField.set(bean,value);
        }
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }
}
