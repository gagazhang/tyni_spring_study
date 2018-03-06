package com.iflytek.spring.study.test;

/**
 * @author : wei
 * @date : 2018/3/5
 */
public class HelloService {

    private String name;

    private OutputService outputService;

    public void sayHello(){
        outputService.output(name);
    }

}
