# 若依学习笔记	

## 1springboot 自定义注解  （自定义规则）

### 1导入依赖

```java
<!--        自定义注解-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
    <version>${ver-version}</version>
</dependency>
```

### 2自定义注解

关于元注解可查看教程：

```
https://www.runoob.com/w3cnote/java-annotation.html
```

示例如下：

```java
/**
 * @author zhouhelong
 * @creat 2022-01-26 13:37
 * @description:  自定义注解测试
 */
@Documented  //
@Retention(RetentionPolicy.RUNTIME)    //标明为运行时注解
@Target(value = {ElementType.FIELD})   //详情可看注解使用  表名使用在字段属性上
@Constraint(validatedBy = DemoVer.class)   //自定义规则注解  实现类为具体实现规则的地方
public @interface demo {
    String  message() default "当前名称错误";
    Class<?>[] groups() default {}; //自定义规则必须携带
    Class<? extends Payload>[] payload() default {};    //自定义规则必须携带
}
```

### 3自定义实现规则

```
/**
 * @author zhouhelong
 * @creat 2022-01-26 13:42
 * @description: 校验规则
 */
public class DemoVer implements ConstraintValidator<demo, String> {  
//实现ConstraintValidator<demo, String>  demo为注解名 String为返回的类型

//校验是否成功  
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.equals("yes")) {
            return true;
        }
        return false;
    }
}
```











































































