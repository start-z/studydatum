# Linux

## 	1简介

linux其实就是一种操作系统    但是windows 也是一种操作系统

那为什么linux 的系统不出名呢

因为linux 的图形化界面没有windows 的好 但是他在性能方面比window操作系统好

linux 常用于企业级服务器   而window适用于 个人

其中linux系统是开源的  并且免费的   开源的意思就是代码是公布的 而window就不是





linux 的发行版本

其实linux没有版本之说  因为代码是开源的  所以都是在代码上进行添加

现在市面上常见的就是 ubuntu（乌班图）redhat（红帽） centos

我们将在虚拟机上安装linux系统

安装完成后 linux系统没有windows系统漂亮

## 2inux目录介绍

- 在这节我了解到了linux的 的一些基本设置
- root：系统管理员目录
- bin  or  /usr/bin：存放系统预装程序   
- usr :存放的都是系统可执行命令 例如redis
- usr/local/bin:存放用户自己的可执行文件
- lib： 存放系统基本的动态连接分享库  
- boot ：存放启动linux核心的文件
- dev： 存放linux 外部设备的文件
- etc：存放所有系统管理所需要的**配置文件**



## 3Linux基本指令

### 3.1vi和vim   编译器

 vi和vim是一种linux的编译器  功能和windows系统中的记事本  tex差不多  其中vi和vim 的区别是vi是早期的编译器   而vim是在vi 的基础上进行改进 是增强版

​                       vi和vim 的使用

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C9a85388ebc434a27be5021c2c7ac91dd%5Cclipboard.png)

其中vi分为三种模式  一般 只能查看   进入编辑模式输入i  

   编辑    能编辑修改  不能保存   退出编辑模式按esc

  命令行    输入指令  保存退出  进入命令行模式输入shirt+：



里面有3个指令   

| 保存退出 | 不保存退出 | 退出 |
| -------- | ---------- | ---- |
| wq       | q！        | q    |



  vi和 vim  常见的快捷键

|    快捷键    |                 描述                  |
| :----------: | :-----------------------------------: |
|      yy      |  复制  数字+yy多行复制 在一般模式下   |
|      dd      |     数字+dd多行删除  在一般模式下     |
|      u       | 撤销  回到上一次的操作   在一般模式下 |
|  / +关键字   |      查找关键字   在命令行模式下      |
| set+ nb/nonu |       显示 行号 在命令行模式下        |



### 3.2帮助命令   

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C556fffb461574693ba2f02e76d7abcf8%5Cclipboard.png)

```
man + 命令句    其中man时帮助手册  是linux 自带的  按q退出

help + 命令句   而help显示的是开发人员写在里面的注释
```





### 3.3操作目录



```
pwd 查看当前的目录
```

```
ls 查看当前的子目录
```

```
ls  -l  以下拉显示
```

```
ls   -a 显示所有的目录信息 用.表示
```



```
切换目录  cd    

.  表示本级目录

..   表示上级目录
```

```
pwd  查看当前目录
```



```
mkdir   创建目录  但是不可以一次性创建多个目录
```

```
mkdir   -p 可以一次性创建多个目录
```

```
删除空目录   rmdir 目录名
```



```
touch  创建文件  可以创建多个文件 用空格隔开
```



```
删除文件或者目录  rm

rm 删除目录  有提示

rm -f   删除目录  无提示

rm-r      强制递归删除目录   有提示

rm-rf   强制递归删除目录     无提示
```



```
mv  源目录    移动目录    移动目录
```

```
cat  文件名 查看文件内容
```

```
less 文件名 分页查看文件内容
```

```
echo   可以输出环境变量和常量
```

在脚本中经常出现

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Ca8c2ab505d0545ae8b0cd4b60311c593%5Cclipboard.png)

  查看命令指令  >  文件名

简单大的意思就是将 查看的命令结果输出到文件之中

如果文件不存在的话哪么就会创建一个新的文件

​     linux的时间和日期操作指令

```

date  显示当前时间

date   ”+%y“  显示年

​              m  显示月

​              d  显示日期

 date   -s  设置 当前的时间
```

```
cal  查看本月日历
```

​      

### 3.4过滤搜索指令

find  搜索指令

```
find  -size  文件大小   按照文件大小搜索  +表示大于  -表示小于  =表示等于
```

```
find  -user  用户名   搜索用户名下的文件及目录
```

```
locate  关键字      效率高 但是 只是搜索当前树下的文件
```



grep  搜索过滤命令

```
查看命令  | grep  过滤条件
```

```
查找命令  | grep   过滤条件
```

 

### 3.5压缩解压指令



| 文件后缀名 | 解压                                                         | 压缩                                    |
| ---------- | ------------------------------------------------------------ | --------------------------------------- |
| .gz        | gunzip  文件名    解压                                       | gzip  文件名       压缩                 |
| .zip       | unzip   解压的文件  解压当前目录   \|\|    unzip  解压的文件 -d  文件路径  解压到指定的目录 | zip  压缩名  文件      压缩             |
| /          | tar   -zxvf     压缩名   压缩文件   解压                     后面加上-C  是解压到指定的目录 | tar   -zcvf   压缩名   压缩文件    压缩 |



### 3.6文件、目录与组

每一个用户至少都属于一个组

文件或者目录也必须属于一个组   且只能是一个组

在文件或者目录看来    用户分为三类

所有者 ：就是创建者  列如  root

同组用户：就是和用户再同一个组  （访问权限）

其他组 ：既不是同一个组 也不是所有者





```
 查看文件或者目录的 所有组和所有者

ls -l  目录或者文件   不输入则是默认当前文件或者目录
```

```
修改文件的所有者

chown  新的所有者 文件或者目录名         只是本身

chown  新的所有者：新的组   文件或者目录名       只是本身

chown -R  新的所有者  文件或者目录名               递归的修改里面的目录或者文件
```



```
只修改组  chgrp
```

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C427e04d8164641abbf57fd84e143a98b%5Cclipboard.png)

### 3.7权限管理命令

文件或者目录都具有3个权限   

- read  读

- wirte  写

- execute  执行

  



 文件或者目录的权限控制



文件或者目录有三种权限  读 写  执行

文件或者目录有三部分权限   所有者权限   同组用户权限   其他组权限

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C0c4327f06bef476faffcbcbf76791c01%5Cclipboard.png)

第一位  表示 当前是目录还是文件

文件是-   目录是盘符

2 3 4 位表示的是所有者的权限

5 6 7  位表示的是同组用户的权限

 8 9 10 表示的不同组用户的权限



```
  修改文件或者目录的权限   chmod
```

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C05d3f736e75f4d20b60d8b28c8e068de%5Cclipboard.png)

 u表示所有者

g表示同组用户

o表示不同组用户

+表示添加权限  - 表示减少权限   =表示设置权限



chmod  g+w  文件名    给文件名添加写的权限、

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cd7e23963a13741868f80ecfd25d71a5a%5Cclipboard.png)

数字修改权限

每一个权限对应一个数字

  r   w   x

  4    2     1

  7 7 7 

rwxrwx rwx

chmod 777 text  表示三部分权限都可以读写执行  

tab键自动补齐剩下的内容

### 3.8  linux网络配置

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C4e1439b3cfb843bdb8c45989689d40a9%5Cclipboard.png)

网络配置需要进入到 etc中

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C0a154aa7cb044ab3b665c4232ad8aaeb%5Cclipboard.png)

```
查看进程  ps

ps  -e  显示所有进程

ps  -ef  格式的显示所有进程 比较清楚
```

```
关闭进程   kill   - 9  KID
```

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cea9fa012fa774ab1bfbe4c9849fb8ee2%5Cclipboard.png)

​    

```
            linux 的服务管理   systemctl
```

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cb69087f5e4eb4b4095ce6a8651edd578%5Cclipboard.png)

什么是服务管理呢  就是后台运行的必要的程序  也是进程

```
firewalld   防火墙   sshd  远程连接

start 开启   stop  停止  status 查看   restatrt 重启   enable  开机自启

systemctl  start  firewalld  开启防火墙
```





### 3.9linux 的rpm打包

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C5c21a1f0efe244a3a101d6be31fc4c4d%5Cclipboard.png)

rpm其实就和windows  的.exe文件差不多

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C50f8338127364c95b201dc2c2f11cefb%5Cclipboard.png)

```
查看安装包  rpm -qa
```

```
卸载  rpm  -e   安装软件
```

```
安装安装包  rpm    -ivh   安装包名
```

yum 安装命令

```
yum last  installed 查看安装包
```

```
yum install     +文件名 安装软件
```

```
yum  remove +文件名   删除软件
```



## 4Linux用户操作

其中**root**是具有最大权限的用户 

### **添加用户**   

useradd  {路径}  用户名

举例如下：

```
useradd -d /home/ww  wangwu 
```

每一个用户创建都会在home下生成一个子目录   如果你没有设置  哪么他就会默认名字和用户名一致

另外每一个用户都至少属于一个组  如果不指定组 哪么就会默认创建一个组  名字和用户名一致



### 给用户设置密码 

 passwd  加用户名

```
passwd   171935
```

### 删除用户  

userdel 加用户名

```
userdel  wangwu 
```

### 查看用户信息  

id+用户名

```
id   wangwu 
```

另外每一个用户都至少有一个组 以后我们可以通过组来对用户进行权限的设置



### 切换账户 

su+用户名 

```
su  root
```

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C02fafc39a7564ece8f5637dd9b5948f8%5Cclipboard.png)

高切换低 不需要密码

低切换高需要密码



##  5 linux的组

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C160c718974ec4fef8caefa87647a0169%5Cclipboard.png)

解释    linux 的组就相当于分工作  例如我是研发部  你是技术部

每一个用户至少属于一个组   其中对用户的权限管理就需要组

新建用户如果没有设置组的话 哪么他就会自动生成一个和用户名一样的组



### **添加组** 

groupadd +组名

```
groupadd    dev
```

### 删除组  

groupdel+组名

```
groupdel  dev
```



### 将用户加入组  

gpasswd -a  用户名   组名

```
gpasswd -a  wangww  dev
```



### 将用户移出组 

 gpasswd  -d  用户名  组名

```
 gpasswd  -d  wangwu dev
```

### **创建新用户时 指定组**  

useradd   -g   组名 用户名

```
useradd   -g   dev wangwu 
```

​       



##  6 linux系统操作

```
shutdown now 现在关机
```

```
shotdown -h xxx  定时关机
```

```
shotdown  -r  now   立即重启
```

```
立即重启  reboot
```

```
同步数据库   sync
```

