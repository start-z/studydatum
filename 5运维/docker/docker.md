

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





### 6docker搭建mysql集群（集群看git脑图文件）



### 7分布式存储

问题：有2亿条数据   需要存储 在redis中   我们如何解决呢？

一台服务器是不可能实现的  在这个时候我们就需要搭建集群。



#### 1hash取余计算

 用数据量/集群的台数的余数确立数据从那一台集群服务器上读取

- 优点： 方便分区
- 缺点： 万一集群某一台宕机  不能动态的修改集群台数的数量 导致 计算出来的数据存储机对应不上    且不方便扩容。 



#### 2一致性哈希算法

其本质也是采用取模算法 只不过由集群的台数变为了2^32-1次方   形成一个**环形空间**

集群服务器的地址计算：通过hash算法对ip的计算得到具体的位置

key如何计算位置呢：通过计算key的hash值得到具体的hash位置 然后**顺时针**找到相遇的**第一台主机**  并存储



- 优点：解决hash取余的容错和一致性和扩展性
- 缺点：数据会导致倾斜（集群服务器太少导致数据大多被缓存在一台服务器上  ）



#### 3hash槽分区

hash槽实际上就是一个数据  大小为【0，2^14-1】

![image-20220601115116713](http://inis.inis1719.cn/202206011151438.png)



### 8 docker Redis 搭建集群（具体看脑图）





redis-cli --cluster create 192.168.47.129:6381 192.168.47.129:6382 192.168.47.129:6383 192.168.47.129:6384 192.168.47.129:6385  192.168.47.129:6386 --cluster-replicas 1



### 9dockerFile

**官网:***

dockerfile是用来构建docker镜像的文本文件，是由一条条构建镜像所需要的指令和参数构成的脚本

![image-20220601152043462](http://inis.inis1719.cn/202206011520260.png)





#### dockerfile常用字指令:

注意  dockerfile的文件首字母大写

**Form:** 表示当前镜像基于那个镜像**

**MAINTAINER: 镜像维护者**

**RUN: **  容器构建时需要的命令

**EXPOSE:  **容器暴露的端口

**WORKDIR:**容器的默认落脚点

**USER:**使用容器的账号 默认为root

 **ENV**:构建过程中设置环境变量

**VOLUME:**容器的容器卷

**ADD:**拷贝宿主机的文件到镜像中且会自动解压和自动处理url

**COPY:**类似与add  

**CMD:**注定容器启动后需要做的事情



dockerfile示例如下(tomcat)

```
#
# NOTE: THIS DOCKERFILE IS GENERATED VIA "apply-templates.sh"
#
# PLEASE DO NOT EDIT IT DIRECTLY.
#

FROM eclipse-temurin:11-jdk-focal

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME

# let "Tomcat Native" live somewhere isolated
ENV TOMCAT_NATIVE_LIBDIR $CATALINA_HOME/native-jni-lib
ENV LD_LIBRARY_PATH ${LD_LIBRARY_PATH:+$LD_LIBRARY_PATH:}$TOMCAT_NATIVE_LIBDIR

# see https://www.apache.org/dist/tomcat/tomcat-8/KEYS
# see also "versions.sh" (https://github.com/docker-library/tomcat/blob/master/versions.sh)
ENV GPG_KEYS 05AB33110949707C93A279E3D3EFE6B686867BA6 07E48665A34DCAFAE522E5E6266191C37C037D42 47309207D818FFD8DCD3F83F1931D684307A10A5 541FBE7D8F78B25E055DDEE13C370389288584E7 5C3C5F3E314C866292F359A8F3AD5C94A67F707E 765908099ACF92702C7D949BFA0C35EA8AA299F1 79F7026C690BAA50B92CD8B66A3AD3F4F22C4FED 9BA44C2621385CB966EBA586F72C284D731FABEE A27677289986DB50844682F8ACB77FC2E86E29AC A9C5DF4D22E99998D9875A5110C01C5A2F6059E7 DCFD35E0BF8CA7344752DE8B6FB21E8933C60243 F3A04C595DB5B6A5F1ECA43E3B7BBB100D811BBE F7DA48BB64BCB84ECBA7EE6935CD23C10D498E23

ENV TOMCAT_MAJOR 8
ENV TOMCAT_VERSION 8.5.79
ENV TOMCAT_SHA512 ae059a595ba11386bf3c0a80e961c5f8bb057b2bab987c3863337311e95d4fa22f1185a44b2c8856dd33f7cfe76d4de4e2fe1bc8b89c6abfbfc008656c49b8c0

RUN set -eux; \
	\
	savedAptMark="$(apt-mark showmanual)"; \
	apt-get update; \
	apt-get install -y --no-install-recommends \
		ca-certificates \
		curl \
		dirmngr \
		gnupg \
	; \
	\
	ddist() { \
		local f="$1"; shift; \
		local distFile="$1"; shift; \
		local mvnFile="${1:-}"; \
		local success=; \
		local distUrl=; \
		for distUrl in \
# https://issues.apache.org/jira/browse/INFRA-8753?focusedCommentId=14735394#comment-14735394
			"https://www.apache.org/dyn/closer.cgi?action=download&filename=$distFile" \
# if the version is outdated (or we're grabbing the .asc file), we might have to pull from the dist/archive :/
			"https://downloads.apache.org/$distFile" \
			"https://www-us.apache.org/dist/$distFile" \
			"https://www.apache.org/dist/$distFile" \
			"https://archive.apache.org/dist/$distFile" \
# if all else fails, let's try Maven (https://www.mail-archive.com/users@tomcat.apache.org/msg134940.html; https://mvnrepository.com/artifact/org.apache.tomcat/tomcat; https://repo1.maven.org/maven2/org/apache/tomcat/tomcat/)
			${mvnFile:+"https://repo1.maven.org/maven2/org/apache/tomcat/tomcat/$mvnFile"} \
		; do \
			if curl -fL -o "$f" "$distUrl" && [ -s "$f" ]; then \
				success=1; \
				break; \
			fi; \
		done; \
		[ -n "$success" ]; \
	}; \
	\
	ddist 'tomcat.tar.gz' "tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz" "$TOMCAT_VERSION/tomcat-$TOMCAT_VERSION.tar.gz"; \
	echo "$TOMCAT_SHA512 *tomcat.tar.gz" | sha512sum --strict --check -; \
	ddist 'tomcat.tar.gz.asc' "tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz.asc" "$TOMCAT_VERSION/tomcat-$TOMCAT_VERSION.tar.gz.asc"; \
	export GNUPGHOME="$(mktemp -d)"; \
	for key in $GPG_KEYS; do \
		gpg --batch --keyserver keyserver.ubuntu.com --recv-keys "$key"; \
	done; \
	gpg --batch --verify tomcat.tar.gz.asc tomcat.tar.gz; \
	tar -xf tomcat.tar.gz --strip-components=1; \
	rm bin/*.bat; \
	rm tomcat.tar.gz*; \
	command -v gpgconf && gpgconf --kill all || :; \
	rm -rf "$GNUPGHOME"; \
	\
# https://tomcat.apache.org/tomcat-9.0-doc/security-howto.html#Default_web_applications
	mv webapps webapps.dist; \
	mkdir webapps; \
# we don't delete them completely because they're frankly a pain to get back for users who do want them, and they're generally tiny (~7MB)
	\
	nativeBuildDir="$(mktemp -d)"; \
	tar -xf bin/tomcat-native.tar.gz -C "$nativeBuildDir" --strip-components=1; \
	apt-get install -y --no-install-recommends \
		dpkg-dev \
		gcc \
		libapr1-dev \
		libssl-dev \
		make \
	; \
	( \
		export CATALINA_HOME="$PWD"; \
		cd "$nativeBuildDir/native"; \
		gnuArch="$(dpkg-architecture --query DEB_BUILD_GNU_TYPE)"; \
		aprConfig="$(command -v apr-1-config)"; \
		./configure \
			--build="$gnuArch" \
			--libdir="$TOMCAT_NATIVE_LIBDIR" \
			--prefix="$CATALINA_HOME" \
			--with-apr="$aprConfig" \
			--with-java-home="$JAVA_HOME" \
			--with-ssl=yes \
		; \
		nproc="$(nproc)"; \
		make -j "$nproc"; \
		make install; \
	); \
	rm -rf "$nativeBuildDir"; \
	rm bin/tomcat-native.tar.gz; \
	\
# reset apt-mark's "manual" list so that "purge --auto-remove" will remove all build dependencies
	apt-mark auto '.*' > /dev/null; \
	[ -z "$savedAptMark" ] || apt-mark manual $savedAptMark > /dev/null; \
	find "$TOMCAT_NATIVE_LIBDIR" -type f -executable -exec ldd '{}' ';' \
		| awk '/=>/ { print $(NF-1) }' \
		| xargs -rt readlink -e \
		| sort -u \
		| xargs -rt dpkg-query --search \
		| cut -d: -f1 \
		| sort -u \
		| tee "$TOMCAT_NATIVE_LIBDIR/.dependencies.txt" \
		| xargs -r apt-mark manual \
	; \
	\
	apt-get purge -y --auto-remove -o APT::AutoRemove::RecommendsImportant=false; \
	rm -rf /var/lib/apt/lists/*; \
	\
# sh removes env vars it doesn't support (ones with periods)
# https://github.com/docker-library/tomcat/issues/77
	find ./bin/ -name '*.sh' -exec sed -ri 's|^#!/bin/sh$|#!/usr/bin/env bash|' '{}' +; \
	\
# fix permissions (especially for running as non-root)
# https://github.com/docker-library/tomcat/issues/35
	chmod -R +rX .; \
	chmod 777 logs temp work; \
	\
# smoke test
	catalina.sh version

# verify Tomcat Native is working properly
RUN set -eux; \
	nativeLines="$(catalina.sh configtest 2>&1)"; \
	nativeLines="$(echo "$nativeLines" | grep 'Apache Tomcat Native')"; \
	nativeLines="$(echo "$nativeLines" | sort -u)"; \
	if ! echo "$nativeLines" | grep -E 'INFO: Loaded( APR based)? Apache Tomcat Native library' >&2; then \
		echo >&2 "$nativeLines"; \
		exit 1; \
	fi

EXPOSE 8080
CMD ["catalina.sh", "run"]
```



### 10docker微服务实战



将jar包和dockerfile的配置文件在统一目录

```
执行 docker  build -t   镜像名称  目录
```



### 11dockerNetwork

![image-20220601164837780](http://inis.inis1719.cn/202206011648839.png)

```
docker network   --docker网络查看
```



#### **1docker网络模式**



|  网络模式  |                             简介                             |
| :--------: | :----------------------------------------------------------: |
|   bridge   | 为每一个容器分配 设置ip 并将容器连接到一个docker0  虚拟网桥  docker默认 |
|   hhost    |     容器将不会虚拟出 自己的网卡 而是使用宿主机的ip和端口     |
|    none    |            容器有独立的network 但没有任何网络设置            |
| containner |  新创建的容器不会创建自己的网卡和ip而是和指定的容器共享网络  |

 **bridge模式**





![image-20220602101024997](http://inis.inis1719.cn/202206021010036.png)

**host模式**

![image-20220602100930843](http://inis.inis1719.cn/202206021009006.png)





**container模式**

![image-20220602101432365](http://inis.inis1719.cn/202206021014012.png)



#### **2自定义模式**

为什么需要使用自定义模式？

因为docker容器的ip地址是会动态变化的  在某个时段可能我们能够互相ping通ip，但是一旦ip变化就会失败，于是我们使用服务名进行ping 。

**实现步骤**

创建自定义network

```
docker network create  zhou_network
```

指定网络模式进入容器

```
docker run -d -p 8081:8080 --network zzyy_network  --name tomcat81 billygoo/tomcat8-jdk8
docker run -d -p 8082:8080 --network zzyy_network  --name tomcat82 billygoo/tomcat8-jdk8
```

容器内部直接ping服务名即可

![img](http://inis.inis1719.cn/202206021031074.png)





### 12docker-compose容器编排

解决问题：容器实例太多   缺乏统一管理。

docker-compose管理由多个docker容器组成一个应用。



安装

```
yum install  docker-compose-plugin
```



业务逻辑图

![image-20220602110257978](http://inis.inis1719.cn/202206021103985.png)

使用步骤：

1创建docker-compose.yml文件

![image-20220602112101185](http://inis.inis1719.cn/202206021121526.png)

2使用docker-compose命令





注意：在面对少量容器时，可使用容器编排 ，但是一旦容器过多  例如1000个的话就需要使用到k8s.



### 13docker可视化界面工具-portainer

官网地址：https://portainer.io/



安装：

```
docker run -d -p 8000:8000   -p 9000:9000 -d   --name portainer --restart=always  -v /var/run/docker.sock:/var/run/docker.sock   -v portainer_data:/data      portainer/portainer
```



访问ip+9000  选择本地docker

![image-20220602114311150](http://inis.inis1719.cn/202206021143171.png)





### 14docker监控统计-CIG

C:CAdivisor -容器资源监控工具

 I:InfluxDB-  开源分布式时序

Granfana: 图形化界面展示



使用容器编排一次性启动3个服务

docker-compose.yml文件如下：

```
version: '3.1'
volumes:
  grafana_data: {}
services:
 influxdb:
  image: tutum/influxdb:0.9
  restart: always
  environment:
    - PRE_CREATE_DB=cadvisor
  ports:
    - "8083:8083"
    - "8086:8086"
  volumes:
    - ./data/influxdb:/data
 cadvisor:
  image: google/cadvisor
  links:
    - influxdb:influxsrv
  command: -storage_driver=influxdb -storage_driver_db=cadvisor -storage_driver_host=influxsrv:8086
  restart: always
  ports:
    - "8080:8080"
  volumes:
    - /:/rootfs:ro
    - /var/run:/var/run:rw
    - /sys:/sys:ro
    - /var/lib/docker/:/var/lib/docker:ro
 grafana:
  user: "104"
  image: grafana/grafana
  user: "104"
  restart: always
  links:
    - influxdb:influxsrv
  ports:
    - "3000:3000"
  volumes:
    - grafana_data:/var/lib/grafana
  environment:
    - HTTP_USER=admin
    - HTTP_PASS=admin
    - INFLUXDB_HOST=influxsrv
    - INFLUXDB_PORT=8086
    - INFLUXDB_NAME=cadvisor
    - INFLUXDB_USER=root
    - INFLUXDB_PASS=root
```

