package com.iflytek.spring.study.io;

import java.net.URL;

/**
 * @author : wei
 * @date : 2018/3/6
 */
public class ResourcesLoader {

    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource( location);
        return new UrlResource(resource);
    }
}
