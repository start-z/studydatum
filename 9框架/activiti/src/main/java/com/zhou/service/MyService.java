package com.zhou.service;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BJB314
 */
@Service
@Slf4j
public class MyService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

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

    public List<Task> getTasks() {
        return taskService.createTaskQuery()
                .processDefinitionKey("vacationRequest")
//                .taskAssignee(assignee)
                .list();
    }

}
