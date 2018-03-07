package com.iflytek.spring.study.test.aop;

import com.iflytek.spring.study.aop.AdviseSupport;
import com.iflytek.spring.study.aop.JdkDynamicAopProxy;
import com.iflytek.spring.study.aop.TargetSource;
import com.iflytek.spring.study.context.ApplicationContext;
import com.iflytek.spring.study.context.ClassPathXmlApplicationContext;
import com.iflytek.spring.study.test.HelloService;
import org.junit.Test;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public class JdkDynamicAopProxyTest {

    @Test
    public void testInterceptor() throws Exception{
        //----------HelloService without aop----------------
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        helloService.sayHello();

        //-------------HelloService with aop --------------
        //1. 设置被代理的对象（JoinPoint)
        AdviseSupport adviseSupport = new AdviseSupport();
        TargetSource targetSource = new TargetSource(HelloService.class,helloService);
        adviseSupport.setTargetSource(targetSource);

        //2. 设置拦截器
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        adviseSupport.setMethodInterceptor(timerInterceptor);

        //3. 创建代理（Proxy)
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(adviseSupport);
        HelloService helloServiceProxy  = (HelloService) jdkDynamicAopProxy.getProxy();

        //4. 基于Aop 的封装
        helloServiceProxy.sayHello();


    }
}
