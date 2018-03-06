package com.iflytek.spring.study.test.io;

import com.iflytek.spring.study.io.Resource;
import com.iflytek.spring.study.io.ResourcesLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : wei
 * @date : 2018/3/6
 */
public class ResourcesLoaderTest {

    @Test
    public void test() throws IOException {
        ResourcesLoader loader = new ResourcesLoader();
        Resource resource = loader.getResource("tinyioc.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }
}
