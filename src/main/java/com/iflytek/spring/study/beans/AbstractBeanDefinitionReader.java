package com.iflytek.spring.study.beans;

import com.iflytek.spring.study.beans.io.ResourcesLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wei
 * @date : 2018/3/6
 */
public abstract  class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String,BeanDefinition> registry;

    private ResourcesLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourcesLoader resourceLoader) {
        this.registry = new HashMap<String, BeanDefinition>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourcesLoader getResourceLoader() {
        return resourceLoader;
    }
}
