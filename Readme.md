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

---

### step_3

---