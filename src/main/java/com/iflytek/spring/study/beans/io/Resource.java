package com.iflytek.spring.study.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : wei
 * @date : 2018/3/6
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
