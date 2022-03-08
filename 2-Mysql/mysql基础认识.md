

MySQL  参考地址

1. https://blog.csdn.net/qq_34115899/article/details/81190461
2. https://blog.csdn.net/XahY66/article/details/119055202



# 第1章 数据库

## 1.1 数据库概述

**什么是数据库**

数据库就是存储数据的仓库，其本质是一个文件系统，数据按照特定的格式将数据存储起来，用户可以对数据库中的数据进行增加，修改，删除及查询操作。

**什么是数据库管理系统**

数据库管理系统（DataBase Management System，DBMS）：指一种操作和管理数据库的大型软件，用于建立、使用和维护数据库，对数据库进行统一管理和控制，以保证数据库的安全性和完整性。用户通过数据库管理系统访问数据库中表内的数据。

**常见的数据库管理系统**

MYSQL ：开源免费的数据库，小型的数据库.已经被Oracle收购了.MySQL6.x版本也开始收费。

Oracle ：收费的大型数据库，Oracle公司的产品。Oracle收购SUN公司，收购MYSQL。

DB2 ：IBM公司的数据库产品,收费的。常应用在银行系统中.

SQLServer：MicroSoft 公司收费的中型的数据库。C#、.net等语言常使用。

SyBase ：已经淡出历史舞台。提供了一个非常专业数据建模的工具PowerDesigner。

SQLite : 嵌入式的小型数据库，应用在手机端。

Java相关的数据库：MYSQL，Oracle．

这里使用MySQL数据库。MySQL中可以有多个数据库，数据库是真正存储数据的地方。

数据库与数据库管理系统的关系
![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071347464.png)

## **1.2 数据库表**

数据库中以表为组织单位存储数据。

表类似我们的Java类，每个字段都有对应的数据类型。

那么用我们熟悉的java程序来与关系型数据对比，就会发现以下对应关系。

类----------表

类中属性----------表中字段

对象----------记录

## 1.3 表数据

根据表字段所规定的数据类型，我们可以向其中填入一条条的数据，而表中的每条数据类似类的实例对象。表中的一行一行的信息我们称之为记录。

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071348294.png)

表记录与java类对象的对应关系

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91cGxvYWRmaWxlcy5ub3djb2Rlci5jb20vaW1hZ2VzLzIwMjAwMTAxLzQ5NDIzMjVfMTU3Nzg3MjYyOTYzMV83RkUwQjNEMDIxQkEzOTY2NzJBNzA3MUZEMkI5NDY5RA?x-oss-process=image/format,png)





# 第2章 MySql数据库

## 2.1 MySql安装

安装

自行百度

安装后，MySQL会以windows服务的方式为我们提供数据存储功能。开启和关闭服务的操作：右键点击我的电脑→管理→服务→可以找到MySQL服务开启或停止。

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071349553.png)

也可以在DOS窗口，通过命令完成MySQL服务的启动和停止（必须以管理运行cmd命令窗口）

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071349973.png)

## 2.2 登录MySQL数据库

MySQL是一个需要账户名密码登录的数据库，登陆后使用，它提供了一个默认的root账号，使用安装时设置的密码即可登录。

格式1：cmd>  mysql –u用户名 –p密码

例如：mysql -uroot –proot

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071349861.png)

格式2：cmd>  mysql --host=ip地址 --user=用户名 --password=密码

例如：mysql --host=127.0.0.1  --user=root --password=root

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071350267.png)



## 2.3MySQL配置文件

看到你的C:\ProgramData\MySQL\MySQL Server 8.0目录，注意ProgramData是隐藏目录，你需要设置查看隐藏文件才能看得到。

发现下面有个my.ini，这就是MySQL数据库的配置文件，比如字符集、端口号、目录地址等信息都可以在这里配置。

从大体上我们可以看到，my.ini里面有3个部分。

[client]和[mysql]是客户端配置信息，[mysqld]是数据库配置信息

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071353447.png)

提示：[mysql]中默认no-beep表示当数据库发生错误的时候，不要让主板发出蜂鸣器的声音

[mysqld]大致说明如下(已去掉默认注释，不然篇幅太长)

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071353096.png)



# 第3章 SQL语句

数据库是不认识JAVA语言的，但是我们同样要与数据库交互，这时需要使用到数据库认识的语言SQL语句，它是数据库的代码。

结构化查询语言(Structured Query Language)简称SQL，是一种数据库查询和程序设计语言，用于存取数据以及查询、更新和管理关系数据库系统。

创建数据库、创建数据表、向数据表中添加一条条数据信息均需要使用SQL语句。

## 3.1 SQL语句

SQL分类：

数据定义语言：简称DDL(Data Definition Language)，用来定义数据库对象：数据库，表，列等。关键字：create，alter，drop等

数据操作语言：简称DML(Data Manipulation Language)，用来对数据库中表的记录进行更新。关键字：insert，delete，update等

数据控制语言：简称DCL(Data Control Language)，用来定义数据库的访问权限和安全级别，及创建用户。

数据查询语言：简称DQL(Data Query Language)，用来查询数据库中表的记录。关键字：select，from，where等

## 3.2 SQL通用语法

1.SQL语句可以单行或多行书写，以分号结尾

2.可使用空格和缩进来增强语句的可读性

3.MySQL数据库的SQL语句不区分大小写，建议使用大写，例如：SELECT * FROM user。

4.同样可以使用/**/的方式完成注释

5.MySQL中的我们常使用的数据类型如下

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071415025.png)



详细的数据类型如下

<div class="table-box"><table border="1" cellspacing="0"><tbody><tr><td> <p><strong>分类</strong></p> </td><td> <p><strong>类型名称</strong></p> </td><td> <p><strong>说明</strong></p> </td></tr><tr><td colspan="1" rowspan="5"> <p><strong>整数类型</strong></p> </td><td> <p>tinyInt</p> </td><td> <p>很小的整数，1字节</p> </td></tr><tr><td> <p>smallint</p> </td><td> <p>小的整数，2字节</p> </td></tr><tr><td> <p>mediumint</p> </td><td> <p>中等大小的整数，3字节</p> </td></tr><tr><td> <p>int(integer)</p> </td><td> <p>普通大小的整数，4字节</p> </td></tr><tr><td>bigint</td><td>大整数，8字节</td></tr><tr><td rowspan="3"> <p><strong>小数类型</strong></p> </td><td> <p>float</p> </td><td> <p>单精度浮点数，4字节</p> </td></tr><tr><td> <p>double</p> </td><td> <p>双精度浮点数，8字节</p> </td></tr><tr><td> <p>decimal（m,d）</p> </td><td> <p>压缩严格的定点数, m表示数字总位数,d表示保留到小数点后d位，不足部分就添0，如果不设置m、d，默认保存精度是整型</p> </td></tr><tr><td rowspan="5"> <p><strong>日期类型</strong></p> </td><td> <p>year</p> </td><td> <p>年份 YYYY &nbsp;1901~2155，1字节</p> </td></tr><tr><td> <p>time</p> </td><td> <p>时间 HH:MM:SS &nbsp;-838:59:59~838:59:59，3字节</p> </td></tr><tr><td> <p>date</p> </td><td> <p>日期 YYYY-MM-DD 1000-01-01~9999-12-3，3字节</p> </td></tr><tr><td> <p>datetime</p> </td><td> <p>日期时间 YYYY-MM-DD HH:MM:SS 1000-01-01 00:00:00~ 9999-12-31 23:59:59，8字节</p> </td></tr><tr><td> <p>timestamp</p> </td><td> <p>时间戳 YYYY-MM-DD HH:MM:SS &nbsp;1970~01~01 00:00:01 UTC~2038-01-19 03:14:07UTC，4字节</p> </td></tr><tr><td rowspan="12"> <p><strong>文本、二进制类型</strong></p> </td><td> <p>CHAR(M)</p> </td><td> <p>M为0~255之间的整数，固定长度为M，不足后面补全空格</p> </td></tr><tr><td> <p>VARCHAR(M)</p> </td><td> <p>M为0~65535之间的整数</p> </td></tr><tr><td> <p>TINYBLOB</p> </td><td> <p>允许长度0~255字节</p> </td></tr><tr><td> <p>BLOB</p> </td><td> <p>允许长度0~65535字节</p> </td></tr><tr><td> <p>MEDIUMBLOB</p> </td><td> <p>允许长度0~167772150字节</p> </td></tr><tr><td> <p>LONGBLOB</p> </td><td> <p>允许长度0~4294967295字节</p> </td></tr><tr><td> <p>TINYTEXT</p> </td><td> <p>允许长度0~255字节（0 ~ 2^8 - 1）</p> </td></tr><tr><td> <p>TEXT</p> </td><td> <p>允许长度0~65535字节（0 ~ 2^16 - 1）</p> </td></tr><tr><td> <p>MEDIUMTEXT</p> </td><td> <p>允许长度0~167772150字节（2^24 - 1）</p> </td></tr><tr><td> <p>LONGTEXT</p> </td><td> <p>允许长度0~4294967295字节（2^32 - 1）</p> </td></tr><tr><td> <p>VARBINARY(M)</p> </td><td> <p>允许长度0~M个字节的变长字节字符串</p> </td></tr><tr><td> <p>BINARY(M)</p> </td><td> <p>允许长度0~M个字节的定长字节字符串</p> </td></tr></tbody></table></div>

需要注意的是：

> BOOLEAN在数据库保存的是tinyInt类型，false为0，true就是1

> **char**是定长，**varchar**是变长，char存储时，**如果字符数没有达到定义的位数，后面会用空格填充到指定长度，而varchar没达到定义位数则不会填充，按实际长度存储。**

> **char长度固定，char存取速度还是要比varchar要快得多**，方便程序的存储与查找；但是char也为此付出的是空间的代价，因为其长度固定，所以会占据多余的空间，可谓是以空间换取时间效率。varchar则刚好相反，以时间换空间。



## 3.3 数据库操作：database

**创建数据库**

格式:

create database 数据库名;



create database 数据库名 character set 字符集;

例如：

\#创建数据库 数据库中数据的编码采用的是安装数据库时指定的默认编码 utf8

```
CREATE DATABASE day21_1;
```

\#创建数据库 并指定数据库中数据的编码

```
CREATE DATABASE day21_2 CHARACTER SET gbk;
```

\#如果创建之后 修改数据库编码

```
ALTER DATABASE day21_2 CHARACTER SET=utf8;
```

**查看数据库**

查看数据库MySQL服务器中的所有的数据库:

```sql
show databases;
```

查看某个数据库的定义的信息:

show create database 数据库名;

例如：

```sql
show create database day21_1;
```

**删除数据库**

drop database 数据库名称;

例如：

```sql
drop database day21_2;
```



其他的数据库操作命令

**切换数据库：**

格式：use 数据库名;

例如：

```sql
use day21_1;
```



**查看正在使用的数据库:**

```sql
select database();
```

## 3.4 表结构相关语句

### 3.4.1 创建表

格式：

```
create table 表名(
   字段名 类型(长度) 约束,
   字段名 类型(长度) 约束
);
```

例如：

创建分类表

```
CREATE TABLE sort (
  sid INT, #分类ID
  sname VARCHAR(100) #分类名称
);
```

**温馨提示**：你创建了数据库，就创建了一块逻辑空间，实际在磁盘上创建了一个文件夹，你创建了一个表，实际磁盘生成了一个.ibd文件，你可以在C:\ProgramData\MySQL\MySQL Server 8.0\Data目录下验证一下，路径中的ProgramData是隐藏文件夹。

举个例子，你创建了test数据库，然后你执行建表语句如下

```
CREATE TABLE temp(/*实验精度丢失问题*/
	id INT UNSIGNED PRIMARY KEY,
	num DECIMAL(20, 10) /*数字总位数20，保留小数点后10位*/
)
```

实际在你的磁盘上是这样存储的

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071434598.png)

### 3.4.2 查看表

查看数据库中的所有表：

格式：

```
show tables;
```


 图形化结果类似于下图

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071435734.png)

这里的命名就告诉了你是 test 数据库里面的表

查看表结构：

有两种方式

方法一： desc 表名;

方法二： SHOW COLUMNS FROM 表名;

例如：

```
DESC student;
```

```
SHOW COLUMNS FROM student;
```

/* 这两种方式结果一模一样，第一种更常见，显然命令更短你也更愿意用 */
 图形化结果类似于下图

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071435547.png)

### 3.4.3 删除表

格式：drop table 表名;

例如：

```
drop table sort;
```

### 3.4.4 修改表结构格式（实际开发最常用）

alter table 表名 add 列名 类型(长度) 约束;

作用：修改表添加列.

例如：

#1，为分类表添加一个新的字段为 分类描述 varchar(20)

```
ALTER TABLE sort ADD sdesc VARCHAR(20);
当然，想添加多个字段分类怎么做呢？
```

/*添加多个列方法一*/

```
ALTER TABLE student
ADD address VARCHAR(200) NOT NULL,
ADD home_tel CHAR(11) NOT NULL;
/*add语句之间用逗号分隔，最后用分号结束*/
```

/*添加多个列方法二*/

```
ALTER TABLE student
ADD (address VARCHAR(200) NOT NULL,home_tel CHAR(11) NOT NULL);
```

值得注意的是：

值得注意的是：

如果表需要添加多列，而有一列字段home_tel之前已经添加过了，结果会显示Duplicate column name 'home_tel'，那么你本次添加的多列字段都是无效的，即全部添加失败

如果我想将这个字段添加到表中间而不是末尾怎么办呢？

alter table 表名 add 列名 类型(长度) 约束 after 某个字段;

比如我想在age字段的后面加一个字段sex，而不是在最后一个字段末尾添加

```
alter table student add column sex char(1) not null comment '性别' after age;
```

```
alter table 表名 modify 列名 类型(长度) 约束;
```

作用：修改表修改列的类型长度及约束.

例如：

#2, 为分类表的分类名称字段进行修改，类型varchar(50) 添加约束 not null

```
ALTER TABLE sort MODIFY sname VARCHAR(50) NOT NULL; /* 添加约束NOT NULL */

ALTER TABLE student
MODIFY home_tel VARCHAR(20) NOT NULL; /*CHAR(11)修改为VARCHAR(200)*/
```


同理，和add类似，需要修改多个列的类型长度及约束，那么modify语句之间用逗号分隔，最后一句的末尾用分号结束。

alter table 表名 change 旧列名 新列名 类型(长度) 约束;

作用：修改表修改列名.

例如：

#3, 为分类表的分类名称字段进行更换 更换为 snamesname varchar(30)

ALTER TABLE sort CHANGE sname snamename VARCHAR(30);
同理，和add类似，需要修改多个列的字段名，那么change语句之间用逗号分隔，最后一句的末尾用分号结束。

直接来个例题：



假设有2个选项, 选择哪一个

A. ALTER TABLE cource CHANGE cname VARCHAR(30) NOT NULL FIRST;

B. ALTER TABLE cource MODIFY  cname VARCHAR(30) NOT NULL FIRST;

请注意CHANGE和MODIFY的区别, MODIFY可以修改字段类型、字段属性，而CHANGE可修改字段名称，并且CHANGE需要旧列名和新列名，答案是B

注意：change和modify都可以修改表的定义，不同的是change后面需要写两次列名，不太方便，但是change的优点是可以修改列名称，modify则不行。

alter table 表名 drop 列名;

作用：修改表删除列.

例如：

#4, 删除分类表中snamename这列

```
ALTER TABLE sort DROP snamename;

ALTER TABLE student
DROP home_address,
DROP home_tel;
```

同理，和add类似，需要删除多列，那么drop语句之间用逗号分隔，最后一句的末尾用分号结束。

来一道选择题，题目是：删除数据表中多余的列的语句是哪些，有同学上去就选了个B，认为删除就是DELETE，这里的答案是AC。

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071440660.png)

```
rename table 表名 to 新表名;
```

作用：修改表名

例如：

#5, 为分类表sort 改名成 category

```
RENAME TABLE sort TO category;
```


alter table 表名 character set 字符集;

作用：修改表的字符集

例如：

#6, 为分类表 category 的编码表进行修改，修改成 gbk

```
ALTER TABLE category CHARACTER SET gbk;
```

## 3.5 DOS操作数据乱码解决

我们在dos命令行操作中文时，会报错

```
insert into user(username,password) values(‘张三’,’123’);

ERROR 1366 (HY000): Incorrect string value: '\xD5\xC5\xC8\xFD' for column 'username' at row 1
```

原因:因为mysql的客户端编码的问题我们的是utf8,而系统的cmd窗口编码是gbk

解决方案（临时解决方案）:修改mysql客户端编码。

```
show variables like 'character%'; 查看所有mysql的编码

```



![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071442855.png)

在图中与客户端有关的编码设置:

client connetion result 和客户端相关

database server system 和服务器端相关

将客户端编码修改为gbk.

set character_set_results=gbk; / set names gbk;

以上操作，只针对当前窗口有效果，如果关闭了服务器便失效。如果想要永久修改，通过以下方式:

在mysql安装目录下有my.ini文件

default-character-set=gbk 客户端编码设置

character-set-server=utf8 服务器端编码设置



# 第4章 字段属性

主键, 唯一键和自增长.

## 4.1 主键（primary key）

主键: primary key,主要的键. 一张表只能有一个字段可以使用对应的键, 用来唯一的约束该字段里面的数据, 不能重复: 这种称之为主键.

一张表只能有最多一个主键, 主键请尽量使用整数类型而不是字符串类型

### 4.1.1增加主键

SQL操作中有多种方式可以给表增加主键: 大体分为三种.

方案1: 在创建表的时候,直接在字段之后,跟primary key关键字(主键本身不允许为空)

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071452873.png)



​	优点: 非常直接; 缺点: 只能使用一个字段作为主键



方案2: 在创建表的时候, 在所有的字段之后, 使用primary key(主键字段列表)来创建主键(如果有多个字段作为主键,可以是复合主键)

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071452377.png)

方案3: 当表已经创建好之后, 额外追加主键: 可以通过修改表字段属性, 也可以直接追加.

```
Alter table 表名  add primary key(字段列表);
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071452618.png)



创建约束的目的就是保证数据的完整性和一致性。

主键对应的字段中的数据必须唯一，且不能为NULL， 一旦重复,数据操作失败(增和改)

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071453624.png)



### 4.1.2 主键约束

创建约束的目的就是保证数据的完整性和一致性。

主键对应的字段中的数据必须唯一，且不能为NULL， 一旦重复,数据操作失败(增和改)

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071612575.png)

建议主键使用数字类型，因为数字的检索速度非常快，并且主键如果是数字类型，还可以设置自动增长。

主键的原理其实就是一个**计数器**。



### 4.1.3 更新主键 & 删除主键

没有办法更新主键: 主键必须先删除,才能增加.

```
Alter table 表名 drop primary key;


```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071613669.png)

4.1.4 主键分类
在实际创建表的过程中, 很少使用真实业务数据作为主键字段(业务主键,如学号,课程号); 大部分的时候是使用逻辑性的字段(字段没有业务含义,值是什么都没有关系), 将这种字段主键称之为逻辑主键.

```
Create table my_student(

Id int primary key auto_increment comment ‘逻辑主键: 自增长’, -- 逻辑主键

Number char(10) not null  comment ‘学号’,

Name varchar(10) not null

)


```

## 4.2 自动增长（auto_increment）

自增长: 当对应的字段,不给值,或者说给默认值,或者给NULL的时候, 会自动的被系统触发, 系统会从当前字段中已有的最大值再进行+1操作,得到一个新的在不同的字段.

自增长的字段必须定义为主键，默认起始值是1而不是0



### 4.2.1 新增自增长

自增长特点: 

  任何一个字段要做自增长必须前提是本身是一个索引(key一栏有值)，**auto_increment**表示自动编号

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071626433.png)



自增长字段必须是数字(整型)

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071627300.png)

### 4.2.2 自增长使用

当自增长被给定的值为NULL或者默认值的时候会触发自动增长.

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071627551.png)

增长如果对应的字段输入了值,那么自增长失效: 但是下一次还是能够正确的自增长(从最大值+1)

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071630139.png)

如何确定下一次是什么自增长呢? 可以通过查看表创建语句看到.

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071639179.png)

### 4.2.3 修改自增长

自增长如果是涉及到字段改变: 必须先删除自增长,后增加(一张表只能有一个自增长)



修改当前自增长已经存在的值: 修改只能比当前已有的自增长的最大值大,不能小(小不生效)

Alter table 表名 auto_increment  = 值;

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071639690.png)

向上修改可以

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071640713.png)



思考: 为什么自增长是从1开始?为什么每次都是自增1呢?

所有系统的变现(如字符集,校对集)都是由系统内部的变量进行控制的.

查看自增长对应的变量: show variables like ‘auto_increment%’;

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071643018.png)

可以修改变量实现不同的效果: 修改是对整个数据修改,而不是单张表: (修改是会话级)

Set auto_increment_increment = 5; -- 一次自增5

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071643513.png)

测试效果: 自动使用自增长

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071643829.png)

### 4.2.4 删除自增长

自增长是字段的一个属性: 可以通过modify来进行修改(保证字段没有auto_increment即可)

```
Alter table 表名 modify 字段 类型;
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071644613.png)

## 4.3 唯一键（unique/ unique key）

一张表往往有很多字段需要具有唯一性,数据不能重复: 但是一张表中只能有一个主键: 唯一键(unique key)就可以解决表中有多个字段需要唯一性约束的问题.

唯一键的本质与主键差不多: 唯一键默认的允许自动为空,而且可以多个为空(空字段不参与唯一性比较)

### 4.3.1 增加唯一键

基本与主键差不多: 三种方案

方案1: 在创建表的时候,**字段之后**直接跟**unique/ unique key**

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071645423.png)

方案2: 在所有的字段之后增加unique key(字段列表); -- 复合唯一键

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071645096.png)

方案3: 在创建表之后增加唯一键

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071646352.png)

### 4.3.2 唯一键约束

唯一键与主键本质相同: 唯一的区别就是唯一键默认允许为空,而且是多个为空.

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071646081.png)

如果唯一键也不允许为空: 与主键的约束作用是一致的.

4.3.3 更新唯一键 & 删除唯一键
更新唯一键

先删除后新增(唯一键可以有多个: 可以不删除).

删除唯一键

Alter table 表名 drop unique key; -- 错误: 唯一键有多个

Alter table 表名 drop index 索引名字; -- 唯一键默认的使用字段名作为索引名字

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071647313.png)s

## 4.4 外键（foreign key）

外键: foreign key, 外面的键(键不在自己表中): 如果一张表中有一个字段(非主键)指向另外一张表的主键,那么将该字段称之为外键.



### 4.4.1 增加外键

外键可以在创建表的时候或者创建表之后增加(但是要考虑数据的问题).

一张表可以有多个外键.

创建表的时候增加外键: 在所有的表字段之后,使用foreign key(外键字段) references 外部表(主键字段)

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071652744.png)

在新增表之后增加外键: 修改表结构

```
Alter table 表名 add [constraint 外键名字] foreign key(外键字段) references 父表(主键字段);
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071653410.png)

### 4.4.2 修改外键&删除外键

外键不可修改

只能先删除后新增.

删除外键语法

Alter table 表名 drop foreign key 外键名; -- 一张表中可以有多个外键,但是名字不能相同

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071654201.png)

### 4.4.3 外键作用

外键默认的作用有两点: 一个对父表,一个对子表(外键字段所在的表)



对子表约束: 子表数据进行写操作(增和改)的时候, 如果对应的外键字段在父表找不到对应的匹配: 那么操作会失败.(约束子表数据操作)

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071655577.png)

对父表约束: 父表数据进行写操作(删和改: 都必须涉及到主键本身), 如果对应的主键在子表中已经被数据所引用, 那么就不允许操作

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071701061.png)

![](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071702463.png)





4.4.4 外键条件
1.外键要存在: 首先必须保证表的存储引擎是innodb(默认的存储引擎): 如果不是innodb存储引擎,那么外键可以创建成功,但是没有约束效果.
2.外键字段的字段类型(列类型)必须与父表的主键类型完全一致.
3.一张表中的外键名字不能重复.
4,增加外键的字段(数据已经存在),必须保证数据与父表主键要求对应.

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071706426.png)

### 4.4.5 外键约束

所谓外键约束: 就是指外键的作用.

之前所讲的外键作用: 是默认的作用; 其实可以通过对外键的需求, 进行定制操作.

需要注意的是：外键约束的定义是写在子表上的，但是不推荐使用外键约束

MySQL字段约束有四种，主键约束，非空约束，唯一约束，外键约束。外键约束是唯一不推荐的约束

提示：主键约束其实就是非空约束和唯一约束合二为一的情况

外键约束有三种约束模式: 都是针对父表的约束(子表约束父表)

District: 严格模式(默认的), 父表不能删除或者更新一个已经被子表数据引用的记录

Cascade: 级联模式: 父表的操作, 对应子表关联的数据也跟着被删除

Set null: 置空模式: 父表的操作之后,子表对应的数据(外键字段)被置空

通常的一个合理的做法(约束模式): 删除的时候子表置空, 更新的时候子表级联操作

指定模式的语法

Foreign key(外键字段) references 父表(主键字段) on delete set null on update cascade;
![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071702926.png)

更新操作: 级联更新

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071702252.png)

删除操作: 置空

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071703864.png)

删除置空的前提条件: 外键字段允许为空(如果不满足条件,外键无法创建)

外键虽然很强大, 能够进行各种约束: 但是对于PHP来讲, 外键的约束降低了PHP对数据的可控性: 通常在实际开发中, 很少使用外键来处理.

### 4.4.6 创建外键约束的要求

创建外键约束的目的是保持数据一致性和完整性，以及实现一对一或者一对多的关系。

创建外键约束要求有以下几点：

1. 父表和子表必须使用相同的存储引擎，而且禁止使用临时表。

注意：具有外键列的表称为子表；子表所参照的表称为父表。

2. 数据表的存储引擎只能是InnoDB。

3. 外键列和参照列必须具有相似的数据类型。其中数字的长度或是否有符号位必须相同；而字符的长度则可以不同。

注意：加 FOREIGN KEY 关键字的列称为外键列；外键列所参照的列称为参照列。

4. 外键列和参照列必须创建索引。如果外键列不存在索引的话，MySQL将自动创建索引。如果参照列不存在索引的话，MySQL不会自动创建索引。

注意:MySQL会为主键自动创建索引
4.4.7 外键约束的闭环问题
比如说我们创建了2张表

```
/*先创建父表*/
CREATE TABLE t_dept(
	deptno INT UNSIGNED PRIMARY KEY,
	dname VARCHAR(20) NOT NULL UNIQUE,
	tel CHAR(4) UNIQUE
)
/*再创建子表*/
CREATE TABLE t_emp(
	empno INT UNSIGNED PRIMARY KEY,
	ename VARCHAR(20) NOT NULL,
	sex ENUM("男", "女") NOT NULL,
	deptno INT UNSIGNED NOT NULL,
	hiredate DATE NOT NULL,
	FOREIGN KEY (deptno) REFERENCES t_dept(deptno)
);
```


父表t_dept加一个数据如下：


![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071704932.png)

子表t_emp加一个数据如下：

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071705652.png)

此时我想删除父表的数据，结果报错

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203071705733.png)

结果发现有子表t_emp外键约束着父表，删除失败。必须先删除子表的约束数据才能删除父表的数据，那这样就失去了增减改查的灵活性了，并且更严重的是，

如果形成**外键闭环**，我们将无法删除任何一张表的数据记录。

![img](https://img-blog.csdnimg.cn/2020120111223113.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0MTE1ODk5,size_16,color_FFFFFF,t_70)

如上图，A约束B，B约束C......，这样每一个表都算作父表，所谓的先删除子表的数据就是不可能的。**因为有外键闭环的存在，所以我们不推荐外键约束**

# 4.5 索引

几乎所有的索引都是建立在字段之上.

索引: 系统根据某种算法, 将已有的数据(未来可能新增的数据),单独建立一个文件: 文件能够实现快速的匹配数据, 并且能够快速的找到对应表中的记录.

## 4.5.1 创建索引

建表的时候创建索引，也可以在已存在的表上添加索引。

```
CREATE TABLE 表名称(
       ......,
       INDEX [索引名称] (字段),
       ......
);
```

```
CREATE TABLE t_message(
	id INT UNSIGNED PRIMARY KEY,
	content VARCHAR(200) NOT NULL,
	type ENUM("公告", "通报", "个人通知") NOT NULL,
	create_time TIMESTAMP NOT NULL,
	INDEX idx_type (type)
);
```

## 4.5.2 添加索引

向已存在的表中添加索引的方式如下

```
普通索引：

CREATE INDEX 索引名称 ON 表名(字段);  /*添加索引方式1*/

ALTER TABLE 表名 ADD INDEX 索引名称(字段); /*添加索引方式2*/

唯一索引：

CREATE UNIQUE INDEX 索引名称 ON 表名(字段)

联合索引：

CREATE INDEX 索引名称 ON 表名(字段1，字段2...)
\
```

 经常被用来做检索条件的字段需要加上索引，原理是B+树，所以查询很快。如果是几千条数据，不必加索引，全表扫描也很快



### 4.5.3 查询索引

SHOW INDEX FROM 表名;

```sql
/*查看t_message表的索引*/

SHOW INDEX FROM t_message;
```

 查出来如下，有添加的普通索引和主键索引

![image-20220308103825913](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081038203.png)

### 4.5.4 删除索引

DROP INDEX 索引名称 ON 表名;

```sql
/* 在t_message表中删除idx_type索引 */

DROP INDEX idx_type ON t_message;
```



### 4.5.5 索引的使用原则

1. 数据量很大，且经常被查询的数据表可以设置索引 (即读多写少的表可以设置索引)

2. 索引只添加在经常被用作检索条件的字段上 (比如电子商城需要在物品名称关键字加索引)
3. 不要在大字段上创建索引 (比如长度很长的字符串不适合做索引，因为查找排序时间变的很长)

## 4.5.6 索引的意义

提升查询数据的效率
约束数据的有效性(唯一性等)
增加索引的前提条件: 索引本身会产生索引文件(有时候有可能比数据文件还大) ,会非常耗费磁盘空间.

如果某个字段需要作为查询的条件经常使用, 那么可以使用索引(一定会想办法增加);

如果某个字段需要进行数据的有效性约束, 也可能使用索引(主键,唯一键)

Mysql中提供了多种索引

主键索引: primary key
唯一索引: unique key
全文索引: fulltext index
普通索引: index
全文索引: 针对文章内部的关键字进行索引

全文索引最大的问题: 在于如何确定关键字

英文很容易: 英文单词与单词之间有空格

中文很难: 没有空格, 而且中文可以各种随意组合(分词: sphinx)

## 4.5.7 MySQL索引原理图解、B+树应用场景大全、索引优化、索引成本计算等

在线网址：https://blog.csdn.net/qq_34115899/article/details/118004118

https://blog.csdn.net/qq_34115899/article/details/118308424

https://blog.csdn.net/qq_34115899/article/details/120727513

# 第5章 关系

将实体与实体的关系, 反应到最终数据库表的设计上来: 将关系分成三种: 一对一, 一对多(多对一)和多对多.

所有的关系都是指的表与表之间的关系.

## 5.1 一对一

一对一: 一张表的一条记录一定只能与另外一张表的一条记录进行对应; 反之亦然.

学生表: 姓名,性别,年龄,身高,体重,婚姻状况, 籍贯, 家庭住址,紧急联系人

<div class="table-box"><table border="1" cellspacing="0"><tbody><tr><td> <p>Id(P)</p> </td><td> <p>姓名</p> </td><td> <p>性别</p> </td><td> <p>年龄</p> </td><td> <p>体重</p> </td><td> <p>身高</p> </td></tr><tr><td> <p>1</p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td></tr><tr><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td></tr></tbody></table></div>

表设计成以上这种形式: 符合要求. 其中姓名,性别,年龄,身高,体重属于常用数据; 但是婚姻,籍贯,住址和联系人属于不常用数据. 如果每次查询都是查询所有数据,不常用的数据就会影响效率, 实际又不用.

解决方案: 将常用的和不常用的信息分离存储,分成两张表

常用信息表

<div class="table-box"><table border="1" cellspacing="0"><tbody><tr><td> <p>Id(P)</p> </td><td> <p>姓名</p> </td><td> <p>性别</p> </td><td> <p>年龄</p> </td><td> <p>体重</p> </td><td> <p>身高</p> </td></tr><tr><td> <p>1</p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td></tr><tr><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td></tr></tbody></table></div>

不常用信息表: 保证不常用信息与常用信息一定能够对应上: 找一个具有唯一性(确定记录)的字段来共同连接两张表

<div class="table-box"><table border="1" cellspacing="0"><tbody><tr><td> <p>Id(P)</p> </td><td> <p>婚姻</p> </td><td> <p>籍贯</p> </td><td> <p>住址</p> </td><td> <p>联系人</p> </td></tr><tr><td> <p>2</p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td></tr><tr><td> <p>1</p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td></tr></tbody></table></div>

一个常用表中的一条记录: 永远只能在一张不常用表中匹配一条记录;反过来,一个不常用表中的一条记录在常用表中也只能匹配一条记录: 一对一的关系



## 5.2 一对多

一对多: 一张表中有一条记录可以对应另外一张表中的多条记录; 但是返回过, 另外一张表的一条记录只能对应第一张表的一条记录. 这种关系就是一对多或者多对一.



母亲与孩子的关系: 母亲,孩子两个实体

妈妈表

<div class="table-box"><table border="1" cellspacing="0"><tbody><tr><td> <p>ID(P)</p> </td><td> <p>名字</p> </td><td> <p>年龄</p> </td><td> <p>性别</p> </td></tr><tr><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td></tr><tr><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td></tr></tbody></table></div>

孩子表

<div class="table-box"><table border="1" cellspacing="0"><tbody><tr><td> <p>ID(P)</p> </td><td> <p>名字</p> </td><td> <p>年龄</p> </td><td> <p>性别</p> </td></tr><tr><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td></tr><tr><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td><td> <p></p> </td></tr></tbody></table></div>

以上关系: 一个妈妈可以在孩子表中找到多条记录(也有可能是一条); 但是一个孩子只能找到一个妈妈: 是一种典型的一对多的关系.

但是以上设计: 解决了实体的设计表问题, 但是没有解决关系问题: 孩子找不出妈,妈也找不到孩子.

解决方案: 在某一张表中增加一个字段,能够找到另外一张表的中记录: 应该在孩子表中增加一个字段指向妈妈表: 因为孩子表的记录只能匹配到一条妈妈表的记录.


## 5.3多对多

多对多: 一张表中(A)的一条记录能够对应另外一张表(B)中的多条记录; 同时B表中的一条记录也能对应A表中的多条记录: 多对多的关系



老师教学: 老师和学生

//增加中间表



## 5.4关系总结

对于一对一时   可分为常用查询和不常用查询

对于一对多时和多对一时  可增加对应的主键字段属性

对于多对多  可以创建中间表 通过主键id来达到一对多的性质



# 第6章 范式

范式: Normal Format, 是一种离散数学中的知识, 是为了解决一种数据的存储与优化的问题: 保存数据的存储之后, 凡是能够通过关系寻找出来的数据,坚决不再重复存储: 终极目标是为了减少数据的冗余.

范式: 是一种分层结构的规范, 分为六层: 每一次层都比上一层更加严格: 若要满足下一层范式,前提是满足上一层范式.

六层范式: 1NF,2NF,3NF...6NF, 1NF是最底层,要求最低;6NF最高层,最严格.

Mysql属于关系型数据库: 有空间浪费: 也是致力于节省存储空间: 与范式所有解决的问题不谋而合: 在设计数据库的时候, 会利用到范式来指导设计.

但是数据库不单是要解决空间问题,要保证效率问题: 范式只为解决空间问题, 所以数据库的设计又不可能完全按照范式的要求实现: 一般情况下,只有前三种范式需要满足.

范式在数据库的设计当中是有指导意义: 但是不是强制规范.

## 6.1 1NF

第一范式: 在设计表存储数据的时候, 如果表中设计的字段存储的数据,在取出来使用之前还需要额外的处理(拆分),那么说表的设计不满足第一范式。

第一范式要求字段的数据具有原子性: 不可再分.

第一范式是数据库的基本要求，不满足第一范式就不是关系型数据库

让我们简单化这个问题：


1NF---**原子性**   不可拆分

数据表的每一列都是不可分割的基本数据项，同一列中不能有多个值，也不能存在重复的属性。

## 6.2 2NF

第二范式: 在数据表设计的过程中,如果有复合主键(多字段主键), 且表中有字段并不是由整个主键来确定, 而是依赖主键中的某个字段(主键的部分): 存在字段依赖主键的部分的问题, 称之为部分依赖: 第二范式就是要解决表设计不允许出现部分依赖.

定义太绕了，简单点:

2NF---唯一性     数据唯一

数据表中的每条记录必须是唯一的。为了实现区分，通常要为表加上一个列来存储唯一标识，这个唯一属性列被称作主键列

## 6.3 3NF

要满足第三范式,必须满足第二范式

第三范式: 理论上讲,应该一张表中的所有字段都应该直接依赖主键(逻辑主键: 代表的是业务主键), 如果表设计中存在一个字段, 并不直接依赖主键,而是通过某个非主键字段依赖,最终实现依赖主键: 把这种不是直接依赖主键,而是依赖非主键字段的依赖关系称之为传递依赖. 第三范式就是要解决传递依赖的问题.

定义很绕，我们简单点：

3NF---关联性    

每列都与主键有直接关系，不存在传递依赖

## 6.4 逆规范化

有时候, 在设计表的时候,如果一张表中有几个字段是需要从另外的表中去获取信息. 理论上讲, 的确可以获取到想要的数据, 但是就是效率低一点. 会刻意的在某些表中,不去保存另外表的主键(逻辑主键), 而是直接保存想要的数据信息: 这样一来,在查询数据的时候, 一张表可以直接提供数据, 而不需要多表查询(效率低), 但是会导致数据冗余增加.


# 第7章 数据操作

## 7.1 新增数据

基本语法

```
Insert into 表名 [字段1,字段2,......] values (值1,值2,......); /*插入单条记录*/

Insert into 表名 [字段1,字段2,......] values (值1,值2,......), (值1,值2,......); /*插入多条记录*/
```

表名后面不写字段列表也可以插入数据，但是会影响速度。Mysql会进行词法分析，找到对应表结构，然后自动给你补上字段列表。所以表名后面不写字段列表，数据库难以高效的操作。

### 7.1.1 IGNORE关键字

IGNORE关键字只会插入数据库不存在的记录。比如主键冲突、唯一性冲突，数据库会报错，加上IGNORE之后数据库会忽略这条数据不会报错。

INSERT [IGNORE] INTO 表名 ......;

```sql
INSERT IGNORE INTO t_dept(deptno, dname, loc)

VALUES(70, "A", "北京"), (80, "B", "上海"); /*70部门已经存在*/
```

 

### 7.1.2 主键冲突

当主键存在冲突的时候(Duplicate key)，你可以添加ignore关键字选择忽略，数据库不会报错，但是确实非得添加这个记录怎么办呢？可以选择性的进行处理: 更新和替换

主键冲突：更新操作

```
Insert into 表名[(字段列表:包含主键)] values(值列表) on duplicate key update 字段 = 新值; (这个语法sql单独执行没问题，在mybatis会报错，找不到你想要的参数)
```

主键冲突: **替换**

```
**Replace** into 表名 [(字段列表:包含主键)] values(值列表);
```

### 7.1.3 蠕虫复制

蠕虫复制: 从已有的数据中去获取数据,然后将数据又进行新增操作: 数据成倍的增加.



表创建高级操作: 从已有表创建新表(复制表结构)

```
Create table 表名 like 数据库.表名
```



![image-20220308113851437](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081138241.png)

蠕虫复制: 先查出数据, 然后将查出的数据新增一遍

```
Insert into 表名[(字段列表)] select 字段列表/* from 数据表名;  数据操作
```

![image-20220308114208855](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081142655.png)

蠕虫复制的意义

从已有表拷贝数据到新表中
可以迅速的让表中的数据膨胀到一定的数量级: 测试表的压力以及效率



## 7.2 更新数据

基本语法

```
UPDATE [IGNORE] 表名 SET 字段1=值1, 字段2=值2, ......
[WHERE 条件1 ......]
[ORDER BY ......]
[LIMIT ......];
```

注意，如果这里有limit关键字，那么后面只能跟一个参数，即表示取前多少条数据，这里的limit不能有2个参数，ignore表示更新失败就直接忽略而不是报错。

eg1：把每个员工的编号和他上司的编号+1，用order by子句完成

```
UPDATE t_emp SET empno=empno+1, mgr=mgr+1
ORDER BY empno DESC;
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081146239.png)

eg2：把月收入前三名的员工底薪减100元，用LIMIT子句完成

```
UPDATE t_emp
SET sal=sal-100s
ORDER BY sal+IFNULL(comm,0) DESC
LIMIT 3;
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081146089.png)

eg3：把10部门中，工龄达到20年的员工，底薪增加200元

```
UPDATE t_emp
SET sal=sal+200
WHERE deptno=10 AND DATEDIFF(NOW(),hiredate)/365 >= 20
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081147512.png)

### 7.2.1 UPDATE语句中的内连接



因为相关子查询效率非常低，所以我们可以利用表连接的方式来改造UPDATE语句

```
UPDATE 表1 JOIN 表2 ON 条件
SET 字段1=值1, 字段2=值2, ......;
```

引申出另一种写法

```
UPDATE 表1 JOIN 表2
SET 字段1=值1, 字段2=值2, ......
WHERE 条件;
```

表连接的UPDATE语句可以修改多张表的记录

eg：把ALLEN调往RESEARCH部门，职务调整为ANALYST

```
/*表连接的几种写法*/
UPDATE t_emp e JOIN t_dept d ON e.ename="ALLEN" AND d.dname="RESEARCH"
SET e.deptno=d.deptno, e.job="ANALYST"

UPDATE t_emp e JOIN t_dept d
SET e.deptno=d.deptno, e.job="ANALYST"
WHERE e.ename="ALLEN" AND d.dname="RESEARCH"

UPDATE t_emp e,t_dept d
SET e.deptno=d.deptno, e.job="ANALYST"
WHERE e.ename="ALLEN" AND d.dname="RESEARCH"
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081149419.png)



分析：其实利用的是笛卡尔积，笛卡尔积一般对于我们连接没什么用，恰恰这里就起了作用，这个例子可以好好推敲一下，表连接的条件直接将ALLEN这个人连接到RESEARCH部门，RESEARCH部门号是20，赋值给ALLEN的部门号就成功修改，接着修改职务即可。

eg：把底薪低于公司平均底薪的员工，底薪增加150元

sql语句如下

```
UPDATE t_emp e JOIN
(SELECT AVG(sal) avg FROM t_emp) t
ON e.sal<t.avg
SET e.sal=e.sal+150;
```

执行结果就不演示了，从逻辑上也很好理解。

### 7.2.2 UPDATE语句中的外连接

UPDATE语句的表连接既可以是内连接，又可以是外连接。

基本语法

```
**UPDATE 表1 [LEFT | RIGHT] JOIN 表2 ON 条件
SET 字段1=值1, 字段2=值2, ......;**
```

**eg：**把没有部门的员工，或者SALES部门低于2000元底薪的员工，都调往20部门

```
UPDATE t_emp e LEFT JOIN t_dept d ON e.deptno=d.deptno
SET e.deptno=20
WHERE e.deptno IS NULL OR (d.dname="SALES" AND e.sal<2000);
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081154407.png)

## 7.3 删除数据

基本语法

```
DELETE [IGNORE] FROM 表名
[WHERE 条件1, 条件2, ...]
[ORDER BY ...]
[LIMIT ...];
```

子句执行顺序：FROM -> WHERE -> ORDER BY -> LIMIT -> DELETE

ignore表示删除失败就直接忽略而不是报错。

有了前面新增、更新数据的基础，下面的例子我就不展示数据表的变化了，基本语法比较容易理解。

eg1：删除10部门中，工龄超过20年的员工记录

```
DELETE from t_emp
WHERE deptno=10 AND DATEDIFF(NOW(),hiredate)/365 >20;
```

**eg2：**删除20部门中工资最高的员工记录

```sql

DELETE FROM t_emp
WHERE deptno=20
ORDER BY sal+IFNULL(comm,0) DESC
LIMIT 1;
```

提示：如果表中存在主键自增长,那么当删除之后, 自增长不会还原，下一条数据记录插入会在上一次计数的基础继续增加

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081156088.png)

### 7.3.1 DELETE语句中的内连接

因为相关子查询的效率非常低，所以我们可以利用表连接的方式来改造DELETE语句

```
**DELETE 表1, ... FROM 表1 JOIN 表2 ON 条件
[WHERE 条件1, 条件2, ...]
[ORDER BY ...]
[LIMIT ...];**
```

**eg1：**删除SALES部门该部门的全部员工记录

```
DELETE e,d
FROM t_emp e JOIN t_dept d ON e.deptno=d.deptno
WHERE d.dname="SALES";
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081157949.png)



**eg2：**删除每个低于部门平均底薪的员工记录

```
DELETE e
FROM t_emp e JOIN
(SELECT deptno, AVG(sal) avg FROM t_emp GROUP BY deptno) t
ON e.deptno=t.deptno AND e.sal<t.avg;
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081158127.png)

**eg3：**删除员工KING和他的下属的员工记录，用表连接实现

```
DELETE e
FROM t_emp e JOIN
(SELECT empno FROM t_emp WHERE ename="KING") t
ON e.mgr=t.empno OR e.empno=t.empno;
```

注意，t 这个临时表是不能删除的，表连接出来的记录就是KING的员工下属和KING本身，删除e即可满足要求。数据表的图示操作就不演示了。

### 7.3.2 DELETE语句中的外连接

基本语法

```
DELETE 表1, ... FROM 表1 [LEFT | RIGHT] JOIN 表2 ON 条件
[WHERE 条件1, 条件2, ...]
[ORDER BY ...]
[LIMIT ...]
```

eg：删除SALES部门的员工，以及没有部门的员工

这里注意对比上一小节第一个例题，上一小节是删除SALES部门的员工，这里还要删除没有部门的员工，这就是内连接和外连接在这里使用的区别。

```
DELETE e
FROM t_emp e LEFT JOIN t_dept d ON e.deptno=d.deptno
WHERE d.dname="SALES" OR e.deptno IS NULL;
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081346848.png)

### 7.3.3 快速删除数据表全部记录  (删除数据)

DELETE语句是在事务机制下删除记录，删除记录之前，先把要删除的记录保存到日志文件里，然后再删除记录。

TRUNCATE语句在事务机制之外删除记录，速度远超过DELETE语句。

语法

```
**TRUNCATE TABLE 表名;**
```

注意：

1. drop（drop table 表名）是完全删除表，包括表结构，数据库就查不到这个表了
2. delete（delete from 表名）是删除表数据，保留表的结构，数据库中该表还存在，如果加where条件，可以只删除一行或者多行，下次插入id不会从1开始，而是从最后一次插入的id+1开始
3. truncate （truncate table 表名）只能删除全表数据，会保留表结构，数据库中该表还存在，下次插入id从1开始


## 7.4 查询数据

完整语法

```
Select [字段别名]/* from 数据源 [where条件子句] [group by子句] [having子句] [order by子句] [limit 子句];
```



### 7.4.1 Select语句

最基本的查询语句就是SELECT和FROM关键字组成，SELECT语句屏蔽了物理层的操作，用户不必关系数据的真实存储，交互由数据库高效的查询数据。

All或者*: 默认保留所有的结果

Distinct: 去重, 查出来的结果,将重复给去除(所有字段都相同)

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081349044.png)

### 7.4.2 去重查询

语法格式

```
SELECT DISTINCT 字段 FROM 表名;
```

**注意点：**

**1.distinct关键字只能在select子句中使用一次**

**2.distinct关键字只能写在select子句的第一个字段前面，否则报错，若有多个字段，则只有多个字段的值都相同的情况才会被认为是重复记录，distinct才会生效**

**3.若有多个字段，即使写在第一个字段前面，而多个字段的值并不完全相同，distinct也失效。**



### 7.4.3 字段别名

字段别名: 当数据进行查询出来的时候, 有时候名字并不一定就满足需求(多表查询的时候, 会有同名字段). 需要对字段名进行重命名: 别名

语法

```
字段名 [as] 别名;
```

**小细节：查询语句的执行顺序是先词法分析与优化，读取SQL语句，然后FROM子句选择数据来源，最后SELECT子句选择输出内容**

### 7.4.4 数据源

数据源: 数据的来源, 关系型数据库的来源都是数据表。本质上只要保证数据类似二维表，最终都可以作为数据源。

数据源分为多种: 单表数据源, 多表数据源, 查询语句

```

单表数据源: select * from 表名;
```

```
多表数据源: select* from 表名1,表名2...;
```

从一张表中取出一条记录，去另外一张表中匹配所有记录，而且全部保留(记录数和字段数)，将这种结果称为笛卡尔积(交叉连接)，笛卡尔积没什么用，所以应该尽量避免。只要没有条件，查询多表就会产生**笛卡尔积**。



**子查询**: 数据的来源是一条查询语句(查询语句的结果是二维表)

```
Select * from (select 语句) as 表名;
```

### 7.4.5 Where子句

Where子句: 用来判断数据,筛选数据.

Where子句返回结果: 0或者1, 0代表false,1代表true.

语法格式

```
SELECT ... FROM ... WHERE 条件 [AND | OR] 条件 ......;
```

判断条件:

比较运算符: >, <, >=, <= ,!= ,<>, =, like, between and, in/not in

逻辑运算符: &&(and), ||(or), !(not)

where语句使用的注意事项：

       WHERE子句中，条件执行的顺序是从左到右的。所以我们应该把索引条件或者筛选掉记录最多的条件写在最左侧。因为索引查询速度快，筛选记录最多的条件更容易触发短路语句的效果，这样就无须执行后续条件就能完成查询。

小提示：子句的执行顺序是**FROM -> WHERE -> SELECT -> ORDER BY -> LIMIT**，先选择数据来源，再进行条件筛选，根据筛选完的记录选择输出内容，接着进行排序，最后选择显示的限定条件



### 7.4.6 聚合函数

聚合函数在数据查询分析中，应用十分广泛。聚合函数可以对数据求和、求最大值和最小值、求平均值等等。

比如SQL提供了如下聚合函数

Count(): 统计分组后的记录数: 每一组有多少记录

Max(): 统计每组中非空的最大值

Min(): 统计非空的最小值

Avg(): 统计平均值

Sum(): 统计和

LENGTH()：统计字符个数

### 7.4.7 Group by子句

为什么要分组呢？因为默认情况下汇总函数是对全表范围内的数据做统计。

Group by:主要用来分组查询, 通过一定的规则将一个数据集划分为若干个小的区域，然后针对每个小区域分别进行数据汇总处理。也就是根据某个字段进行分组(相同的放一组,不同的分到不同的组)

```
基本语法: group  by 字段名;
```

eg：根据不同的部门号分组显示平均工资

```
SELECT deptno, ROUND(AVG(sal)) FROM t_emp GROUP BY deptno;/*round四舍五入为整数*/
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081401364.png)

**逐级分组**

数据库支持多列分组条件，执行的时候逐级分组

eg：查询每个部门里，每种职位的人员数量和平均底薪

```
SELECT deptno, job, COUNT(*), AVG(sal)
FROM t_emp
GROUP BY deptno, job
ORDER BY deptno;
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081401450.png)

这里千万千万要注意一个硬性要求！

如果查询语句中含有GROUP BY子句，那么SELECT子句中的内容必须遵守如下约定：

       SELECT子句中可以包含聚合函数或者GROUP BY子句的分组列，其余内容均不可以出现在SELECT子句中。否则查询的结果根本没有任何意义，甚至你自己根本看不懂为什么出现这个结果。任何时候看到GROUP BY 马上检查SELECT子句，若有其他字段，不用往下分析，肯定是混乱的查询。
**GROUP_CONCAT函数**

​    这个函数可以把分组查询中的某个字段拼接成一个字符串

eg：查询每个部门内底薪超过2000元的人数和员工姓名

```
SELECT deptno, COUNT(*), GROUP_CONCAT(ename)
FROM t_emp
WHERE sal >= 2000
GROUP BY deptno;
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081403445.png)

### 7.4.8 Having子句

Having子句与where子句一样是进行条件判断的.

有同学会问了，和where子句功能一样，那还有什么用，多此一举？

eg1：查询部门平均底薪超过2000的员工数量，你是不是会这样写？

```
SELECT deptno, COUNT(*)
FROM t_emp
WHERE AVG(sal) >= 2000
GROUP BY deptno;
```

![img](https://gitee.com/zhou-kaifa/images/raw/master/Images/202203081406401.png)

结果运行出错，我们前面也说了，WHERE子句不允许出现聚合函数。而且WHERE优先级高于GROUP BY，在条件筛选的时候不知道按照什么范围去筛选，是全部数据筛选还是分部门数据筛选呢？

解决方案来了，那就是HAVING子句，HAVING子句的出现主要是为了WHERE子句不能使用聚合函数的问题，HAVING子句不能独立存在，必须依赖于GROUP BY子句而存在，GROUP BY 执行完成就立即执行HAVING子句

```
SELECT deptno, COUNT(*)
FROM t_emp
GROUP BY deptno HAVING AVG(sal) >= 2000;
```

**HAVING的出现是不是可以完全替换WHERE？**

1那肯定是不行的，**Where是针对磁盘数据进行判断****，**进入到内存之后会进行分组操作****，分组结果就需要having来处理.

2从功能上来说，上面两种写法没有什么区别，但是WHERE优先级在GROUP BY之前，是先把数据按条件筛选完了再分组好呢，还是分完组再去筛选好呢？肯定是前者。所以WHERE能完成的就用WHERE完成，不要放到HAVING中。大量的数据从磁盘读取到内容代价比较大，先筛选完了，再把符合条件的记录读取到内存中显然更好。
3Having能做where能做的几乎所有事情, 但是where却不能做having能做的很多事情.

### 7.4.9 Order by子句

Order by: 排序, 根据某个字段进行升序或者降序排序, 依赖校对集.

```
Order by 字段名 [asc|desc]; -- asc是升序(默认的),desc是降序
```

### 7.4.10 Limit子句

Limit子句是一种限制结果的语句，用来做**数据分页**的。

比如我们看朋友圈，只会加载少量的部分信息，不会一次性加载全部朋友圈，那样只会浪费CPU时间、内存、网络带宽。而结果集的记录可能很多，可以使用limit关键字限定结果集数量。



### 7.4.11 select语句中各关键字的先后顺序

(1) from 
(2) on
(3) join
(4) where （可以使用表的别名）
(5) group by(可以开始使用select中字段的别名（不是表的别名），后面的语句中都可以使用)
(6) having + 聚合函数
(7) select 
(8) distinct 
(9) order by
(10) limit 

1~3是table部分——形成表

4~6是filter部分——过滤条件

7~10是show部分——展示
