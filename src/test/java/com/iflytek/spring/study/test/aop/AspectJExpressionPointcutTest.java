package com.iflytek.spring.study.test.aop;

import com.iflytek.spring.study.aop.AspectJExpresstionPointcut;
import com.iflytek.spring.study.test.HelloService;
import com.iflytek.spring.study.test.HelloServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public class AspectJExpressionPointcutTest {


    public void testClassFilter() throws Exception{
        String expression = "execution(* com.iflytek.spring.study.*.*(..))";
        AspectJExpresstionPointcut aspectJExpresstionPointcut = new AspectJExpresstionPointcut();
        aspectJExpresstionPointcut.setExpression(expression);
        boolean matches = aspectJExpresstionPointcut.getClassFilter().matches(HelloService.class);
        Assert.assertTrue(matches);
    }

//    @Test
    public void testMethodInterceptor() throws Exception{
        String expression = "execution(* com.iflytek.spring.study.test.*.*(..))";
        AspectJExpresstionPointcut aspectJExpresstionPointcut = new AspectJExpresstionPointcut();
        aspectJExpresstionPointcut.setExpression(expression);
        boolean matches = aspectJExpresstionPointcut.getMethodMatcher().
                matches(HelloServiceImpl.class.getDeclaredMethod("sayHello"),HelloServiceImpl.class);
        Assert.assertTrue(matches);

    }

    @Test
    public void testFun(){
        System.out.println(int.class.isAssignableFrom(int.class));
    }

}
