package com.iflytek.spring.study.beans.factory;

import com.iflytek.spring.study.beans.BeanDefinition;
import com.iflytek.spring.study.beans.BeanPostProcessor;

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

    /**
     * 增加BeanPostProcessor 的集合,在这个集合中的每一个处理器，都需要处理Bean 创建以后，
     * before_init 和 post_init 的方法
     */
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    @Override
    public Object getBean(String beanName) throws Exception{
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null){
            throw new IllegalArgumentException("No bean named "+ beanName + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if(bean == null){
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean,beanName);
        }
        return bean;
    }

    private Object initializeBean(Object bean, String beanName) throws Exception {
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessBeforeInitialization(bean,beanName);
        }

        //TODO : call initialize method

        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            bean = beanPostProcessor.postProcessAfterInitialization(bean,beanName);
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
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name,beanDefinition);
        beanDefinitionNames.add(name);
    }

    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 根据超类，或者超集合，获取一批的Bean
     * @param type
     * @return
     * @throws Exception
     */
    public List<Object> getBeansForType(Class type) throws Exception{
        List<Object> beans = new ArrayList<Object>();
        for(String beanDefiniationName : beanDefinitionNames){
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefiniationName).getBeanClass())){
                beans.add(getBean(beanDefiniationName));
            }
        }
        return beans;
    }

    /**
     * 本次修改主要更改了函数的访问级别
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

    }
}
