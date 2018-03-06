package com.iflytek.spring.study.test;

import com.iflytek.spring.study.BeanDefinition;
import com.iflytek.spring.study.PropertyValue;
import com.iflytek.spring.study.PropertyValues;
import com.iflytek.spring.study.factory.AutowireCapableBeanFactory;
import com.iflytek.spring.study.factory.BeanFactory;

import org.junit.Test;

/**
 * @author : wei
 * @date : 2018/3/5
 */
public class BeanFactoryTest {

    @Test
    public void testHelloService() throws Exception {
        //1. 初识化BeanFactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        //2. 定义BeanDefinition
        BeanDefinition beanDef = new BeanDefinition();
        beanDef.setBeanClassName("com.iflytek.spring.study.test.HelloService");

        //3. 定义Bean 的属性
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue propertyValue = new PropertyValue("name","zhangsan");
        propertyValues.addPropertyValue(propertyValue);
        beanDef.setPropertyValues(propertyValues);

        //4. 注册到beanfactory 中
        beanFactory.registerBeanDefinition("helloService",beanDef);

        //5. 获取Bean，开始使用
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
    }
}
