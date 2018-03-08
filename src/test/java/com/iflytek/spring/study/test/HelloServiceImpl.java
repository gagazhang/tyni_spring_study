package com.iflytek.spring.study.test;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public class HelloServiceImpl implements HelloService{

    private String name;

    private OutputServiceImpl outputService;

    @Override
    public void sayHello(){
        outputService.output(name);
    }
}
