package com.zhou.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BJB314
 */
@Service
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;
    public void startProcess(String demo) {
        ProcessInstance oneTaskProcess = runtimeService.startProcessInstanceByKey("test");
        System.out.println("流程定义id：" + oneTaskProcess.getProcessDefinitionId());
        System.out.println("流程实例id：" + oneTaskProcess.getId());
        System.out.println("当前活动Id：" + oneTaskProcess.getActivityId());
    }

    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery()
                .processDefinitionKey("test")
//                .taskAssignee(assignee)
                .list();
    }

}
