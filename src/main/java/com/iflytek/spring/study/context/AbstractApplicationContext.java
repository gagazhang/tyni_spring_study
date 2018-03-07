package com.iflytek.spring.study.context;

import com.iflytek.spring.study.beans.factory.AbstractBeanFactory;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public abstract class AbstractApplicationContext  implements ApplicationContext{

    protected  AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception{

    }

    @Override
    public Object getBean(String beanName) throws Exception {
        return beanFactory.getBean(beanName);
    }
}
