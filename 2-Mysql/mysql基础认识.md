

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