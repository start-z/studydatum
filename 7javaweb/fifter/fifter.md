# fifter过滤器

## 1简介

javaweb的三大组件

- filter过滤器   
- servlet程序  
- listen监听器





filter过滤器的作用是拦截

拦截请求常见的应用场景有:
1.权限检查
2、日记操作
3、事务管理
...等等



## 2filter的基本应用

![img](http://inis.inis1719.cn/202206071505167.png)

其实就是过滤

首先实现filter就先创建一个类实现接口filter 

然后重写里面的三个方法

其中

![img](http://inis.inis1719.cn/202206071505168.png)

dofilter是实现过滤的

![img](http://inis.inis1719.cn/202206071505169.png)

另外还需要在web配置文件中配置

![img](http://inis.inis1719.cn/202206071505170.png)

##               3filter的生命周期

![img](http://inis.inis1719.cn/202206071505171.png)

其实就是filter中的方法

- init方法
- dofilter方法是每次拦截到请求在执行
- destory是停止工程的时候销毁执行  



在这里提醒一下  filter一定要配置文件



## 4 filterconfig类

1获取配置文件

2 获取filtername

3获取servletcontext

![img](http://inis.inis1719.cn/202206071505172.png)

其中最常见的还是获取filter配置参数

配置参数需要在web文件中配置



## 5filter  chain 过滤器链

![img](http://inis.inis1719.cn/202206071505173.png)

过滤器其实每个都有filterchain 的参数

哪么多个filter执行时会出现什么呢

1会先执行前置代码  在执行后置代码

2 如果没有了filterchain.dofilter这个方法 后面的都不执行

3filter的执行顺序是按照你自己在web.xml文件配置中的先后顺序来执行的

4其实在多个filter执行的时候是都默认执行一个线程

5多个filter执行时其实他们使用的session都是同一个

5filter的拦截路径

其中filter有4

中拦截路径

1 精确匹配/文件名

2 目录匹配/目录名/*

3后缀名匹配*.html

![img](http://inis.inis1719.cn/202206071505174.png)

其中后缀名没有/