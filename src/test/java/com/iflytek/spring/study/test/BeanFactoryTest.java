package com.iflytek.spring.study.test;

import com.iflytek.spring.study.beans.BeanDefinition;
import com.iflytek.spring.study.beans.factory.AbstractBeanFactory;
import com.iflytek.spring.study.beans.factory.AutowireCapableBeanFactory;
import com.iflytek.spring.study.beans.factory.BeanFactory;
import com.iflytek.spring.study.beans.io.ResourcesLoader;
import com.iflytek.spring.study.beans.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/**
 * @author : wei
 * @date : 2018/3/5
 */
public class BeanFactoryTest {

    /**
     * 测试beanFactory 的延迟初始化
     */
//    @Test
    public void testLazy() throws Exception {
        //1. 从配置文件中读取bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourcesLoader());
        reader.loadBeanDefinitions("tinyioc.xml");

        //2. 初识化beanFactory 并注册Bean
        AutowireCapableBeanFactory beanFactory = new AutowireCapableBeanFactory();
        Map<String,BeanDefinition> registryMap = reader.getRegistry();
        for(Map.Entry<String,BeanDefinition> entry : registryMap.entrySet()){
            beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
        }

        //3. 使用bean
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();

    }

    /**
     * 测试预初始化的工作
     * @throws Exception
     */
    @Test
    public void testPreInstance() throws Exception {
        //1. 读取配置文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourcesLoader());
        reader.loadBeanDefinitions("tinyioc.xml");

        //2. 初识化BeanFactory 并且注册bean
        AbstractBeanFactory factory = new AutowireCapableBeanFactory();
        for(Map.Entry<String,BeanDefinition> entry:reader.getRegistry().entrySet()){
            factory.registerBeanDefinition(entry.getKey(),entry.getValue());
        }

        //3. 初始化bean
        factory.preInstantiateSingletons();

        //4. 获取bean 并且使用
        HelloService helloService = (HelloService) factory.getBean("helloService");
        helloService.sayHello();
    }

}
