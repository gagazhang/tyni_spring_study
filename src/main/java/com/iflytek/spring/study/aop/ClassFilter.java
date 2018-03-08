package com.iflytek.spring.study.aop;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public interface ClassFilter {

    boolean matches(Class targetClass);
}
