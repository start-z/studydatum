# 1idea  JDK 自动变更

解决方法  在maven项目中指定编译版本

```java
<properties>
    <maven.compiler.source>12</maven.compiler.source>
    <maven.compiler.target>12</maven.compiler.target>
  
</properties>
```

![image-20220127153028344](http://inis.inis1719.cn/202206021242905.png)





# 2Springboot使用xml读取yml配置

## - springProfile

该 `<springProfile>` 标签允许我们更加灵活配置文件，可选地包含或排除配置部分。元素中的任何位置均支持轮廓部分。使用该name属性指定哪个配置文件接受配置。可以使用逗号分隔列表指定多个配置文件。

```
<springProfile name="dev">
    <!-- 开发环境时激活 -->
</springProfile>

<springProfile name="dev,test">
    <!-- 开发，测试的时候激活-->
</springProfile>

<springProfile name="!prod">
    <!-- 当 "生产" 环境时，该配置不激活-->
</springProfile>
```

### - 案例

```
<!-- 开发环境日志级别为DEBUG -->
<springProfile name="dev">
    <root level="DEBUG">
        <appender-ref ref="FILE"/>
        <appender-ref ref="STDOUT"/>
    </root>
</springProfile>

<!-- 测试环境日志级别为INFO -->
<springProfile name="test">
    <root level="INFO">
        <appender-ref ref="FILE"/>
        <appender-ref ref="STDOUT"/>
    </root>
</springProfile>
```

## - springProperty

1.该 `<springProperty>` 标签允许我们从Spring中显示属性，Environment 以便在Logback中使用。如果你想将 application.properties在回读配置中访问文件中的值，这将非常有用

2.标签的工作方式与Logback的标准 `<property>` 标签类似，但不是直接value 指定source属性（从Environment）指定。scope 如果需要将属性存储在local范围之外的其他位置，则可以使用该属性。如果您需要一个后备值，以防该属性未设置，则Environment可以使用该defaultValue属性。

```
<springProperty scope="context" name="fluentHost" source="myapp.fluentd.host" defaultValue="localhost"/>
<appender name="FLUENT" class="ch.qos.logback.more.appenders.DataFluentAppender">
    <remoteHost>${fluentHost}</remoteHost>
    
</appender>
```

### - 案例

```
<!-- 读取spring.application.name中的属性来生成日志文件名 -->
<springProperty scope="context" name="logName" source="spring.application.name" defaultValue="localhost.log"/>

<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>logs/${logName}.log</file>    <!-- 使用方法 -->
    <append>true</append>
    <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
        <fileNamePattern>logs/${logName}-%d{yyyy-MM-dd}.%i.log.zip</fileNamePattern>
        <maxFileSize>100MB</maxFileSize>
        <maxHistory>7</maxHistory>
        <totalSizeCap>3GB</totalSizeCap>
    </rollingPolicy>
    <encoder>
        <pattern>[%date{yyyy-MM-dd HH:mm:ss}] [%-5level] [%logger:%line] &#45;&#45;%mdc{client} %msg%n</pattern>
    </encoder>
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <level>DEBUG</level>
    </filter>
</appender>
```

将RelaxedPropertyResolver用于访问环境属性。如果使用虚线符号指定source（my-property-name）所有的变化都会被尝试（myPropertyName，MY_PROPERTY_NAME等）。

## - logback-spring.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true">

    <springProperty scope="context" name="logName" source="spring.application.name" defaultValue="localhost.log"/>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/${logName}.log</file>
        <append>true</append>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>logs/${logName}-%d{yyyy-MM-dd}.%i.log.zip</fileNamePattern>
            <maxFileSize>100MB</maxFileSize>
            <maxHistory>7</maxHistory>
            <totalSizeCap>3GB</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern>[%date{yyyy-MM-dd HH:mm:ss}] [%-5level] [%logger:%line] &#45;&#45;%mdc{client} %msg%n</pattern>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>DEBUG</level>
        </filter>
    </appender>
    <!-- Console output -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>
                [ %-5level] [%date{yyyy-MM-dd HH:mm:ss}] %logger{96} [%line] - %msg%n
            </pattern>
            <charset>UTF-8</charset> <!-- 此处设置字符集 -->
        </encoder>
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>DEBUG</level>
        </filter>
    </appender>

    <springProfile name="dev,test">
        <root level="DEBUG">
            <appender-ref ref="FILE"/>
            <appender-ref ref="STDOUT"/>
        </root>
    </springProfile>

    <springProfile name="prod">
        <root level="INFO">
            <appender-ref ref="FILE"/>
            <appender-ref ref="STDOUT"/>
        </root>
    </springProfile>

    <logger name="org.springframework" level="INFO"/>
    <logger name="com.netflix" level="WARN"/>
    <logger name="org" level="INFO"/>
    <logger name="springfox.documentation" level="INFO"/>

</configuration>
```

 