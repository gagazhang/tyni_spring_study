package com.iflytek.spring.study;

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
        //0. 初识化BeanFactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        //1. 定义BeanDefinition
        BeanDefinition beanDef = new BeanDefinition();
        beanDef.setBeanClassName("com.iflytek.spring.study.HelloService");

        //2. 注册到beanfactory 中
        beanFactory.registerBeanDefinition("helloService",beanDef);

        //3. 获取Bean，开始使用
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
    }
}
