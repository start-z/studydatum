## 		MyBatis

## 1简介

- Mybatis官方文档 : http://www.mybatis.org/mybatis-3/zh/index.html
- GitHub : https://github.com/mybatis/mybatis-3

什么是MyBatis？

- MyBatis 是一款优秀的**持久层框架**
- MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集的过程
- MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 实体类 【Plain Old Java Objects,普通的 Java对象】映射成数据库中的记录。
- MyBatis 本是apache的一个开源项目ibatis, 2010年这个项目由apache 迁移到了google code，并且改名为MyBatis 。
- 2013年11月迁移到**Github** .

持久化

**持久化是将程序数据在持久状态和瞬时状态间转换的机制。**

- 即把数据（如内存中的对象）保存到可永久保存的存储设备中（如磁盘）。持久化的主要应用是将内存中的对象存储在数据库中，或者存储在磁盘文件中、XML数据文件中等等。
- JDBC就是一种持久化机制。文件IO也是一种持久化机制。
- 在生活中 : 将鲜肉冷藏，吃的时候再解冻的方法也是。将水果做成罐头的方法也是。

**为什么需要持久化服务呢？那是由于内存本身的缺陷引起的**

- 内存断电后数据会丢失，但有一些对象是无论如何都不能丢失的，比如银行账号等，遗憾的是，人们还无法保证内存永不掉电。
- 内存过于昂贵，与硬盘、光盘等外存相比，内存的价格要高2~3个数量级，而且维持成本也高，至少需要一直供电吧。所以即使对象不需要永久保存，也会因为内存的容量限制不能一直呆在内存中，需要持久化来缓存到外存。

持久层

**什么是持久层？**

- 完成持久化工作的代码块 .  ---->  dao层 【DAO (Data Access Object)  数据访问对象】
- 大多数情况下特别是企业级应用，数据持久化往往也就意味着将内存中的数据保存到磁盘上加以固化，而持久化的实现过程则大多通过各种**关系数据库**来完成。
- 不过这里有一个字需要特别强调，也就是所谓的“层”。对于应用系统而言，数据持久功能大多是必不可少的组成部分。也就是说，我们的系统中，已经天然的具备了“持久层”概念？也许是，但也许实际情况并非如此。之所以要独立出一个“持久层”的概念,而不是“持久模块”，“持久单元”，也就意味着，我们的系统架构中，应该有一个相对独立的逻辑层面，专注于数据持久化逻辑的实现.
- 与系统其他部分相对而言，这个层面应该具有一个较为清晰和严格的逻辑边界。【说白了就是用来操作数据库存在的！】

**为什么需要Mybatis？**

- Mybatis就是帮助程序猿将数据存入数据库中 , 和从数据库中取数据 .
- 传统的jdbc操作 , 有很多重复代码块 .比如 : 数据取出时的封装 , 数据库的建立连接等等... , 通过框架可以减少重复代码,提高开发效率 .
- MyBatis 是一个半自动化的**ORM框架 (Object Relationship Mapping) -->对象关系映射**
- 所有的事情，不用Mybatis依旧可以做到，只是用了它，所有实现会更加简单！**技术没有高低之分，只有使用这个技术的人有高低之别**
- MyBatis的优点
- - 简单易学：本身就很小且简单。没有任何第三方依赖，最简单安装只要两个jar文件+配置几个sql映射文件就可以了，易于学习，易于使用，通过文档和源代码，可以比较完全的掌握它的设计思路和实现。
  - 灵活：mybatis不会对应用程序或者数据库的现有设计强加任何影响。sql写在xml里，便于统一管理和优化。通过sql语句可以满足操作数据库的所有需求。
  - 解除sql与程序代码的耦合：通过提供DAO层，将业务逻辑和数据访问逻辑分离，使系统的设计更清晰，更易维护，更易单元测试。sql和代码的分离，提高了可维护性。
  - 提供xml标签，支持编写动态sql。
  - .......
- 最重要的一点，使用的人多！公司需要！



## 2搭建mybaits

**思路流程：搭建环境-->导入Mybatis--->编写代码--->测试**

代码演示

### 1搭建实验数据库

```
CREATE DATABASE `mybatis`;

USE `mybatis`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (

`id` int(20) NOT NULL,

`name` varchar(30) DEFAULT NULL,

`pwd` varchar(30) DEFAULT NULL,

PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `user`(`id`,`name`,`pwd`) values (1,'狂神','123456'),(2,'张三','abcdef'),(3,'李四','987654');
```



### 2导入MyBatis相关 jar 包

```
<dependency>

  <groupId>org.mybatis</groupId>

  <artifactId>mybatis</artifactId>

  <version>3.5.2</version>

</dependency>

<dependency>

  <groupId>mysql</groupId>

  <artifactId>mysql-connector-java</artifactId>

  <version>5.1.47</version>

</dependency>
```

### 3编写MyBatis核心配置文件

```
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE configuration


​    PUBLIC "-//mybatis.org//DTD Config 3.0//EN"

​    "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

  <environments default="development">

​    <environment id="development">

​      <transactionManager type="JDBC"/>

​      <dataSource type="POOLED">

​        <property name="driver" value="com.mysql.jdbc.Driver"/>

​        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=utf8"/>

​        <property name="username" value="root"/>

​        <property name="password" value="123456"/>

​      </dataSource>

​    </environment>

  </environments>

  <mappers>

​    <mapper resource="com/kuang/dao/userMapper.xml"/>

  </mappers>

</configuration>
```

### 4编写MyBatis工具类

- 查看帮助文档

```
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

import java.io.InputStream;

public class MybatisUtils {

  private static SqlSessionFactory sqlSessionFactory;

  static {

​    try {

​      String resource = "mybatis-config.xml";

​      InputStream inputStream = Resources.getResourceAsStream(resource);

​      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

   } catch (IOException e) {

​      e.printStackTrace();

   }

 }

  //获取SqlSession连接

  public static SqlSession getSession(){

​    return sqlSessionFactory.openSession();

 }

}
```

### 5创建实体类

```
public class User {

  

  private int id;  //id

  private String name;  //姓名

  private String pwd;  //密码

  

  //构造,有参,无参

  //set/get

  //toString()

  

}
```

### 6编写Mapper接口类

```
import com.kuang.pojo.User;

import java.util.List;

public interface UserMapper {

  List<User> selectUser();

}
```

### 7编写Mapper.xml配置文件

- namespace 十分重要，不能写错！

```
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper


​    PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"

​    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.kuang.dao.UserMapper">

 <select id="selectUser" resultType="com.kuang.pojo.User">


 select * from user

 </select>

</mapper>
```

### 8编写测试类

- Junit 包测试

```
public class MyTest {

  @Test

  public void selectUser() {

​    SqlSession session = MybatisUtils.getSession();

​    //方法一:

​    //List<User> users = session.selectList("com.kuang.mapper.UserMapper.selectUser");

​    //方法二:

​    UserMapper mapper = session.getMapper(UserMapper.class);

​    List<User> users = mapper.selectUser();

​    for (User user: users){

​      System.out.println(user);

   }

​    session.close();

 }

}
```

### 9运行测试，成功的查询出来的我们的数据，ok！

**可能出现问题说明：Maven静态资源过滤问题**

```
<resources>

  <resource>

​    <directory>src/main/java</directory>

​    <includes>

​      <include>**/*.properties</include>

​      <include>**/*.xml</include>

​    </includes>

​    <filtering>false</filtering>

  </resource>

  <resource>

​    <directory>src/main/resources</directory>

​    <includes>

​      <include>**/*.properties</include>

​      <include>**/*.xml</include>

​    </includes>

​    <filtering>false</filtering>

  </resource>

</resources>
```



## 3配置参数解析 官网清晰明了

特别注意 xml配置的参数是由顺序要求

环境配置environments 

其中数据源默认的都是pooled  都是两种

  **properties**属性 

在属性propeities中  标签存放是有顺序的

另外 在properties中会优先使用外部引入的数据

 别名   typealiases   排名第3

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cee610554f69b4cf5acf787c2dc90c57d%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C0b74ab2745764db4b952b617adff9736%5Cclipboard.png)

简单的来说就是给类或者其他的取一个简单的名字 方便输入

方法有2种

1使用typealias 给指定的类设置指定的名字

2使用package给他目录下的设置默认是小写名字的名字

另外  package还可以使用注解的方式来使用

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C19ef9d4100494cce900d19cb2703566e%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Ca2703de16ef641728987cce99ba5552a%5Cclipboard.png)

​                  映射器  mappers

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C35ae5cfb7e3f4dd596a85dec45850bdd%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C98a718afae6348ba89bbd8864de2308e%5Cclipboard.png)

映射器mapper有4种方式 其中file直接被我们排除

1包名映射   

2class文件映射

3路径映射



## 4mybatis生命周期

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C0d8edf9b0e414707b579177256ec0757%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cc9706ce4356043fb845f39fd5c02f79d%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C068249b2c01c4a06b558c072661e159d%5Cclipboard.png)

**作用域（Scope）和生命周期**

理解我们目前已经讨论过的不同作用域和生命周期类是至关重要的，因为错误的使用会导致非常严重的并发问题。

我们可以先画一个流程图，分析一下Mybatis的执行过程！

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C31dab9d7f94b4f6db10dd54175426df5%5C640.png)

**作用域理解**

- SqlSessionFactoryBuilder 的作用在于创建 SqlSessionFactory，创建成功后，SqlSessionFactoryBuilder 就失去了作用，所以它只能存在于创建 SqlSessionFactory 的方法中，而不要让其长期存在。因此 **SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域**（也就是局部方法变量）。
- SqlSessionFactory 可以被认为是一个数据库连接池，它的作用是创建 SqlSession 接口对象。因为 MyBatis 的本质就是 Java 对数据库的操作，所以 SqlSessionFactory 的生命周期存在于整个 MyBatis 的应用之中，所以一旦创建了 SqlSessionFactory，就要长期保存它，直至不再使用 MyBatis 应用，所以可以认为 SqlSessionFactory 的生命周期就等同于 MyBatis 的应用周期。
- 由于 SqlSessionFactory 是一个对数据库的连接池，所以它占据着数据库的连接资源。如果创建多个 SqlSessionFactory，那么就存在多个数据库连接池，这样不利于对数据库资源的控制，也会导致数据库连接资源被消耗光，出现系统宕机等情况，所以尽量避免发生这样的情况。
- 因此在一般的应用中我们往往希望 SqlSessionFactory 作为一个单例，让它在应用中被共享。所以说 **SqlSessionFactory 的最佳作用域是应用作用域。**
- 如果说 SqlSessionFactory 相当于数据库连接池，那么 SqlSession 就相当于一个数据库连接（Connection 对象），你可以在一个事务里面执行多条 SQL，然后通过它的 commit、rollback 等方法，提交或者回滚事务。所以它应该存活在一个业务请求中，处理完整个请求后，应该关闭这条连接，让它归还给 SqlSessionFactory，否则数据库资源就很快被耗费精光，系统就会瘫痪，所以用 try...catch...finally... 语句来保证其正确关闭。
- **所以 SqlSession 的最佳的作用域是请求或方法作用域。**

   **数据库和对应的适合类对象不同查不出来怎么办**



## 5映射文件

**要解决的问题：属性名和字段名不一致**

环境：新建一个项目，将之前的项目拷贝过来

1、查看之前的数据库的字段名

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C2ac5a1f3c5b54871ac029a62e919f5e3%5C640.png)

2、Java中的实体类设计

```
public class User {

  private int id;  //id

  private String name;  //姓名

  private String password;  //密码和数据库不一样！

  

  //构造

  //set/get

  //toString()

}
```

3、接口

```
//根据id查询用户

User selectUserById(int id);
```

4、mapper映射文件

```
<select id="selectUserById" resultType="user">

 select * from user where id = #{id}

</select>
```

5、测试

```
@Test

public void testSelectUserById() {

  SqlSession session = MybatisUtils.getSession();  //获取SqlSession连接

  UserMapper mapper = session.getMapper(UserMapper.class);

  User user = mapper.selectUserById(1);

  System.out.println(user);

  session.close();

}
```

**结果:**

- User{id=1, name='狂神', password='null'}
- 查询出来发现 password 为空 . 说明出现了问题！

**分析：**

- select * from user where id = #{id} 可以看做

select  id,name,pwd  from user where id = #{id}

- mybatis会根据这些查询的列名(会将列名转化为小写,数据库不区分大小写) , 去对应的实体类中查找相应列名的set方法设值 , 由于找不到setPwd() , 所以password返回null ; 【自动映射】

解决方案

方案一：为列名指定别名 , 别名和java实体类的属性名一致 .

```
<select id="selectUserById" resultType="User">

 select id , name , pwd as password from user where id = #{id}

</select>
```

**方案二：使用结果集映射->ResultMap** 【推荐】

```
<resultMap id="UserMap" type="User">

  <!-- id为主键 -->

  <id column="id" property="id"/>

  <!-- column是数据库表的列名 , property是对应实体类的属性名 -->

  <result column="name" property="name"/>

  <result column="pwd" property="password"/>

</resultMap>

<select id="selectUserById" resultMap="UserMap">

 select id , name , pwd from user where id = #{id}

</select>
```

ResultMap

**自动映射**

- resultMap 元素是 MyBatis 中最重要最强大的元素。它可以让你从 90% 的 JDBC ResultSets 数据提取代码中解放出来。
- 实际上，在为一些比如连接的复杂语句编写映射代码的时候，一份 resultMap 能够代替实现同等功能的长达数千行的代码。
- ResultMap 的设计思想是，对于简单的语句根本不需要配置显式的结果映射，而对于复杂一点的语句只需要描述它们的关系就行了。

你已经见过简单映射语句的示例了，但并没有显式指定 resultMap。比如：

```
<select id="selectUserById" resultType="map">

select id , name , pwd

 from user

 where id = #{id}

</select>
```

上述语句只是简单地将所有的列映射到 HashMap 的键上，这由 resultType 属性指定。虽然在大部分情况下都够用，但是 HashMap 不是一个很好的模型。你的程序更可能会使用 JavaBean 或 POJO（Plain Old Java Objects，普通老式 Java 对象）作为模型。

ResultMap 最优秀的地方在于，虽然你已经对它相当了解了，但是根本就不需要显式地用到他们。

简单的来说就是将名字是由map的形式起一个别名 让他能对应上数据库的名字

**手动映射**

1、返回值类型为resultMap

```
<select id="selectUserById" resultMap="UserMap">

 select id , name , pwd from user where id = #{id}

</select>
```

2、编写resultMap，实现手动映射！

```
<resultMap id="UserMap" type="User">

  <!-- id为主键 -->

  <id column="id" property="id"/>

  <!-- column是数据库表的列名 , property是对应实体类的属性名 -->

  <result column="name" property="name"/>

  <result column="pwd" property="password"/>

</resultMap>
```

如果世界总是这么简单就好了。但是肯定不是的，数据库中，存在一对多，多对一的情况，我们之后会使用到一些高级的结果集映射，association，collection这些，我们将在之后讲解，今天你们需要把这些知识都消化掉才是最重要的！理解结果集映射的这个概念！

分页的几种方式

日志工厂

思考：我们在测试SQL的时候，要是能够在控制台输出 SQL 的话，是不是就能够有更快的排错效率？

如果一个 数据库相关的操作出现了问题，我们可以根据输出的SQL语句快速排查问题。

对于以往的开发过程，我们会经常使用到debug模式来调节，跟踪我们的代码执行过程。但是现在使用Mybatis是基于接口，配置文件的源代码执行过程。因此，我们必须选择日志工具来作为我们开发，调节程序的工具。

Mybatis内置的日志工厂提供日志功能，具体的日志实现有以下几种工具：

- SLF4J
- Apache Commons Logging
- Log4j 2
- Log4j
- JDK logging

具体选择哪个日志实现工具由MyBatis的内置日志工厂确定。它会使用最先找到的（按上文列举的顺序查找）。如果一个都未找到，日志功能就会被禁用。

**标准日志实现**

指定 MyBatis 应该使用哪个日志记录实现。如果此设置不存在，则会自动发现日志记录实现。

```
<settings>

​    <setting name="logImpl" value="STDOUT_LOGGING"/>

</settings>
```

测试，可以看到控制台有大量的输出！我们可以通过这些输出来判断程序到底哪里出了Bug

Log4j

**简介：**

- Log4j是Apache的一个开源项目
- 通过使用Log4j，我们可以控制日志信息输送的目的地：控制台，文本，GUI组件....
- 我们也可以控制每一条日志的输出格式；
- 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。最令人感兴趣的就是，这些可以通过一个配置文件来灵活地进行配置，而不需要修改应用的代码。

**使用步骤：**

1、导入log4j的包

```
<dependency>

  <groupId>log4j</groupId>

  <artifactId>log4j</artifactId>

  <version>1.2.17</version>

</dependency>
```

2、配置文件编写

\#将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码

log4j.rootLogger=DEBUG,console,file

\#控制台输出的相关设置

log4j.appender.console = org.apache.log4j.ConsoleAppender

log4j.appender.console.Target = System.out

log4j.appender.console.Threshold=DEBUG

log4j.appender.console.layout = org.apache.log4j.PatternLayout

log4j.appender.console.layout.ConversionPattern=[%c]-%m%n

\#文件输出的相关设置

log4j.appender.file = org.apache.log4j.RollingFileAppender

log4j.appender.file.File=./log/kuang.log

log4j.appender.file.MaxFileSize=10mb

log4j.appender.file.Threshold=DEBUG

log4j.appender.file.layout=org.apache.log4j.PatternLayout

log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n

\#日志输出级别

log4j.logger.org.mybatis=DEBUG

log4j.logger.java.sql=DEBUG

log4j.logger.java.sql.Statement=DEBUG

log4j.logger.java.sql.ResultSet=DEBUG

log4j.logger.java.sql.PreparedStatement=DEBUG

3、setting设置日志实现

<settings>

  <setting name="logImpl" value="LOG4J"/>

</settings>

4、在程序中使用Log4j进行输出！

//注意导包：org.apache.log4j.Logger

static Logger logger = Logger.getLogger(MyTest.class);

@Test

public void selectUser() {

  logger.info("info：进入selectUser方法");

  logger.debug("debug：进入selectUser方法");

  logger.error("error: 进入selectUser方法");

  SqlSession session = MybatisUtils.getSession();

  UserMapper mapper = session.getMapper(UserMapper.class);

  List<User> users = mapper.selectUser();

  for (User user: users){

​    System.out.println(user);

 }

  session.close();

}

5、测试，看控制台输出！

- 使用Log4j 输出日志
- 可以看到还生成了一个日志的文件 【需要修改file的日志级别】

limit实现分页

**思考：为什么需要分页？**

在学习mybatis等持久层框架的时候，会经常对数据进行增删改查操作，使用最多的是对数据库进行查询操作，如果查询大量数据的时候，我们往往使用分页进行查询，也就是每次处理小部分数据，这样对数据库压力就在可控范围内。

**使用Limit实现分页**

\#语法

SELECT * FROM table LIMIT stratIndex，pageSize

SELECT * FROM table LIMIT 5,10; // 检索记录行 6-15 

\#为了检索从某一个偏移量到记录集的结束所有的记录行，可以指定第二个参数为 -1：  

SELECT * FROM table LIMIT 95,-1; // 检索记录行 96-last.  

\#如果只给定一个参数，它表示返回最大的记录行数目：  

SELECT * FROM table LIMIT 5; //检索前 5 个记录行  

\#换句话说，LIMIT n 等价于 LIMIT 0,n。 

**步骤：**

1、修改Mapper文件

<select id="selectUser" parameterType="map" resultType="user">

 select * from user limit #{startIndex},#{pageSize}

</select>

2、Mapper接口，参数为map

//选择全部用户实现分页

List<User> selectUser(Map<String,Integer> map);

3、在测试类中传入参数测试

- 推断：起始位置 =  （当前页面 - 1 ） * 页面大小

//分页查询 , 两个参数startIndex , pageSize

@Test

public void testSelectUser() {

  SqlSession session = MybatisUtils.getSession();

  UserMapper mapper = session.getMapper(UserMapper.class);

  int currentPage = 1;  //第几页

  int pageSize = 2;  //每页显示几个

  Map<String,Integer> map = new HashMap<String,Integer>();

  map.put("startIndex",(currentPage-1)*pageSize);

  map.put("pageSize",pageSize);

  List<User> users = mapper.selectUser(map);

  for (User user: users){

​    System.out.println(user);

 }

  session.close();

}

RowBounds分页

我们除了使用Limit在SQL层面实现分页，也可以使用RowBounds在Java代码层面实现分页，当然此种方式作为了解即可。我们来看下如何实现的！

**步骤：**

1、mapper接口

//选择全部用户RowBounds实现分页

List<User> getUserByRowBounds();

2、mapper文件

<select id="getUserByRowBounds" resultType="user">

select * from user

</select>

3、测试类

在这里，我们需要使用RowBounds类

@Test

public void testUserByRowBounds() {

  SqlSession session = MybatisUtils.getSession();

  int currentPage = 2;  //第几页

  int pageSize = 2;  //每页显示几个

  RowBounds rowBounds = new RowBounds((currentPage-1)*pageSize,pageSize);

  //通过session.**方法进行传递rowBounds，[此种方式现在已经不推荐使用了]

  List<User> users =session.selectList("com.kuang.mapper.UserMapper.getUserByRowBounds", null,rowBounds);

  for (User user: users){

​    System.out.println(user);

 }

  session.close();

}

PageHelper

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C29904bd527df4ffab2dab3ef39623a60%5C640.png)

了解即可，可以自己尝试使用

官方文档：https://pagehelper.github.io/



## 6log4j

在mybatis种 我们还可以开启日志来排错 使 自己能够清楚的知道自己的错误在哪里

其中日志实现实在配置文件setting中设置的

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C9250562486e64b079e401261348f3b1d%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cedc0cd697a1f4ceb899c09796e65ac4f%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C85653d16b8734d70b046f1ea8c12df1c%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cf2eb24a9a952448d8d7ba1e4c2472d1e%5Cclipboard.png)

​       

<settings>

  <setting name="logImpl" value="LOG4J"/>

</settings>

 log4j的实现

1 导入对应的log4j的包

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C3720c37942364a46b02fbdf833285f41%5Cclipboard.png)

2编写配置文件(.properties ,在 resouce目录下)

\#将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码

log4j.rootLogger=DEBUG,console,file

\#控制台输出的相关设置

log4j.appender.console = org.apache.log4j.ConsoleAppender

log4j.appender.console.Target = System.out

log4j.appender.console.Threshold=DEBUG

log4j.appender.console.layout = org.apache.log4j.PatternLayout

log4j.appender.console.layout.ConversionPattern=[%c]-%m%n

\#文件输出的相关设置

log4j.appender.file = org.apache.log4j.RollingFileAppender

log4j.appender.file.File=./log/kuang.log

log4j.appender.file.MaxFileSize=10mb

log4j.appender.file.Threshold=DEBUG

log4j.appender.file.layout=org.apache.log4j.PatternLayout

log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n

\#日志输出级别

log4j.logger.org.mybatis=DEBUG

log4j.logger.java.sql=DEBUG

log4j.logger.java.sql.Statement=DEBUG

log4j.logger.java.sql.ResultSet=DEBUG	

log4j.logger.java.sql.PreparedStatement=DEBUG

3使用log4j输出

创建Logger 对象

然后使用info 或者error 或者debug使用

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cbd3d4d24449d484eb8e8c1fca5740730%5Cclipboard.png)

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C2530fbceef6e45509025045130ff485f%5Cclipboard.png)



笔记暂时停止在这里 具体可查看官网  简直太详细了