package com.iflytek.spring.study.aop;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
