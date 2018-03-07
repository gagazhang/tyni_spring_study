package com.iflytek.spring.study.aop;

/**
 * 被代理的对象
 * @author : wei
 * @date : 2018/3/7
 */
public class TargetSource {

    private Class targetClass;

    private Object target;

    public TargetSource(Class targetClass, Object target) {
        this.targetClass = targetClass;
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }
}
