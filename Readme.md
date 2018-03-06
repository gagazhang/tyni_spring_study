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

---