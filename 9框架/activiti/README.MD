##activiti学习之路
>本文记录activiti的学习过程及其对应api
> 学习地址:https://www.activiti.org/userguide/#demo.setup.one.minute.version

###示例入门程序
导入依赖
```
//特别注意 以下jar包用户自行选择导入  如以下依赖未使用到的话可自行去除
    <dependencies><!--activiti modeler start-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.22</version>
        </dependency>
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-spring-boot-starter-basic</artifactId>
            <version>6.0.0</version>
            <exclusions>
                <exclusion>
                    <artifactId>spring-core</artifactId>
                    <groupId>org.springframework</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-spring-boot-starter-rest-api</artifactId>
            <version>6.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-json-converter</artifactId>
            <version>6.0.0</version>
        </dependency>
        <!-- xml解析依赖-->
        <dependency>
            <groupId>org.apache.xmlgraphics</groupId>
            <artifactId>batik-codec</artifactId>
            <version>1.7</version>
        </dependency>
        <dependency>
            <groupId>org.apache.xmlgraphics</groupId>
            <artifactId>batik-css</artifactId>
            <version>1.7</version>
        </dependency>
        <dependency>
            <groupId>org.apache.xmlgraphics</groupId>
            <artifactId>batik-svg-dom</artifactId>
            <version>1.7</version>
        </dependency>
        <dependency>
            <groupId>org.apache.xmlgraphics</groupId>
            <artifactId>batik-svggen</artifactId>
            <version>1.7</version>
        </dependency>
        <!-- xml解析依赖-->
    </dependencies>
```
创建配置文件(详解activiti官网第三章)
本文讲述spring集成activiti编程时配置
```aidl
package com.activiti6.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * activiti 配置类
 */
@Configuration
public class ActivitiConfig {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 初始化配置
     *
     * @return
     */
    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        // 执行工作流对应的数据源
        configuration.setDataSource(dataSource);
        // 是否自动创建流程引擎表
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //管理线程池异步激活
        configuration.setAsyncExecutorActivate(false);
        // 流程历史等级
        configuration.setHistoryLevel(HistoryLevel.FULL);
        //设置全局eventlister集合
//        configuration.setEventListeners()//
        //流程部署缓存 最多10条
//        configuration.setProcessDefinitionCacheLimit(10);
        // 流程图字体
        configuration.setActivityFontName("宋体");
        configuration.setAnnotationFontName("宋体");
        configuration.setLabelFontName("宋体");
        //事务处理器
        configuration.setTransactionManager(transactionManager);
        return configuration;
    }
}

```
###API基本使用

RepositoryService:此服务提供用于管理和操作deployments和 的操作process definitions
RuntimeService:它处理启动流程定义的新流程实例。允许查询流程实例和执行。
TaskService:
1. 查询分配给用户或组的任务

2. 创建新的独立任务。这些是与流程实例无关的任务。

3. 操纵任务分配给哪个用户或哪些用户以某种方式参与任务。

4. 领取并完成任务。Claiming 意味着某人决定成为该任务的受让人，这意味着该用户将完成该任务。完成意味着完成任务的工作。通常这是填写某种表格。

IdentityService:它允许管理（创建、更新、删除、查询……）组和用户。
FormService是一项可选服务。这意味着 Activiti 可以在没有它的情况下完美使用，而不会牺牲任何功能。该服务引入了启动表单和任务表单的概念。启动表单是在流程实例启动之前向用户显示的表单，而任务表单是在用户想要完成表单时显示的表单。

HistoryService:公开了 Activiti 引擎收集的所有历史数据。在执行流程时，引擎可以保留很多数据（这是可配置的），例如流程实例启动时间、谁执行了哪些任务、完成任务花费了多长时间、每个流程实例遵循的路径等. 此服务主要公开访问此数据的查询功能。

部署流程
```aidl
ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
RepositoryService repositoryService = processEngine.getRepositoryService();
repositoryService.createDeployment()
  .addClasspathResource("org/activiti/test/VacationRequest.bpmn20.xml")
  .deploy();

Log.info("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
```
暂停流程（创建新流程实例会抛异常）
```aidl
repositoryService.suspendProcessDefinitionByKey("vacationRequest");
try {
  runtimeService.startProcessInstanceByKey("vacationRequest");
} catch (ActivitiException e) {
  e.printStackTrace();
}
要重新激活流程定义，只需调用其中一种repositoryService.activateProcessDefinitionXXX方法即可。

也可以暂停流程实例。挂起时，进程无法继续（例如完成任务会抛出异常）并且不会执行任何作业（例如计时器）。可以通过调用runtimeService.suspendProcessInstance方法来挂起流程实例。再次激活流程实例是通过调用runtimeService.activateProcessInstanceXXX方法来完成的。
```
将流程定义部署到 Activiti 引擎后，我们可以从中启动新的流程实例。对于每个流程定义，通常有许多流程实例。流程定义是蓝图，而流程实例是它的运行时执行。

```aidl
Map<String, Object> variables = new HashMap<String, Object>();
variables.put("employeeName", "Kermit");
variables.put("numberOfDays", new Integer(4));
variables.put("vacationMotivation", "I'm really tired!");

RuntimeService runtimeService = processEngine.getRuntimeService();
ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);

// Verify that we started a new process instance
Log.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
```
junit单元测试activiti
```aidl
public class MyBusinessProcessTest {

  @Rule
  public ActivitiRule activitiRule = new ActivitiRule();

  @Test
  @Deployment
  public void ruleUsageExample() {
    RuntimeService runtimeService = activitiRule.getRuntimeService();
    runtimeService.startProcessInstanceByKey("ruleUsage");

    TaskService taskService = activitiRule.getTaskService();
    Task task = taskService.createTaskQuery().singleResult();
    assertEquals("My Task", task.getName());

    taskService.complete(task.getId());
    assertEquals(0, runtimeService.createProcessInstanceQuery().count());
  }
}
```
内容太多了 插件集成和xml文件解析就不讲了

