# 													servlet

## 1简介

1. servlet 是JavaEE规范之一 。规范就是接口。
2. servlet 是JavaWeb三大组件之一- 。三大组件分别是: Servlet 程序、Flter 过滤器、Lstener监听器。
3. Servlet 是运行在服务器上的一个java小程序,它可以接收客户端发送过来的请求，并响应数据给客户端。



## 2手动实现servlet

实现步骤如下

1. 编写类实现servlet接口
2. 实现servlet方法（doGet doPost）

1. 去web.xml中去配置servlet的访问地址



## 3servlet的生命周期

创建（init）-执行（doGet  doPost）-销毁(destroy)

整个servlet的继承实现

![img](http://inis.inis1719.cn/202206071450691.png)



## 4 常见的servlet类

### 4.1servletConfig

​    servletConfig类是servlet的配置信息类

1. 可以获取Servlet 程序的别名servlet-name的值
2. 获取初始化参数init-param
3. 获取ServletContext对象
   

### 4.2   servletContext类

#### 简介

1. ServletContext 是-一个个接口,它表示Servlet.上下文对象。
2. 一个web工程，只有一-个ServletContext对象实例。
3. ServletContext 对象是-一个域对象。



#### **什么是域对象?**

域对象，是可以像Map一样存取数据的对象,叫域对象。
这里的域指的是存取数据的操作范围。

| 操作 |   Map    |       域对象       |
| ---- | :------: | :----------------: |
| 存   |  put()   |   setAttribute()   |
| 获取 |  get()   |   getAttribute(    |
| 移除 | remove() | removeAttribute(); |




####  获取text的上下文内容

#####    1 需要去配置中设置param 

![img](http://inis.inis1719.cn/202206071450692.png)

![img](http://inis.inis1719.cn/202206071450694.png)

##### 2   获取当前的路径

 

![img](http://inis.inis1719.cn/202206071450695.png)





#### 总结 

 就是获取配置文本内容

其中包括 获取配置信息的内容

和同一个web工程下文件的路径

还有就是可以存储数据    但是如果你一直在运行 那么你存储的数据就会一直存在

相反 如果  你储存的数据停止运行 后   哪么你查询的数据就会消失、







## 5http协议

### 简介

http协议就是网络双方需要共同遵守的一种规则。



### 请求协议

####   get 请求

![img](http://inis.inis1719.cn/202206071450696.png)

####  post请求

![img](http://inis.inis1719.cn/202206071450697.png)

常用的请求头

![img](http://inis.inis1719.cn/202206071450698.png)





### 响应协议

![img](http://inis.inis1719.cn/202206071450699.png)





#### 常见响应码

200  访问成功

302  请求重定向

404 资源不存在

50  代码内部错误

  MIME数据类型

mime数据类型就是http协议中的数据类型

![img](http://inis.inis1719.cn/202206071450700.png)

### httpservletrrequest类


每次只要有请求进入Tomcat服务器，iomcat 服务器就会把请求过来的HTTP协议信息解析好封装到Request对象中。
然后传递到service方法( doGet和doPost)中给我们使用。我们可以通过HttpServletRequest对象，获取到所有请求的
信息。



![img](http://inis.inis1719.cn/202206071450701.png)

获取参数的时候使用getparameter这个方法

另外获取post方法是中文会乱码

我们只需要在方法中设置字符集

![img](http://inis.inis1719.cn/202206071450702.png)

​         



### 请求转发

简单的来说请求转发就是2个servet程序互相运行

![img](http://inis.inis1719.cn/202206071450703.png)

#### 第一个servlet程序

![img](http://inis.inis1719.cn/202206071450704.png)

![img](http://inis.inis1719.cn/202206071450705.png)

#### 第二个servlet程序代码

![img](http://inis.inis1719.cn/202206071450706.png)





### httpservletresponse类

HttpServletResponse 和HttpServletRequest的异同。

1. HttpServletResponse 类和HttpServletRequest类一样。 每次请求进来，Tomcat 服务器都会创建一个Response对
   类和HttpServletRequest类。每次请求进来，Tomcat服务器都会创建一个Response。
2. HttpServletRequest 表示请求过来的信息, HtpServletResponse 表示所有响应的信息，
3. 我们如果需要设置返回给客户端的信息，都可以通过HttpServletResponse对象来进行设置。
   

response传文本给客户端

![img](http://inis.inis1719.cn/202206071450707.png)

在这里如果输入的是中文的话 会出现乱码

在request中我们学了代码去解决  但是光是那个还不够用

因为设置的是服务端的编码  我们还要设置一下客户端的编码

![img](http://inis.inis1719.cn/202206071450708.png)

在解决乱码上还可以有另一种的方法  但是必须在输出流之前使用

![img](http://inis.inis1719.cn/202206071450709.png)

####   

#### 请求重定向

![img](http://inis.inis1719.cn/202206071450710.png)

就是客户端不知道servlet1不能用了 然后在解析jservlet1的结果在新地址中在去查询   然后输出

##### 代码servlet1

![img](http://inis.inis1719.cn/202206071450711.png)

##### 代码servlet2

![img](http://inis.inis1719.cn/202206071450712.png)





