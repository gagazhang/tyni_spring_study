package com.iflytek.spring.study.test.context;

import com.iflytek.spring.study.context.ApplicationContext;
import com.iflytek.spring.study.context.ClassPathXmlApplicationContext;
import com.iflytek.spring.study.test.HelloService;
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
}
