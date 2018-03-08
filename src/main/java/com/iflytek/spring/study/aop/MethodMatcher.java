package com.iflytek.spring.study.aop;

import java.lang.reflect.Method;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);
}
