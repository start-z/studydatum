# oracle11g安装教程

# 1 卸载教程

根据文章中的进行操作即可

https://jingyan.baidu.com/article/922554468d4e6b851648f4e3.html



# 2安装教程



## 1  下载11g安装包  已准备好11g安装包

链接：https://pan.baidu.com/s/1Cw8r-nJjbleYLYIU4lWDlQ?pwd=9999 
提取码：9999 
--来自百度网盘超级会员V1的分享





## 2进行安装界面

教程可自行百度  







# 3疑难杂症



如果sql/plus能登录上说明oracle安装成功



如果java程序不能连接oracle 的话 具体看查看对应的报错信息和日志文件

目录为   oracle目录\product\11.2.0\dbhome_1\NETWORK\ADMIN下的文件可查看对应网关及监听器的问题

![image-20220304152607867](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041526885.png)



目录oracle\oracle\2022-03-04\diag\tnslsnr\magicPro\listener\trace下的listen文件可以查看监听程序的日志

![image-20220304152739320](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041527196.png)

举例报错如下：

本地windows用户名为中文导致连接失败   监听日志文件报错内容为：

```java
TNS-01153: 未能处理字符串: (DESCRIPTION=(CONNECT_DATA=(SID=orcl)(CID=(PROGRAM=JDBC Thin Client)(HOST=__jdbc__)(USER=一号线)))(ADDRESS=(PROTOCOL=TCP)(HOST=127.0.0.1)(PORT=1521
 NL-00303: NV 字符串中存在语法错误
```

根据日志可以清楚的看到user的属性为一号线   于是解析的时候存在错误



后查阅资料发现更改计算机用户名的快捷方式

windows徽标+R打开运行 并输入netplwiz

![image-20220304153023860](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041530579.png)



点击需要修改的用户   重启计算机即可（必须重启）

![image-20220304153119568](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041531221.png)



2关于oracle注意事项：

网关监听器查看对应的host是否为localhost或者 127.0.0.1  

![image-20220304153416799](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041534587.png)

![](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041534217.png)

2关于oracle监听服务客户端

![image-20220304153503626](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041535782.png)



客户端监听服务查看

![image-20220304153537987](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041535571.png)

客户端服务名主机查看

![](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041536646.png)





3查看对应服务是否启动

打开任务管理器



![image-20220304153710298](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041537138.png)

点击服务即可查看 另外还支持手动开启

![image-20220304153750777](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203041537546.png)



