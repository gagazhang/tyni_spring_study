package com.iflytek.spring.study.aop;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * 获取Pointcut
     * @return Pointcut
     */
    Pointcut getPointcut();
}
