package com.iflytek.spring.study;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wei
 * @date : 2018/3/5
 */
public class PropertyValues {

    private List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();

    /**
     * 构造函数
     */
    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue propertyValue){
        //TODO 对于重复的propertyValue 无法判断
        propertyValues.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues(){
        return this.propertyValues;
    }
}
