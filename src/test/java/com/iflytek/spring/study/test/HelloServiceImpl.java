package com.iflytek.spring.study.test;

/**
 * @author : wei
 * @date : 2018/3/7
 */
public class HelloServiceImpl implements HelloService{

    private String name;

    private OutputService outputService;

    @Override
    public void sayHello(){
        outputService.output(name);
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
