package com.iflytek.spring.study.factory;

import com.iflytek.spring.study.BeanDefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : wei`
 * @date : 2018/3/5
 */
public abstract class AbstractBeanFactory implements BeanFactory{

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String,BeanDefinition>();

    /**
     * 所有bean 的名字的集合，目前想到的作用是提前初始化使用
     */
    private List<String> beanDefinitionNames = new ArrayList<String>();

    @Override
    public Object getBean(String beanName) throws Exception{
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null){
            throw new IllegalArgumentException("No bean named "+ beanName + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if(bean == null){
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }

    /**
     * 预初识化单例模式的bean
     * @throws Exception
     */
    public void preInstantiateSingletons() throws Exception{
        for(Iterator it = this.beanDefinitionNames.iterator();it.hasNext();){
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }

    /**
     * 因为要支持beanReference 的方法， 所以不能在注册的时候初始化，因为注册的时候初始化，
     * 可能这个时候依赖的bean 还没有加载到beanDefinitionMap 中
     * @param name
     * @param beanDefinition
     * @throws Exception
     */
    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name,beanDefinition);
        beanDefinitionNames.add(name);
    }

    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
