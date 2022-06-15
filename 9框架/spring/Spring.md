# 																spring

## 1简介

官网 : http://spring.io/

官方下载地址 : https://repo.spring.io/libs-release-local/org/springframework/spring/

GitHub : https://github.com/spring-projects

优点

1、Spring是一个开源免费的框架 , 容器  .

2、Spring是一个轻量级的框架 , 非侵入式的 .

**3、控制反转 IoC  , 面向切面 Aop**

4、对事物的支持 , 对框架的支持

.......

一句话概括：

**Spring是一个轻量级的控制反转(IoC)和面向切面(AOP)的容器（框架）。**

**简单的来说spring就是一个控制反转ioc 和面向切面aop 的框架**

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C192466827d1a4093922f3b8ed207c1a8%5C640.png)

Spring 框架是一个分层架构，由 7 个定义良好的模块组成。Spring 模块构建在核心容器之上，核心容器定义了创建、配置和管理 bean 的方式 .

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C616bd7ab696b4ea284ae8e0af0251a09%5C640.png)



组成 Spring 框架的每个模块（或组件）都可以单独存在，或者与其他一个或多个模块联合实现。每个模块的功能如下：

- **核心容器**：核心容器提供 Spring 框架的基本功能。核心容器的主要组件是BeanFactory，它是工厂模式的实现。BeanFactory 使用*控制反转*（IOC） 模式将应用程序的配置和依赖性规范与实际的应用程序代码分开。
- **Spring 上下文**：Spring 上下文是一个配置文件，向 Spring 框架提供上下文信息。Spring 上下文包括企业服务，例如 JNDI、EJB、电子邮件、国际化、校验和调度功能。
- **Spring AOP**：通过配置管理特性，Spring AOP 模块直接将面向切面的编程功能 , 集成到了 Spring 框架中。所以，可以很容易地使 Spring 框架管理任何支持 AOP的对象。Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。通过使用 Spring AOP，不用依赖组件，就可以将声明性事务管理集成到应用程序中。
- **Spring DAO**：JDBC DAO 抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理和不同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，并且极大地降低了需要编写的异常代码数量（例如打开和关闭连接）。Spring DAO 的面向 JDBC 的异常遵从通用的 DAO 异常层次结构。
- **Spring ORM**：Spring 框架插入了若干个 ORM 框架，从而提供了 ORM 的对象关系工具，其中包括 JDO、Hibernate 和 iBatis SQL Map。所有这些都遵从 Spring 的通用事务和 DAO 异常层次结构。
- **Spring Web 模块**：Web 上下文模块建立在应用程序上下文模块之上，为基于 Web 的应用程序提供了上下文。所以，Spring 框架支持与 Jakarta Struts 的集成。Web 模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作。
- **Spring MVC 框架**：MVC 框架是一个全功能的构建 Web 应用程序的 MVC 实现。通过策略接口，MVC 框架变成为高度可配置的，MVC 容纳了大量视图技术，其中包括 JSP、Velocity、Tiles、iText 和 POI。

拓展

**Spring Boot与Spring Cloud**

- Spring Boot 是 Spring 的一套快速配置脚手架，可以基于Spring Boot 快速开发单个微服务;
- Spring Cloud是基于Spring Boot实现的；
- Spring Boot专注于快速、方便集成的单个微服务个体，Spring Cloud关注全局的服务治理框架；
- Spring Boot使用了约束优于配置的理念，很多集成方案已经帮你选择好了，能不配置就不配置 , Spring Cloud很大的一部分是基于Spring Boot来实现，Spring Boot可以离开Spring Cloud独立使用开发项目，但是Spring Cloud离不开Spring Boot，属于依赖的关系。
- SpringBoot在SpringClound中起到了承上启下的作用，如果你要学习SpringCloud必须要学习SpringBoot。

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5C5a86a4837c834f8abde265b8f90216d2%5C640.png)



## **2IoC**

### 2.1ioc理念思想

新建一个空白的maven项目

分析实现

我们先用我们原来的方式写一段代码 .

1、先写一个UserDao接口

```
public interface UserDao {

  public void getUser();

}
```

2、再去写Dao的实现类

```
public class UserDaoImpl implements UserDao {

  @Override

  public void getUser() {

​    System.out.println("获取用户数据");

 }

}
```

3、然后去写UserService的接口

```
public interface UserService {

  public void getUser();

}
```

4、最后写Service的实现类

```
public class UserServiceImpl implements UserService {

  private UserDao userDao = new UserDaoImpl();

  @Override

  public void getUser() {

​    userDao.getUser();

 }

}
```

5、测试一下

```
@Test

public void test(){

  UserService service = new UserServiceImpl();

  service.getUser();

}
```



这是我们原来的方式 , 开始大家也都是这么去写的对吧 . 那我们现在修改一下 .

把Userdao的实现类增加一个 .

```
public class UserDaoMySqlImpl implements UserDao {

  @Override

  public void getUser() {

​    System.out.println("MySql获取用户数据");

 }

}
```

紧接着我们要去使用MySql的话 , 我们就需要去service实现类里面修改对应的实现

```
public class UserServiceImpl implements UserService {

  private UserDao userDao = new UserDaoMySqlImpl();

  @Override

  public void getUser() {

​    userDao.getUser();

 }

}
```

在假设, 我们再增加一个Userdao的实现类 .

```
public class UserDaoOracleImpl implements UserDao {

  @Override

  public void getUser() {

​    System.out.println("Oracle获取用户数据");

 }

}
```

那么我们要使用Oracle , 又需要去service实现类里面修改对应的实现 . 假设我们的这种需求非常大 , 这种方式就根本不适用了, 甚至反人类对吧 , 每次变动 , 都需要修改大量代码 . 这种设计的耦合性太高了, 牵一发而动全身 .

**那我们如何去解决呢 ?** 

我们可以在需要用到他的地方 , 不去实现它 , 而是留出一个接口 , 利用set , 我们去代码里修改下 .

```
public class UserServiceImpl implements UserService {

  private UserDao userDao;

// 利用set实现

  public void setUserDao(UserDao userDao) {

​    this.userDao = userDao;

 }

  @Override

  public void getUser() {

​    userDao.getUser();

 }

}

现在去我们的测试类里 , 进行测试 ;

@Test

public void test(){

  UserServiceImpl service = new UserServiceImpl();

  service.setUserDao( new UserDaoMySqlImpl() );

  service.getUser();

  //那我们现在又想用Oracle去实现呢

  service.setUserDao( new UserDaoOracleImpl() );

  service.getUser();

}
```

大家发现了区别没有 ? 可能很多人说没啥区别 . 但是同学们 , 他们已经发生了根本性的变化 , 很多地方都不一样了 . 仔细去思考一下 , 以前所有东西都是由程序去进行控制创建 , 而现在是由我们自行控制创建对象 , 把主动权交给了调用者 . 程序不用去管怎么创建,怎么实现了 . 它只负责提供一个接口 .

这种思想 , 从本质上解决了问题 , 我们程序员不再去管理对象的创建了 , 更多的去关注业务的实现 . 耦合性大大降低 . 这也就是IOC的原型 !

**ioc  的本质其实就是为了解耦 在开发种  我们遵循的是低耦合 高类聚**

**另外ioc 是一种编程思想  并不是一种编程代码**

ioc其实是默认调用无参的

哪么有参的我们该怎么去调用呢

这里有三种方法  还有一种数据类型不推荐

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cf3279d856cbd4e0fac7ea24de463a698%5Cclipboard.png)



### 2.2ioc在spring中的运用

导入Jar包

注 : spring 需要导入commons-logging进行日志记录 . 我们利用maven , 他会自动下载对应的依赖项 .

```
<dependency>

  <groupId>org.springframework</groupId>

  <artifactId>spring-webmvc</artifactId>

  <version>5.1.10.RELEASE</version>

</dependency>
```

编写代码

1、编写一个Hello实体类

```
public class Hello {

  private String name;

  public String getName() {

​    return name;

 }

  public void setName(String name) {

​    this.name = name;

 }

  public void show(){

​    System.out.println("Hello,"+ name );

 }

}
```

2、编写我们的spring文件 , 这里我们命名为beans.xml

```
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"

   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

   xsi:schemaLocation="http://www.springframework.org/schema/beans

​    http://www.springframework.org/schema/beans/spring-beans.xsd">

  <!--bean就是java对象 , 由Spring创建和管理-->

  <bean id="hello" class="com.kuang.pojo.Hello">

​    <property name="name" value="Spring"/>

  </bean>

</beans>
```

3、我们可以去进行测试了 .

```
@Test

public void test(){

  //解析beans.xml文件 , 生成管理相应的Bean对象

  ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

  //getBean : 参数即为spring配置文件中bean的id .

  Hello hello = (Hello) context.getBean("hello");

  hello.show();

}
```

思考

- Hello 对象是谁创建的 ?  【hello 对象是由Spring创建的
- Hello 对象的属性是怎么设置的 ?  hello 对象的属性是由Spring容器设置的

这个过程就叫控制反转 :

- 控制 : 谁来控制对象的创建 , 传统应用程序的对象是由程序本身控制创建的 , 使用Spring后 , 对象是由Spring来创建的
- 反转 : 程序本身不创建对象 , 而变成被动的接收对象 .

**依赖注入** : 就是利用set方法来进行注入的.

 IOC是一种编程思想，由主动的编程变成被动的接收

可以通过newClassPathXmlApplicationContext去浏览一下底层源码 .

修改案例一

我们在案例一中， 新增一个Spring配置文件beans.xml

```
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"

   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

   xsi:schemaLocation="http://www.springframework.org/schema/beans

​    http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="MysqlImpl" class="com.kuang.dao.impl.UserDaoMySqlImpl"/>

  <bean id="OracleImpl" class="com.kuang.dao.impl.UserDaoOracleImpl"/>

  <bean id="ServiceImpl" class="com.kuang.service.impl.UserServiceImpl">

​    <!--注意: 这里的name并不是属性 , 而是set方法后面的那部分 , 首字母小写-->

​    <!--引用另外一个bean , 不是用value 而是用 ref-->

​    <property name="userDao" ref="OracleImpl"/>

  </bean>

</beans>
```

测试！

```
@Test

public void test2(){

  ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

  UserServiceImpl serviceImpl = (UserServiceImpl) context.getBean("ServiceImpl");

  serviceImpl.getUser();

}
```

OK , 到了现在 , 我们彻底不用再程序中去改动了 , 要实现不同的操作 , 只需要在xml配置文件中进行修改 , 所谓的IoC,一句话搞定 : 对象由Spring 来创建 , 管理 , 装配 ! 

**IOC创建对象方式**

通过无参构造方法来创建

1、User.java

```
public class User {

  private String name;

  public User() {

​    System.out.println("user无参构造方法");

 }

  public void setName(String name) {

​    this.name = name;

 }

  public void show(){

​    System.out.println("name="+ name );

 }

}
```

2、beans.xml

```
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"

   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

   xsi:schemaLocation="http://www.springframework.org/schema/beans

​    http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="user" class="com.kuang.pojo.User">

​    <property name="name" value="kuangshen"/>

  </bean>

</beans>
```

3、测试类

```
@Test

public void test(){

  ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

  //在执行getBean的时候, user已经创建好了 , 通过无参构造

  User user = (User) context.getBean("user");

  //调用对象的方法 .

  user.show();

}
```

结果可以发现，在调用show方法之前，User对象已经通过无参构造初始化了！

通过有参构造方法来创建

1、UserT . java

```
public class UserT {

  private String name;

  public UserT(String name) {

​    this.name = name;

 }

  public void setName(String name) {

​    this.name = name;

 }

  public void show(){

​    System.out.println("name="+ name );

 }

}
```

2、beans.xml 有三种方式编写

```
<!-- 第一种根据index参数下标设置 -->

<bean id="userT" class="com.kuang.pojo.UserT">

  <!-- index指构造方法 , 下标从0开始 -->

  <constructor-arg index="0" value="kuangshen2"/>

</bean>

<!-- 第二种根据参数名字设置 -->

<bean id="userT" class="com.kuang.pojo.UserT">

  <!-- name指参数名 -->

  <constructor-arg name="name" value="kuangshen2"/>

</bean>

<!-- 第三种根据参数类型设置 -->

<bean id="userT" class="com.kuang.pojo.UserT">

  <constructor-arg type="java.lang.String" value="kuangshen2"/>

</bean>
```

3、测试

```
@Test

public void testT(){

  ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

  UserT user = (UserT) context.getBean("userT");

  user.show();

}
```

结论：在配置文件加载的时候。其中管理的对象都已经初始化了！



### 2.3依赖注入

- 依赖注入（Dependency Injection,DI）。
- 依赖 : 指Bean对象的创建依赖于容器 . Bean对象的依赖资源 .
- 注入 : 指Bean对象所依赖的资源 , 由容器来设置和装配 .

构造器注入

我们在之前的案例已经讲过了

Set 注入 （重点）

要求被注入的属性 , 必须有set方法 , set方法的方法名由set + 属性首字母大写 , 如果属性是boolean类型 , 没有set方法 , 是 is .

下面是各种类型的依赖注入

测试pojo类 :

```
Address.java

 public class Address {

 

   private String address;

 

   public String getAddress() {

​     return address;

  }

 

   public void setAddress(String address) {

​     this.address = address;

  }

 }
```

```
Student.java

 package com.kuang.pojo;

 

 import java.util.List;

 import java.util.Map;

 import java.util.Properties;

 import java.util.Set;

 

 public class Student {

 

   private String name;

   private Address address;

   private String[] books;

   private List<String> hobbys;

   private Map<String,String> card;

   private Set<String> games;

   private String wife;

   private Properties info;

 

   public void setName(String name) {

​     this.name = name;

  }

 

   public void setAddress(Address address) {

​     this.address = address;

  }

 

   public void setBooks(String[] books) {

​     this.books = books;

  }

 

   public void setHobbys(List<String> hobbys) {

​     this.hobbys = hobbys;

  }

 

   public void setCard(Map<String, String> card) {

​     this.card = card;

  }

 

   public void setGames(Set<String> games) {

​     this.games = games;

  }

 

   public void setWife(String wife) {

​     this.wife = wife;

  }

 

   public void setInfo(Properties info) {

​     this.info = info;

  }

 

   public void show(){

​     System.out.println("name="+ name

​         \+ ",address="+ address.getAddress()

​         \+ ",books="

​    );

​     for (String book:books){

​       System.out.print("<<"+book+">>\t");

​    }

​     System.out.println("\n爱好:"+hobbys);

 

​     System.out.println("card:"+card);

 

​     System.out.println("games:"+games);

 

​     System.out.println("wife:"+wife);

 

​     System.out.println("info:"+info);

 

  }

 }
```

1、**常量注入**

```
 <bean id="student" class="com.kuang.pojo.Student">

   <property name="name" value="小明"/>

 </bean>
```

测试：

```
 @Test

 public void test01(){

   ApplicationContext context = newClassPathXmlApplicationContext("applicationContext.xml");

 

   Student student = (Student) context.getBean("student");

 

   System.out.println(student.getName());

 

 }
```

2、**Bean注入** 

注意点：这里的值是一个引用，ref

```
 <bean id="addr" class="com.kuang.pojo.Address">

   <property name="address" value="重庆"/>

 </bean>

 

 <bean id="student" class="com.kuang.pojo.Student">

   <property name="name" value="小明"/>

   <property name="address" ref="addr"/>

 </bean>
```

3、**数组注入**

```
 <bean id="student" class="com.kuang.pojo.Student">

   <property name="name" value="小明"/>

   <property name="address" ref="addr"/>

   <property name="books">

​     <array>

​       <value>西游记</value>

​       <value>红楼梦</value>

​       <value>水浒传</value>

​     </array>

   </property>

 </bean>
```

4、**List注入**

```
 <property name="hobbys">

   <list>

​     <value>听歌</value>

​     <value>看电影</value>

​     <value>爬山</value>

   </list>

 </property>
```

5、**Map注入**

 

```
<property name="card">

   <map>

​     <entry key="中国邮政" value="456456456465456"/>

​     <entry key="建设" value="1456682255511"/>

   </map>

 </property>
```

6、**set注入**

```
 <property name="games">

   <set>

​     <value>LOL</value>

​     <value>BOB</value>

​     <value>COC</value>

   </set>

 </property>
```

7、**Null注入**

```
 <property name="wife"><null/></property>
```

8、**Properties注入**

```
 <property name="info">

   <props>

​     <prop key="学号">20190604</prop>

​     <prop key="性别">男</prop>

​     <prop key="姓名">小明</prop>

   </props>

 </property>
```

测试结果：

![img](D:%5C%E6%9C%89%E9%81%93%E4%BA%91%E7%AC%94%E8%AE%B0%5CqqDBD1F84E1C986CF725E8AFDDB2DCE08F%5Cc81d23503c34451f888749f9f4e2b974%5C640.webp)

p命名和c命名注入

```
User.java ：【注意：这里没有有参构造器！】

 public class User {

   private String name;

   private int age;

 

   public void setName(String name) {

​     this.name = name;

  }

 

   public void setAge(int age) {

​     this.age = age;

  }

 

   @Override

   public String toString() {

​     return "User{" +

​         "name='" + name + '\'' +

​         ", age=" + age +

​         '}';

  }

 }
```

1、P命名空间注入 : 需要在头文件中加入约束文件

 导入约束 : xmlns:p="http://www.springframework.org/schema/p"

 <!--P(属性: properties)命名空间 , 属性依然要设置set方法-->

 <bean id="user" class="com.kuang.pojo.User" p:name="狂神" p:age="18"/>

2、c 命名空间注入 : 需要在头文件中加入约束文件

 导入约束 : xmlns:c="http://www.springframework.org/schema/c"

 <!--C(构造: Constructor)命名空间 , 属性依然要设置set方法-->

 <bean id="user" class="com.kuang.pojo.User" c:name="狂神" c:age="18"/>

发现问题：爆红了，刚才我们没有写有参构造！

解决：把有参构造器加上，这里也能知道，c 就是所谓的构造器注入！

测试代码：

```
 @Test

 public void test02(){

   ApplicationContext context = newClassPathXmlApplicationContext("applicationContext.xml");

   User user = (User) context.getBean("user");

   System.out.println(user);

 }
```



## **3Spring配置**

别名alias 设置别名 , 为bean设置别名 , 可以设置多个别名

```
<!--设置别名：在获取Bean的时候可以使用别名获取-->

<alias name="userT" alias="userNew"/>
```

  id 是bean的标识符,要唯一,如果没有配置id,name就是默认标识符

  如果配置id,又配置了name,那么name是别名

  name可以设置多个别名,可以用逗号,分号,空格隔开

  如果不配置id和name,可以根据applicationContext.getBean(.class)获取对象;

class是bean的全限定名=包名+类名

```
-->

<bean id="hello" name="hello2 h2,h3;h4" class="com.kuang.pojo.Hello">

  <property name="name" value="Spring"/>

</bean>
```



团队的合作通过import来实现 .

```
<import resource="{path}/beans.xml"/>
```

spring 配置文件

```
<beans xmlns="http://www.springframework.org/schema/beans"

    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

     xsi:schemaLocation="http://www.springframework.org/schema/beans
      https://www.springframework.org/schema/beans/spring-beans.xsd">

</beans>
```

类操作

ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

使用注解需要在配置文件输入

```
<?xml version="1.0" encoding="UTF-8"?>** <beans xmlns="http://www.springframework.org/schema/beans"    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xmlns:context="http://www.springframework.org/schema/context"  
xsi:schemaLocation="http://www.springframework.org/schema/beans     
https://www.springframework.org/schema/beans/spring-beans.xsd      
http://www.springframework.org/schema/context     
https://www.springframework.org/schema/context/spring-context.xsd">  
<context:annotation-config/> </beans>


```



### 3.1注解自动注入

前提：  xml配置文件中加入<context:annotation-config/>

```
@autwirs  上面的必须在spring种使用
```

其中还可以加入@nullable这个注解  就是可以为空 默认为null值

```
@resource  这个是java 自带的
```

- @resource ：  java自带  通过名称注入
- @autwirs     spring中使用  通过类型注入





准备工作：利用注解的方式注入属性。

1、在spring配置文件中引入context文件头

```
xmlns:context="http://www.springframework.org/schema/context"

http://www.springframework.org/schema/context

http://www.springframework.org/schema/context/spring-context.xsd
```

2、开启属性注解支持！

```
<context:annotation-config/>
```

**@Autowired**

测试：

1、将User类中的set方法去掉，使用@Autowired注解

```
public class User {

  @Autowired

  private Cat cat;

  @Autowired

  private Dog dog;

  private String str;

  public Cat getCat() {

​    return cat;

 }

  public Dog getDog() {

​    return dog;

 }

  public String getStr() {

​    return str;

 }

}
```

2、此时配置文件内容

```
context:annotation-config/>

<bean id="dog" class="com.kuang.pojo.Dog"/>

<bean id="cat" class="com.kuang.pojo.Cat"/>

<bean id="user" class="com.kuang.pojo.User"/>
```

3、测试，成功输出结果！

**@Qualifier**

- @Autowired是根据类型自动装配的，加上@Qualifier则可以根据byName的方式自动装配
- @Qualifier不能单独使用。

测试实验步骤：

1、配置文件修改内容，保证类型存在对象。且名字不为类的默认名字！

```
<bean id="dog1" class="com.kuang.pojo.Dog"/>

<bean id="dog2" class="com.kuang.pojo.Dog"/>

<bean id="cat1" class="com.kuang.pojo.Cat"/>

<bean id="cat2" class="com.kuang.pojo.Cat"/>
```

2、没有加Qualifier测试，直接报错

3、在属性上添加Qualifier注解

@Autowired

@Qualifier(value = "cat2")

private Cat cat;

@Autowired

@Qualifier(value = "dog2")

private Dog dog;

测试，成功输出！

**@Resource**

- @Resource如有指定的name属性，先按该属性进行byName方式查找装配；
- 其次再进行默认的byName方式进行装配；
- 如果以上都不成功，则按byType的方式自动装配。
- 都不成功，则报异常。

实体类：

```
public class User {

  //如果允许对象为null，设置required = false,默认为true

  @Resource(name = "cat2")

  private Cat cat;

  @Resource

  private Dog dog;

  private String str;

}
```

```
beans.xml

<bean id="dog" class="com.kuang.pojo.Dog"/>

<bean id="cat1" class="com.kuang.pojo.Cat"/>

<bean id="cat2" class="com.kuang.pojo.Cat"/>

<bean id="user" class="com.kuang.pojo.User
```

"/>

测试：结果OK

配置文件2：beans.xml ， 删掉cat2

```
<bean id="dog" class="com.kuang.pojo.Dog"/>

<bean id="cat1" class="com.kuang.pojo.Cat"/>
```

实体类上只保留注解

```
@Resource

private Cat cat;

@Resource

private Dog dog;
```

结果：OK

结论：先进行byName查找，失败；再进行byType查找，成功。

**小结**

@Autowired与@Resource异同：

1、@Autowired与@Resource都可以用来装配bean。都可以写在字段上，或写在setter方法上。

2、@Autowired默认按类型装配（属于spring规范），默认情况下必须要求依赖对象必须存在，如果要允许null 值，可以设置它的required属性为false，如：@Autowired(required=false) ，如果我们想使用名称装配可以结合@Qualifier注解进行使用

3、@Resource（属于J2EE复返），默认按照名称进行装配，名称可以通过name属性进行指定。如果没有指定name属性，当注解写在字段上时，默认取字段名进行按照名称查找，如果注解写在setter方法上默认取属性名进行装配。当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。

它们的作用相同都是用注解方式注入对象，但执行顺序不同。@Autowired先byType，@Resource先byName。





