package com.iflytek.spring.study;

/**
 * @author : wei
 * @date : 2018/3/5
 */
public class BeanDefinition {

    /**
     * 被封装的bean
     */
    private Object bean;

    /**
     * bean class
     */
    private Class beanClass;

    /**
     * bean class name
     */
    private String beanClassName;

    /**
     * bean 属性
     */
    private PropertyValues propertyValues;

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public BeanDefinition() {
    }

    /**
     * 获取BeanClass
     * @return
     */
    public Class getBeanClass(){
        return beanClass;
    }

    /**
     * Set BeanClass
     * @param beanClass
     */
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
