package com.iflytek.spring.study.test.context;

import com.iflytek.spring.study.context.ApplicationContext;
import com.iflytek.spring.study.context.ClassPathXmlApplicationContext;
import com.iflytek.spring.study.test.HelloService;
import com.iflytek.spring.study.test.OutputService;
import org.junit.Test;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public class ApplicationContextTest {


    @Test
    public void testApplication() throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloService helloService = (HelloService) appContext.getBean("helloService");
        helloService.sayHello();

    }

//    @Test
    public void testPostBeanProcessor() throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc-postbeanprocessor.xml");
        HelloService helloWorldService = (HelloService) applicationContext.getBean("helloService");
        helloWorldService.sayHello();
    }
}
