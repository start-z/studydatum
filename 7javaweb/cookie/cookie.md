# Cookie

## 1简介

cookie就是通知客户端存放键值对  其中cookie不大于4kb

其中cookie 是保存在客户端浏览器

session是保存在服务端  tomcat

​     

##  2 cookie的创建

### 1创建流程图

![img](http://inis.inis1719.cn/202206071458291.png)

### 2创建步骤（其实在这里的servlet其实是继承了Baseservlet）

1. ​       新建cookie类存放键值对
2. ​		 使用response.addcookie加载cookie  

### 3  输出

![img](http://inis.inis1719.cn/202206071458292.png)

在这里需要注意一点 当你写cookie的servlet程序时  由于时表单元素  所以重写的时dopost方法

![img](http://inis.inis1719.cn/202206071458293.png)

中文乱码在继承的baseservlet中书写



## 3 cookie的获取

其实cookie 的获取只需要一行代码

request.getcookie

![img](http://inis.inis1719.cn/202206071458294.png)

## 4cookie的修改

![img](http://inis.inis1719.cn/202206071458295.png)

### 同名覆盖

![img](http://inis.inis1719.cn/202206071458297.png)

### 查找cookie并修改

最后打印

![img](http://inis.inis1719.cn/202206071458298.png)

##    5cookie的存活时间（生命控制）

![img](http://inis.inis1719.cn/202206071458299.png)

可以设值cookie的存活时间    默认cookie 的存活时间是-1

正数的时候是到你设定 的时间删除

负数的时候后是关闭浏览器后删除  

为0的时候是立即删除

cookie.setMaxAge(0/-1/1);

其中是使用cookie中的setmaxage方法





## 6cookie 的有效路径过滤

 其实这里就是工程路径的显示而已 但由于安全问题 所以谷歌不会显示

cookie.setpath这个方式是设置cookie的地址

cookie  A

http：//地址/端口号/工程名

cookie  B

http：//地址/端口号/工程名/abc

如果输入的是a这个地址

哪么cookie 发送的是cookiea的值

如果发送的是b 的这个值

哪么cookie 发送的是cookie   A 和cookieB的值



##   7cookie的免登录

![img](http://inis.inis1719.cn/202206071458336.png)

![img](http://inis.inis1719.cn/202206071458300.png)

其实就是设置cookie的存活时间 让他保存的时间更久

其次设置想要默认的表单修改value

下次就会自动显示