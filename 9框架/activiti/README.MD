

# activiti学习之路

>本文记录activiti的学习过程及其对应api
> 学习地址:https://www.activiti.org/userguide/#demo.setup.one.minute.version

## 1示例入门程序

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
## 2流程创建步骤

### 2.1部署流程

文件如下

```
<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:activiti="http://activiti.org/bpmn"
             xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
             xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI"
             typeLanguage="http://www.w3.org/2001/XMLSchema" expressionLanguage="http://www.w3.org/1999/XPath"
             targetNamespace="http://www.activiti.org/processdef">
  <process id="10010011" name="请假流程" isExecutable="true">
    <documentation>请假流程</documentation>
    <startEvent id="sid-02caa80f-7836-440a-b698-06542eb51ca7">
      <extensionElements>
        <activiti:formProperty id="Property 1"/>
        <activiti:executionListener class="Class 1"/>
        <activiti:executionListener class="Class 2"/>
      </extensionElements>
    </startEvent>
    <userTask id="sid-c3f726b6-7a2f-4859-a47e-9c55e2731436" name="组长审批">
      <documentation>组长审批</documentation>
    </userTask>
    <endEvent id="sid-77c5cb63-b4f6-4416-9fdc-20cdfbce2139"/>
    <sequenceFlow id="sid-b7dddc78-de27-49f8-a51f-0e235c280285" sourceRef="sid-02caa80f-7836-440a-b698-06542eb51ca7" targetRef="sid-62606fff-02d7-4c5c-8ee4-1d18492ca78c"/>
    <sequenceFlow id="sid-4cce2718-f81b-439d-a69c-1f786f5845e9" sourceRef="sid-62606fff-02d7-4c5c-8ee4-1d18492ca78c" targetRef="sid-c3f726b6-7a2f-4859-a47e-9c55e2731436"/>
    <sequenceFlow id="sid-42bb015f-3d55-4381-9a78-7af4f9ac0da3" sourceRef="sid-c3f726b6-7a2f-4859-a47e-9c55e2731436" targetRef="sid-77c5cb63-b4f6-4416-9fdc-20cdfbce2139"/>
    <userTask id="sid-62606fff-02d7-4c5c-8ee4-1d18492ca78c" name="创建出差">
      <documentation>创建出差</documentation>
    </userTask>
  </process>
  <bpmndi:BPMNDiagram id="BPMNDiagram_test">
    <bpmndi:BPMNPlane bpmnElement="10010011" id="BPMNPlane_test">
      <bpmndi:BPMNShape id="shape-929d7e15-946d-4ab6-b98c-2a27ccf2e063" bpmnElement="sid-02caa80f-7836-440a-b698-06542eb51ca7">
        <omgdc:Bounds x="0.0" y="-115.0" width="30.0" height="30.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="shape-e778224e-e282-42be-810c-2dcf42d1b132" bpmnElement="sid-62606fff-02d7-4c5c-8ee4-1d18492ca78c">
        <omgdc:Bounds x="-25.0" y="-55.0" width="100.0" height="80.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="shape-5b4d66d6-f9ff-413b-9219-86454b72e7ad" bpmnElement="sid-c3f726b6-7a2f-4859-a47e-9c55e2731436">
        <omgdc:Bounds x="-25.0" y="55.0" width="100.0" height="80.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="shape-c9f76592-90f5-497e-9d0d-9a2db1feaa2e" bpmnElement="sid-77c5cb63-b4f6-4416-9fdc-20cdfbce2139">
        <omgdc:Bounds x="10.0" y="155.0" width="30.0" height="30.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge id="edge-5cb9b216-15fa-4903-a614-fcf817e3668f" bpmnElement="sid-b7dddc78-de27-49f8-a51f-0e235c280285">
        <omgdi:waypoint x="22.5" y="-85.0"/>
        <omgdi:waypoint x="25.0" y="-55.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="edge-64be6f3b-9422-4500-813f-bdc5341422f2" bpmnElement="sid-4cce2718-f81b-439d-a69c-1f786f5845e9">
        <omgdi:waypoint x="25.0" y="25.0"/>
        <omgdi:waypoint x="25.0" y="55.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="edge-23c93f0d-d379-4140-932e-fe3f13f570e9" bpmnElement="sid-42bb015f-3d55-4381-9a78-7af4f9ac0da3">
        <omgdi:waypoint x="25.0" y="135.0"/>
        <omgdi:waypoint x="25.0" y="155.0"/>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>


```

接口如下：

```
    /**
     * 部署流程
     */
    @RequestMapping(value = "/deploy", method = RequestMethod.GET)
    public void init() {
        //部署流程
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService
                .createDeployment()
                .addClasspathResource("processes/test.bpmn20.xml")
                .deploy();
        System.out.println(deploy + " 创建成功");
    }
```



### 2.2创建流程实例

与进程运行时状态相关的所有内容都可以在**RuntimeService**中找到。

接口如下：

```
  public void startProcess() {
        //设置流程变量
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");
        //key为xml中定义的id
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
        log.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
        System.out.println("流程定义id：" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例id：" + processInstance.getId());
        System.out.println("当前活动Id：" + processInstance.getActivityId());
    }
```

### 2.3查看流程任务

```

    /**
     * 查看当前任务流程
     *
     * @return 任务列表
     */
    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks() {
        List<Task> tasks = myService.getTasks();
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }
```



### 2.4完成任务

```

    /**
     * 完成当前任务
     *
     * @return 任务列表
     */
    @RequestMapping(value = "/complete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void complete() {
        Task task = taskService.createTaskQuery().taskCandidateGroup("management").list().get(0);
        System.out.println(task);
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("vacationApproved", "false");
        taskVariables.put("managerMotivation", "We have a tight deadline!");
        taskService.complete(task.getId(), taskVariables);
    }
```

## 3API基本使用

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
## 4流程变量

每个流程实例都需要并使用数据来执行它存在的步骤。在 Activiti 中，这些数据称为*变量*，存储在数据库中。变量可以用在表达式中（例如在独占网关中选择正确的输出序列流），在调用外部服务时的 java 服务任务中（例如提供输入或存储服务调用的结果）等。

流程实例可以有变量（称为*流程变量*），也可以*有执行*（它是指向流程活动位置的特定指针）和用户任务可以有变量。流程实例可以有任意数量的变量。*每个变量都存储在ACT_RU_VARIABLE*数据库表的一行中。

瞬息变量

瞬态变量是行为类似于常规变量的变量，但不会持久化。通常，瞬态变量用于高级用例（即当有疑问时，使用常规过程变量）。

以下适用于瞬态变量：

- 根本没有存储瞬态变量的历史记录。
- 与*常规*变量一样，瞬态变量在设置时放在*最高父级*上。这意味着当在执行中设置变量时，临时变量实际上存储在流程实例执行中。与常规变量一样，如果应在特定执行或任务上设置变量，则存在方法的*局部变体。*
- 瞬态变量只能在流程定义中的下一个*等待状态之前被访问。*之后，他们就走了。等待状态在这里意味着流程实例中它被持久化到数据存储的点。请注意，在此定义中，*异步*活动也是*等待状态！*
- *瞬态变量只能由setTransientVariable(name, value)*设置，但在调用*getVariable(name)*时也会返回瞬态变量（也存在*getTransientVariable(name)*，它只检查瞬态变量）。这样做的原因是为了简化表达式的编写，并且使用变量的现有逻辑适用于这两种类型。
- 瞬态变量*隐藏*同名的持久变量。这意味着当在流程实例上同时设置持久变量和瞬态变量并且使用*getVariable("someVariable")时，将返回瞬态变量值。*

可以在大多数暴露常规变量的地方获取和/或设置瞬态变量：

- 关于*JavaDelegate*实现中的*DelegateExecution*
- *ExecutionListener*实现中的*DelegateExecution*和*TaskListener*实现中的*DelegateTask*
- 通过*执行*对象在脚本任务中
- 通过运行时服务启动流程实例时
- 完成任务时
- 调用*runtimeService.trigger*方法时



