package com.iflytek.spring.study.context;

import com.iflytek.spring.study.beans.BeanDefinition;
import com.iflytek.spring.study.beans.factory.AbstractBeanFactory;
import com.iflytek.spring.study.beans.factory.AutowireCapableBeanFactory;
import com.iflytek.spring.study.beans.io.ResourcesLoader;
import com.iflytek.spring.study.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(AbstractBeanFactory beanFactory, String configLocation) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(new AutowireCapableBeanFactory(),configLocation);
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourcesLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for(Map.Entry<String,BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()){
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(),beanDefinitionEntry.getValue());
        }
    }
}
