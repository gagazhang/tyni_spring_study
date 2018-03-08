package com.iflytek.spring.study.aop;

import org.aopalliance.aop.Advice;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpresstionPointcut pointcut = new AspectJExpresstionPointcut();

    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }

    public void setExpression(String expression){
        this.pointcut.setExpression(expression);
    }
}
