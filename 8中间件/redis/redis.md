# 												Redis

## 1简介

为什么需要使用redis？

单机mysql时代在以前   数据访问量不大   所以一个数据库就行，现在是大数据时代 所以我们需要使用缓存。

redis 其实就是一个ky键值对的数据库.

  redis的用途？

1. 内存存储、持久化。
2. 效率高，基于单线程。
3. 发布订阅。
4. 地图信息分析
5. 计时器等等。

其中redis 不建议在windows 上进行  一般都是在linux 系统中进行



**redis是使用单线程的，为什么redis 使用单线程还是哪么快呢？**

因为redis是将数据存储到内存中的 而多线程cpu还需要上下文



## 2基于docker安装redis

### 拉取redis镜像

```java
docker pull redis
```

### 启动redis容器

```
docker run -p 6379:6379   --name RedisStudy   --privileged=true -v /usr/dockerRedis/config/redis.conf:/etc/redis/redis.conf  -v  /usr/dockerRedis/config/data:/data -d 7614ae9453d1 redis-server /etc/redis/redis.conf  --restart=always  --appendonly yes  

--privileged=true  开启绝对权限
-v  设置容器卷 防止数据丢失
redis-server /etc/redis/redis.conf  容器启动时启动redis
--restart=always   总是自启动 开机自启
--appendonly yes  开启持久化
```

其中redis的默认软件路径   usr /local/bin

### 修改配置文件

打开redis.conf文件

注释bind 127.0.0.1

设置redis密码 

![image-20220608175614316](http://inis.inis1719.cn/202206081756376.png)

设置daemonize 为no



## 3redis基础命令

帮助命令

```
help  命令 
```



### 数据库操作

在redis中的配置文件中有这个databases

![img](http://inis.inis1719.cn/202206082133386.png)

redis中默认有16个数据库 我们可以随时切换  默认是0号数据库

切换数据库

```
select   数字 切换数据库
```

查看数据库大小

```java
dbsize 数字 查看数据库大小
```

查看当前库所有key

```
key*
```

清除当前数据库

```
 flushdb
```

清除全部数据库 

```
flushall
```

### Key操作

```
exists   key  判断是否存在
```

```
move  key  指定数据库号   移动  到指定号数数据库
```

```
expipe   key    设置过期时间  单位为s
```

```
type  查看当前数据的类型
```

```
incr  key  自增1

 incrby   key   自增的数字   自增自定义的数字

decr  key 自减1
```

![img](http://inis.inis1719.cn/202206082133388.png)

## 4五大数据类型

### 1String（字符串）

key添加字符串

```
append    key  添加字符串
```

getrange  替换 多少到多少的数据

```
setrange   key   value截取开始索引  结束位置索引 
```

设置过期的时间

```
setex    key   时间值 
```

判断key是否存在 如果不存在就创建  

```
setnx    key
```

![img](http://inis.inis1719.cn/202206082133389.png)

 一次设置多个值

```
mset     key1   value1  key2    value2   
```

 一次获取多个值

```
mget   key1  key2  
```

一次设置多个值 具有原子性  如果有一个失败  就全部失败

```
msetnx    key1 value1  key2  value2
```



### 2List   （列表）

在redis 中我们 将list 弄成栈  队列 堵塞队列

栈就是先进后出      队列就是两边都是通的

所有的list命令都是l开头  从左到右

```
Lpush    key    value1  value2
```

获取list的值

```
Lrange   key
```

rpush   设置list的值 从右到左

```
rpush       key    value1  value2
```

lpop  移出第一个元素

```
lpop  key
```

rpop  移出最后一个元素

```
rpop key
```

 lindex  获取对应索引的数据

```
lindex   key  索引
```

llen  返回列表的长度

```
llen key
```

移出指定的数据 lrem

```
lrem  key  索引位置
```

截取指定的元素 ltrim

```
ltrim  key  起始索引  结束索引
```

rpoplpush  把最后一个元素转移到指定的列表中

```
rpoplpush   key1  key2
```

lset  替换指定索引的值  没有就失败 有就替换

```
lset     key    index  value
```

linsert  将某一个数据插入在你指定的数据后面或者前面

```
linsert  key   after/before  index  value
```

### 3set（list数据可重复  set不可以  集合）

sadd 添加set集合的值

```
sadd   key   value
```

```
smembers  查看指定set 的值
```

```
sisimember  判断指定的值在不在set集合中
```

```
scard  获取set的长度
```

```
srem  移出指定的元素
```

```
srandmember   在集合中随机抽选出数据
```

```
spop  随机移出一个元素 
```

```
sdiff   差集  输出2个集合不同的元素
```

```
sinter   交集  输出 2个集合中相同的元素
```

```
sunion  并集   输出相同的部分
```

### 4Hash

```
hset  设置一个值
```

```
hget  获取一个值
```

```
hgetall  获取所有的值
```

```
  hdel  删指定的key
```

```
 hllen  获取hash 的长度
```

```
hexists   判断指定字符端是否存在
```

```
incr  自增
```

```
decr  自减
```

```
hsetnx  如果不存在可以设置
```

```
hsetnx   如果存在不可以设置
```

存储对象建议使用hash  虽然string 也可以存储 但是hash 更好



### 5zset（有序集合）

```
zadd 添加数据
```

```
zrange  查看数据
```

```
zrangescore 升序
```

```
zrevrange  降序
```

```
zrem  移出指定的元素 
```

```
scard   获取集合的个数
```

应用场景

set能使用的zset 都能使用 另外zset是有序集合 所有可以统计一些升序降序的数据   比如排行榜之类的



## 5  三大特殊类型

###  geospatial  位置

```
  geoadd 添加地理位置
```

```
添加地理经纬度设置 geoadd
```

```
geopos 获取数据
```

```
geodist 计算之间的差  查看两地的距离
```

```
georedius  以经纬度为半径 查找数据  常用于找附近的人
```

```
georandiusbymember  找出 指定元素 周围的其他元素
```

![img](http://inis.inis1719.cn/202206082133390.png)

​                       

###   hyperloglog

 统计用户

![img](http://inis.inis1719.cn/202206082133391.png)

```
添加pfadd
```

```
查看数量pfcount
```

```
合并查看交集  pfmerge 
```

![img](http://inis.inis1719.cn/202206082133392.png)

优点是固定的内存 而大数据 则是十分占内存的

其中loglog 是有误差的

### bitmaps

```
 setbit  设置值
```

```
getbit 获取值
```

```
bitcount  获取数量
```





## 6事务

其中redis中的事务不一定保持原子性

redis中不存在隔离级别的概念

原子性就是多个都执行完才算成功  如果有一个失败就 全部失败

```
开启事务 multi
```

```
关闭事务 exec
```

```
放弃事务 discard   就是放弃在队列中的事务 不会执行
```

redis事务中语法错误不会影响到其他的运行 但是命令错误就是全部错误。



## 7redis  锁

乐观锁：查数据的时候不一定上锁

悲观锁:干什么都要上锁   影响性能

```
 watch   监视 
```

![img](http://inis.inis1719.cn/202206082133393.png)

![img](http://inis.inis1719.cn/202206082133394.png)

![img](http://inis.inis1719.cn/202206082133395.png)

如果执行失败的话  解锁unwatch  在上锁获取最新的值 watch

```
watch 上锁
```

```
multi  开启事务
```

```
exit 结束事务
```

```
unwatch  解锁
```



## 8redis配置文件解析

### 单位的配置  不区分大小写  

![img](http://inis.inis1719.cn/202206082133396.png)

### includes  包含 配置文件

![img](http://inis.inis1719.cn/202206082133397.png)



![img](http://inis.inis1719.cn/202206082133398.png)

![img](http://inis.inis1719.cn/202206082133399.png)

### 快照 snapshotting

![img](http://inis.inis1719.cn/202206082133400.png)

![img](http://inis.inis1719.cn/202206082133401.png)

![img](http://inis.inis1719.cn/202206082133402.png)

save 后第一个数字时间  第二个是key至少被修改的次数

![img](http://inis.inis1719.cn/202206082133403.png)

###   replication  主从复制

![img](http://inis.inis1719.cn/202206082133404.png)

### security  安全

![img](http://inis.inis1719.cn/202206082133405.png)

里面有一个参数 requirapass  是设置redis 的密码的  默认是没有密码的

clients  客户端

![img](http://inis.inis1719.cn/202206082133406.png)

 

appent  only模式    aof配置

![img](http://inis.inis1719.cn/202206082133407.png)



## 9redis持久化

​    redis持久化操作（rdb 和aof）

- aof本质上是读写文件
- rdb本质上是fork一个子进程

### rdb

![img](http://inis.inis1719.cn/202206082133408.png)

  简单的来说就是数据先存储到一个临时的rdb文件  然后在替换上次持久化的rdb文件  最后形成了正式的rdb文件

![img](http://inis.inis1719.cn/202206082133409.png)

rdb触发

![img](http://inis.inis1719.cn/202206082133410.png)

如果你想恢复rdb 文件   就将rdb文件放在redis目录下即可

![img](http://inis.inis1719.cn/202206082133411.png)

- 优点 ： 就是适合大规模的数据恢复
- 缺点：他是有时间间隔 的的操作 如果宕机 的话 哪么在那个时候他的数据就会全部丢失

另外他们保存rdb文件是在主进程下for开辟一个子线程   会占用一定的内存

![img](http://inis.inis1719.cn/202206082133412.png)







### aof

![img](http://inis.inis1719.cn/202206082133413.png)

其实aof就是将你操作的命令记录下来  最后在使用命令 从头再来 进行操作    

![img](http://inis.inis1719.cn/202206082133414.png)

  配置文件的位置

![img](http://inis.inis1719.cn/202206082133415.png)

![img](http://inis.inis1719.cn/202206082133416.png)

![img](http://inis.inis1719.cn/202206082133417.png)

如果文件失败了   哪么就是用bin目录下的check-aof 文件去修复他

![img](http://inis.inis1719.cn/202206082133418.png)

![img](http://inis.inis1719.cn/202206082133419.png)

![img](http://inis.inis1719.cn/202206082133420.png)



## 10发布订阅

需要三个对象

1 消息发布者

2 通道

3消息订阅者

![img](http://inis.inis1719.cn/202206082133422.png)

命令

![img](http://inis.inis1719.cn/202206082133423.png)

![img](http://inis.inis1719.cn/202206082133424.png)

![img](http://inis.inis1719.cn/202206082133425.png)



## 11主从复制

主从复制的概念

其实就是将redis 的数据多次备份

其中被复制的只负责写    其他复制的redis则负责读  大大的减轻服务器的压力

![img](http://inis.inis1719.cn/202206082133426.png)

![img](http://inis.inis1719.cn/202206082133427.png)

![img](http://inis.inis1719.cn/202206082133428.png)

![img](http://inis.inis1719.cn/202206082133429.png)

### 主从复制的作用

1 保持备份

2 数据丢失后 还能在从服务器上找到

3读写分离



### 环境配置  

其中主redis 是不用配置的  需要配置的是从redis

查看当前redis数据库的信息  info   replication

![img](http://inis.inis1719.cn/202206082133430.png)

![img](http://inis.inis1719.cn/202206082133431.png)

![img](http://inis.inis1719.cn/202206082133432.png)

### 实现主从复制

在从机上  输入 slaveof   主机号  端口号

![img](http://inis.inis1719.cn/202206082133433.png)

![img](http://inis.inis1719.cn/202206082133434.png)

![img](http://inis.inis1719.cn/202206082133435.png)

另外 我们是用命令slaveo f  主机号  端口号 实现主从复制   实际中我们需要在配置文件中设置replication中的值  进行永久配置

细节  只有主机可以写   从机不可以写

![img](http://inis.inis1719.cn/202206082133436.png)

![img](http://inis.inis1719.cn/202206082133437.png)

使用命令行

当主机丢失连接后 从机还是能读取主机的值

当从机丢失连接后   从机自己就变成了主机    不能读取到以前主机的值

![img](http://inis.inis1719.cn/202206082133438.png)

![img](http://inis.inis1719.cn/202206082133439.png)

我们实现了第一个图的操作后  除了m都是从节点

第二个图    80即是从节点  也是父节点  但是他还是不具备写的能力  因为  查询replication 的时候他还是一个从节点

这两种都可以完成主从复制

![img](http://inis.inis1719.cn/202206082133440.png)



### 哨兵模式

意思就是说当以前的主节点宕机后   从机自己变成主机  slaveof no one （手动）

​                            哨兵模式

![img](http://inis.inis1719.cn/202206082133441.png)

哨兵模式其实就是自动的当主机宕机后 自动的在从机中配置主机

![img](http://inis.inis1719.cn/202206082133442.png)

![img](http://inis.inis1719.cn/202206082133443.png)

主观下线就是目前只有一个哨兵检测到了主机不可用  

客观下线就是 所有的哨兵都发现了主机不可用 然后进行投票在从机中选一个当从机   

1配置哨兵配置文件

![img](http://inis.inis1719.cn/202206082133444.png)

这里是配置哨兵 sentinel monitor  哨兵名   主机地址  主机端口    投票数

2启动

![img](http://inis.inis1719.cn/202206082133445.png)

上面的那个是启动哨兵的  下面的是启动redis 的

哨兵报错的日志

![img](http://inis.inis1719.cn/202206082133446.png)

f

![img](http://inis.inis1719.cn/202206082133447.png)

哨兵模式的优缺点

![img](http://inis.inis1719.cn/202206082133448.png)

优点

- 1手动变自动
- 2主从可以切换  故障转移    可用性变好

- 3基于主从复制   所有的组从配置优点他都有

缺点

- 扩容麻烦

哨兵的全部配置

![img](http://inis.inis1719.cn/202206082133449.png)

![img](http://inis.inis1719.cn/202206082133450.png)

![img](http://inis.inis1719.cn/202206082133451.png)



## 12缓存雪崩击穿

![img](http://inis.inis1719.cn/202206082133452.png)

上图 的意思就是说缓存中有user1   sql中也有user1

当时当我们读取user2  由于缓存 中没有user2  所有就会频繁的去访问sql  但是sql中也没有user2

这个就是标准的缓存穿透

![img](http://inis.inis1719.cn/202206082133454.png)

解决穿透的方法  

1给读的值加空值  再缓存中 就不会频繁访问sql 但是空值多了还是会影响性能

![img](http://inis.inis1719.cn/202206082133455.png)

![img](http://inis.inis1719.cn/202206082133456.png)

2使用布隆过滤器

布隆过滤器其实就是一种数据结构

![img](http://inis.inis1719.cn/202206082133457.png)

![img](http://inis.inis1719.cn/202206082133458.png)

缓存击穿就是用户大量的访问一个数据 类似于微博的热搜瘫痪 大量的用户访问  这里不同于缓存穿透的是  缓存穿透是因为没有那个值  而缓存击穿是有那个值 但是太多人访问

![img](http://inis.inis1719.cn/202206082133459.png)

缓存雪崩

![img](http://inis.inis1719.cn/202206082133460.png)

![img](http://inis.inis1719.cn/202206082133461.png)

![img](http://inis.inis1719.cn/202206082133462.png)

总结缓存穿透就是服务器范围redis却没有哪个值转而去数据库寻找

解决办法

1 给访问的值设置为空值，但是过多的控制还是会影响

2 实现布隆过滤器

缓存击穿：就是一个热点值key被大量访问而导致击穿

解决方法

设置热点信息永不过期

实现集群

缓存雪崩：指过程中redis进程宕机

解决方法：实现哨兵模式，搭建集群

2数据预热 提前防备，把有可能