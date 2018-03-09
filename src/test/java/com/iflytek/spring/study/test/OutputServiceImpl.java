package com.iflytek.spring.study.test;

import org.junit.Assert;

/**
 * @author : wei
 * @date : 2018/3/6
 */
public class OutputServiceImpl implements OutputService{

    private HelloService helloService;

    @Override
    public void output(String text){
        System.out.println("hello : " + text);
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
