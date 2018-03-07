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
这个类在注册BeanDefinition 的时候，通过BeanDefinition 找到Class信息，通过反射的方式的，newInstance() 了一个对象，放到了Map<String,BeanDefinition> 中。使用ConcurrentHashMap 保证多线程的访问的安全性。

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

step_4 主要实现了inject bean to bean ，示例中使用了tinyioc.xml 的配置文件，其中配置了两个bean, helloService 和 outputService, 并且这两个bean 相互依赖。

step_4 和 step_3 一样，同样需要两个步骤，读取资源和beanFactory 注册。

但是==不同点==如下：step_3 中 beanFactory 是在bean 注册时初始化，但是从读取的资源注册到beanFactory 是一个顺序的过程，例如示例中先注册 outputService, 后注册 helloService(因为配置文件定义的顺序)，这是outputService 的 实例化依赖 helloService ,但是这是，helloService 的BeanDefinition 还未加入到 BeanDefinition 的Map中，所以初始化失败。

==因此==，step_4 最重要的变化是使用了延迟初始化，即在getBean 的函数中进行了Bean的初始化。

step_4 的重要演进有两点，1. 新增了类BeanReference ，2. BeanFactory 的Bean的初始化，放在了getBean 的 函数中，同时提供预初始化机制，即在bean 全部注册完成以后，使用preInstantiateSingletons 的方法，预初始化单例bean.


```
BeanReference
```

简单封装了name, Object bean两个属性。

---

### step_5

熟悉的ApplicationContext 终于来了，本次主要添加了一个接口：

```
ApplicationContext
```
一个抽象类

```
AbstractApplicationContext
```
和一个实现类 

```
ClassPathXmlApplicationContext
```


BeanFactory 和 ApplicationContext 的关注点不同，BeanFactory 只剩下一个接口，getBean(String beanName),ApplicationContext 使用代理模式代理了BeanFactory ，并且增加了资源管理相关的工作。

经过重构后的代码，就看到了spring 熟悉的 Application.getBean(beanName) 的使用模式了。

---

### step_6

---


