package com.iflytek.spring.study.aop;

import org.aopalliance.aop.Advice;

/**
 *
 * @author : wei
 * @date : 2018/3/7
 */
public interface Advisor {

    /**
     * 提供一种封装，返回Advice,Advice 定义了切入的地方，before ,after, around
     * @return
     */
    Advice getAdvice();
}
