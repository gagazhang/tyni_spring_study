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

从step_6 往后，主要是探讨spring 中 AOP的实现方式，前面的部分主要探讨IOC的实现方式。

step_6 主要引入两个框架，aopalliance 和 JDK 的danymicProxy 方式，因此pom 文件中引入了aopalliance 依赖，代码如下：

```
<dependency>
      <groupId>aopalliance</groupId>
      <artifactId>aopalliance</artifactId>
      <version>1.0</version>
</dependency>
```

step_6 的核心类是

```
JdkDynamicAopProxy
```
JdkDynamicAopProxy 主要的提供一个能力，使用Proxy.newProxyInstance 的方法提供一个代理对象。代理对象的使用流程如下：
1. 调用代理对象的相关方法，本例中为HelloService 中sayHello()方法
2. 回调到InvocationHandler.invoke() 方法
3. 在InvocationHandler.invoke() 中方法中，获取之前封装的MethodInterceptor.invoke()方法，该方法接受一个MethodInvocation 的对象。
4. 本例中，跳转到TimerInterceptor.invoke()方法
5. 跳转到ReflectiveMethodInvocation.preceed()方法进行处理
6. 返回结果，结束

---

### step_7

step_7 按照计划，本来是是要继续更新AOP的相关内容，但是整理了原作者的思路以后，感觉还是step_7 应该分两步来实施，本章节中重点改造了AbstractBeanFactory and AbstractApplicationContext ，理解了这一部分，对后面AspectJ 的学习打下了基础。

本节中重点添加的模块

```
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
```
BeanPostProcessor 主要提供两个接口，简单说就是在before bean init 和 after bean init 中添加相关的代码，这一部分和AOP 无关。因此可以不需要关心AOP 包下面的内容的修改

BeanPostProcessor 在本节中的一个实现类为：

```
BeanInitializationLogger
```

该类什么都没做，就是println() 了两行日志，详细可以参考代码，实现比较简单。

重点单元测试主要修改了

```
ApplicationContextTest
```
配合测试的文件增加了

```
tinyioc-postbeanprocessor.xml
```

>**Tip** , 在原作者的实现中，xml 文件中bean 定义的name, 改成了spring中熟悉 id, 这点要注意，不然排查起代码来会花费写时间

---

### step_8

step_8 引入了aspectj 框架实现的AOP，理解本章节的内容，需要对aspectj 有一定的了解。

aspectj 的三个基本概念

- Join point : 定义了新代码插入的位置，程序里面用到了execution，即函数执行内部。
- pointcut :  提供了一种开发者能够选择自己需要的Joinpoint 的方法，在本例中aspectj 表达式来表述
- advise :代码插入的方式，有before,after, around

在本例中，同时结合了这三功能的类是：

```
AspectJExpressionPointcutAdvisor
```

其中AspectJExpresstionPointcut 是Pointcut，主要通过aspectj 表达式来构造，
Advice 是TimerInterceptor，应该是采用aroud 的方式，即在before 和after 地方都插入了代码。

AspectJExpressionPointcutAdvisor 在xml 文件中的定义如下：

```
<bean id="aspectjAspect" class="com.iflytek.spring.study.aop.AspectJExpressionPointcutAdvisor">
        <property name="advice" ref="timeInterceptor"></property>
        <property name="expression" value="execution(* com.iflytek.spring.study.test.*.*(..))"></property>
</bean>
```

需要对表达式做一些说明
- execution ：join point 类型，execution 代表函数执行内容，例如Log.e(),代表在e() 函数内容插入代码
- 第一个* ：代表任意的返回类型
- com.iflytek.spring.study.test.*.*: * 代表任意的匹配，表示这个表下面的类
- (..):任意类型的参数




>**tip** ,在本例中发现一个问题，就是step_8 的工程，如果同时出现了AOP 和循环引用，会发现AOP失效了，究其原因，就是在Bean初始化的过程中，循环引用的在XML文件中定义稍后的 bean, 拿到了一个原始的Bean，而不是代理的Bean，所以无法使用AOP。

> **例如**：step_8 中，打开tinyioc.xml 文件中 outputService 的ref 定义，会发现outputService 中持有的helloService 是一个代理对象，而helloService 中却持有 outputService 的原始对象，因此调用helloService -> outputService的方法时，只有helloService 的方法可以实现AOP，而outputService 中的AOP并不生效。


### step_9

### over




