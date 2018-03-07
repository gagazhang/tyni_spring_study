package com.iflytek.spring.study.test.beans.xml;

import com.iflytek.spring.study.beans.BeanDefinition;
import com.iflytek.spring.study.beans.io.ResourcesLoader;
import com.iflytek.spring.study.beans.xml.XmlBeanDefinitionReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author : wei
 * @date : 2018/3/6
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void test() throws Exception {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourcesLoader());
        reader.loadBeanDefinitions("tinyioc.xml");
        Map<String,BeanDefinition> registry = reader.getRegistry();
        Assert.assertTrue(registry.size() > 0);
    }
}
