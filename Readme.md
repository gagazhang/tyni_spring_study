## tyni_spring_study

### step_1

这一部分主要对应于原工程里面的step_1 和 step_2 的工程，主要有两部分。


```
BeanDefinition
```
这个类主要做了对bean(一个Object)的简单封装，同时保存了class,className 的信息，在设置beanClassName 的时候，通过Class.forName 找到了响应的Class 信息，并且设置到class 的属性中


```
BeanFactory
```
抽象为了接口，真正工作的是

```
AutowireCapableBeanFactory
```
这个类在注册BeanDefinition 的时候，通过BeanDefinition 找到Class信息，通过反射的方式的，newInstance() 了一个对象，放到了Map<String,BeanDefinition> 中。使用CurrentHashMap 保证多线程的访问的安全性。

---
### step_2

step_2 的更新，主要是为bean 增加了添加属性的功能

```
PropertyValue
```
对Bean的属性做了封装，主要是属性名和属性对应的值（Object）


```
PropertyValues
```
使用List接口，对PropertyValue做了一次封装


```
AutowireCapableBeanFactory
```
增加了设置属性的功能，主要是通过反射拿到Field 的信息，然后调用field.set(instance, value) 的方法，为相应的属性设置值



---

### step_3

本次更细的内容，主要是增加xml的解析，从配置文件中读取bean的信息，然后再注册到BeanFactory 中。

#### 步骤1：解析XML文件，然后把bean 收集到一个map 中

主要新增的类分为以下2类：

- resource 类
- reader 类


> ==resource相关==

```
Resource
```
抽象一种资源，主要的功能是可以提供输入流：InputStream,实现类是

```
UrlResource
```
这里面使用了URL类，代表了本地和网络的资源，只不过是使用的协议不同（例如：file、http 等）


```
ResourcesLoader
```
这是资源加载器，主要的输入是路径，输出是Resource,这个类似一个工具类，前三个类的简单封装，本身和tiny_spring 没什么关系，对于加载任何的资源，都是 通用的。


> ==reader类==

```
BeanDefinitionReader
```
这也是一个工具接口，定义一个接口loadBeanDefinitions(String location),主要的参数是load 的位置


```
AbstractBeanDefinitionReader
```
BeanDefinitionReader 的抽象实现，里面包含了**步骤1**说明map 信息，加载的工程使用了ResourceLoader。


```
XmlBeanDefinitionReader
```
真正解析xml 文件的reader，里面封装了xml 操作的流程。




#### 步骤2：遍历之前收集的BeanDefinitionMap,然后注册到BeanFactory 中

这个过程就比较简单，基本上之前的操作都不需要改，主要是遍历上一个步骤的Map，全部注册到BeanFactory中就行了


---

### step_4

---

