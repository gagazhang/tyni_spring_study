package com.iflytek.spring.study.beans.xml;

import com.iflytek.spring.study.beans.AbstractBeanDefinitionReader;
import com.iflytek.spring.study.beans.BeanDefinition;
import com.iflytek.spring.study.beans.BeanReference;
import com.iflytek.spring.study.beans.PropertyValue;
import com.iflytek.spring.study.beans.io.ResourcesLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : wei
 * @date : 2018/3/6
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{


    public XmlBeanDefinitionReader(ResourcesLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream  = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinition(inputStream);
    }

    /**
     * 加载Bean 定义的
     * @param inputStream
     */
    private void doLoadBeanDefinition(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);

        //解析Bean
        registerBeanDefinition(doc);
        inputStream.close();
    }

    /**
     * 解析Bean
     * @param doc
     */
    private void registerBeanDefinition(Document doc) {
        Element root = doc.getDocumentElement();

        parseBeanDefinition(root);
    }

    /**
     * 解析Root 节点
     * @param root
     */
    private void parseBeanDefinition(Element root) {
        NodeList nl = root.getChildNodes();
        for(int i = 0 ; i < nl.getLength(); i++){
            Node node = nl.item(i);
            if(node instanceof Element ){
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    /**
     * 解析 bean 节点
     * @param ele
     */
    private void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("id");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele,beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name,beanDefinition);

    }

    /**
     * 处理bean 节点的属性信息
     * @param ele
     * @param beanDefinition
     */
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for(int i = 0 ; i < propertyNode.getLength(); i ++){
            Node node = propertyNode.item(i);
            if(node instanceof Element){
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if(value != null && value.length() > 0){
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
                }
                else{
                    String ref = propertyEle.getAttribute("ref");
                    if(ref == null || ref.length() == 0){
                        throw new IllegalArgumentException("Configuration problem: <property> element " +
                                " must specify a ref or value");
                    }
                    BeanReference reference = new BeanReference();
                    reference.setName(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,reference));
                }
            }
        }
    }
}
