# 											docker学习





**docker官网：https://docs.docker.com/search/?q=redi**

**dockerhub**国外镜像仓库文件： https://hub.docker.com/**





## 1简介

方便本地环境没有问题但是到其他环境就会出现问题的情况

一次镜像 ，多次执行



**虚拟机与容器化技术的比较**d

虚拟机文件：依赖于外部系统 且在外部系统只是一堆文件 不会干涉应用程序（操作步骤慢 资源占用多 启动慢）

容器化技术：启动快  资源占用少   小巧轻量。



**docker三要素**

| 名称 | 面向对象                                |
| ---- | --------------------------------------- |
| 容器 | 对象                                    |
| 镜像 | 类（简易版的linux环境  不需要的不加载） |
| 仓库 | （存放**镜像**的地方  类似于maven项目） |

docker国外镜像仓库地址：https://hub.docker.com/search?q=redis

**docker操作原理入门**

docker本质是cs架构 通过客户端连接对应镜像文件的后台守护进程

![image-20220510164343830](https://gitee.com/zhou-kaifa/images/raw/master/202205101643738.png)







## 2 docker安装教程

自行去官网下载文件

### 1卸载旧版本docker

```
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```



### 2安装gcc软件

```
 	yum -y install gcc- gcc++
	yum -y install gcc
```



### 3安装utls工具

```
yum install -y yum-utils
```

### 4设置镜像仓库（以下命令二选一即可）

```
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo    //设置为国内阿里云镜像仓库
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo        //设置为国外镜像仓库
```

### 5设置yum索引

```
yum makecache fast   --设置yum索引
```

### 6安装docker

```
yum install docker-ce docker-ce-cli containerd.io
```

### 7启动docker

```
systemctl start docker
```

### 8检验docker是否正常运行

```
docker run hello-world
```



### 9查看docker版本

```
docker version
```



### 10设置阿里云加速镜像

登录阿里云控制台查看容器镜像服务

![image-20220531112426719](http://inis.inis1719.cn/202205311124831.png)









## 3docker命令大全（命令参数可通过docker  --help查看）

### 帮助启动类命令

**docker启动/停止/重启/查看状态**

```
systemctl  start/stop/restart/status  docker
```

**开机启动docker**

```
systemctl  enable docker   
```

**docker命令手册**

```
docker  --help
docker  指定命令   --help  查看指定命令使用手册
```



### 镜像命令

**查看镜像**

```
docker images  /-a 查看全部/-q  只显示镜像id
```

**搜索镜像（在配置的镜像仓库中搜索）**

```
docker search    镜像名     
```

**拉取镜像**

```
docker  pull 镜像名/id
```

**查看镜像占用率**

```
 docker system df
```

**删除镜像**

```
docker rmi 镜像名称/id
```



面试题： 谈谈docker虚悬镜像是什么？

仓库名和标签都是《none》的镜像





### 容器命令

**容器启动**

```
docker  run     --name 指定镜像名称    -P 大写P  使用随机端口映射   -p 小写p 使用指定端口映射    -it    进入镜像内部操作
```

![image-20220531120424442](http://inis.inis1719.cn/202205311204415.png)



**查看docker正在运行的容器**

```
docker ps 
```



**内部容器（ubuntu）退出**

```
exit  退出  容器会退出
ctrp+p+q    容器不会退出
```



**启动/停止/删除的容器**

```
docker star/stop/rm  容器id/容器名
```



**查看容器内部细节**

```
docker inspect  荣启名/id
```



**进入容器**

```
docker  exec  --it 容器名/id
docker attach  容器id    

区别是exec会打开新的终端 使用exit退出时不会关闭容器
attach不会打开新的终端  使用exit退出会关闭容器
```



**容器文件拷贝到主机**

```
docker  cp  容器名/id  容器文件目录   目的主机文件目录
```



**导出容器**

```
docker  export 容器名/id >文件名.tar 
```

**导入容器**

```
cat 文件名.tar | docker import -镜像用户/镜像名：版本号
```



**更新容器内容方便下次运行时和上次保持一致**

```
docker commit -m="提交的描述" -a="作者" 容器id  要创建的目标镜像名：标签名
```



### 4docker私有化搭建及其发布云端

**发布阿里云**

![image-20220531152358888](http://inis.inis1719.cn/202205311523756.png)



**搭建私有docker仓库**

```
docker  pull registry   --拉取镜像
```



容器数据卷：其实就是容器的数据备份文件



**docker数据卷与宿主主机绑定**

```
  docker run -it --privileged=true  -v  /宿主主机目录：/容器目录:rw/ro  镜像名    "rw  容器内能读能写  ro 只能读"
```



**容器卷的继承**

1. 容器完成与宿主机的映射

```
 docker run -it --privileged=true  --volumes-from 夫容器卷   镜像名
```

docker run -p 6379:6379    --name myredis   --privileged=true    -v     /usr/dockerRedis/config/redis.conf:/etc/redis/redis.conf  -v   /usr/dockerRedis/config/data:/data -d 7614ae9453d1 redis-server /etc/redis/redis.conf 







### 5docker 启动各种常用软件

**redis**

```
docker run -p 6379:6379 --name MyRedis --privileged=true -v /usr/dockerRedis/config/redis.conf:/etc/redis/redis.conf  -v  /usr/dockerRedis/config/data:/data -d 7614ae9453d1 redis-server /etc/redis/redis.conf 
```



**mysql**

```
docker run -d -p 3306:3306   --privileged=true     -v   /usr/dockerMySQL/log:/var/log/mysql    -v   /usr/dockerMySQL/data:/var/lib/mysql   -v  /usr/dockerMySQL/conf:/etc/mysql/conf.d   --name   mysql    -e  MYSQL_ROOT_PASSWORD=171935  mysql:5.7

```





### 6docker搭建mysql集群



1启动mysql-master主机 

```
docker run -d -p 3306:3306   --privileged=true     -v   /usr/dockerMySQL/log:/var/log/mysql    -v   /usr/dockerMySQL/data:/var/lib/mysql   -v  /usr/dockerMySQL/conf:/etc/mysql/conf.d   --name   mysql    -e  MYSQL_ROOT_PASSWORD=171935  mysql:5.7
```



