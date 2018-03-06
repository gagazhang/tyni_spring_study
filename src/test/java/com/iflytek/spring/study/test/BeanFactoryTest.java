package com.iflytek.spring.study.test;

import com.iflytek.spring.study.BeanDefinition;
import com.iflytek.spring.study.PropertyValue;
import com.iflytek.spring.study.PropertyValues;
import com.iflytek.spring.study.factory.AutowireCapableBeanFactory;
import com.iflytek.spring.study.factory.BeanFactory;

import com.iflytek.spring.study.io.ResourcesLoader;
import com.iflytek.spring.study.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/**
 * @author : wei
 * @date : 2018/3/5
 */
public class BeanFactoryTest {

    @Test
    public void testHelloService() throws Exception {
        //1. 读取配置文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourcesLoader());
        reader.loadBeanDefinitions("tinyioc.xml");

        //2. 初识化beanFactory 并且注册bean
        BeanFactory beanFactory = new AutowireCapableBeanFactory() ;
        for(Map.Entry<String,BeanDefinition> beanDefinitionEntry: reader.getRegistry().entrySet()){
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }

        //3.获取bean 并且使用
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();


    }
}
