# Session

## 1简介

其中session是保存在服务端tomcat

常用于保存登录后的信息 



## 2session 的创建获取

### 1 session的创建

其中session的创建和多次创建都是一个方法

session.getsession

另外每一个session还具有唯一的sessionid

方法是session.getid

### 2判断是否是新的session

session.isnow

![img](http://inis.inis1719.cn/202206071500287.png)

### 3session的存取数据

在前面的学习中我们知道session是4大域对象之一 所以可以存储数据

使用的方法是setattbute  存

取  getatttbute  取

![img](http://inis.inis1719.cn/202206071500288.png)

 





## 3session的生活周期控制

![img](http://inis.inis1719.cn/202206071500289.png)

session的方法设置时间主要是

session.setmax  设置超时时间

session.getmax  获取超时时间

![img](http://inis.inis1719.cn/202206071500291.png)

其中在默认的设置超时时间是30分钟

如果你自己想修改超时的时间的话哪么就需要在web工程配置文件下自己设置

![img](http://inis.inis1719.cn/202206071500292.png)

另外如果你想设置某个session 的存活时间的话就需要使用setmax方法

在这里session的超时时间指的是停留的时间超过

在这里也可以想cookie那样设置值

如果是正数的话  就是在超时多少时间销毁

负数    永不超时

在cookie中可以设置0为存活时间

但是在session是不可以的

但是也同样可以立即销毁  需要使用到session.invalidate

![img](http://inis.inis1719.cn/202206071500293.png)

使用这个方法后就立即销毁

  3浏览器和session会话关联的内幕

![img](http://inis.inis1719.cn/202206071500294.png)

其实在每次创建session会话时都会	创建一个cookie值  而cookie的key永远都是sessionid， 而cookie的key对应的值就是session的id  所以在每次运行后都能找到对应的session

 而如果删除了那个cookie  哪么就会找不到session  所以会重新去创建session、