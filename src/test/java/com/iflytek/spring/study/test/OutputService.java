package com.iflytek.spring.study.test;

import org.junit.Assert;

/**
 * @author : wei
 * @date : 2018/3/6
 */
public class OutputService {

    private HelloService helloService;

    public void output(String text){
        Assert.assertNotNull(helloService);
        System.out.println("hello : " + text);
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
