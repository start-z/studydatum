package com.zhou.controller;

import com.zhou.service.MyService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MyRestController {

    @Autowired
    private MyService myService;

    @Resource
    TaskService taskService;


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
                .addClasspathResource("processes/VacationRequest.bpmn20.xml")
                .deploy();
        System.out.println(deploy + " 创建成功");
    }

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public void startProcessInstance() {
        //启动实例
        myService.startProcess();
    }

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

    /**
     * 查看当前任务流程
     *
     * @return 任务列表
     */
    @RequestMapping(value = "/feachTasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object feachTasks() {
        List<Task> list = taskService.createTaskQuery().taskCandidateGroup("management").list();
        List<Object> dtos = new ArrayList<Object>();
        for (Task task : list) {
            dtos.add(task);
        }
        return dtos;
    }

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

    /**
     * 获取流程图
     *
     * @param response 返回流
     */
    @RequestMapping(value = "getProcessImg", method = RequestMethod.GET)
    public void getProcessImg(HttpServletResponse response) {
        RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine().getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("vacationRequest")
                .latestVersion()
                .singleResult();

        String diagramResourceName = processDefinition.getDiagramResourceName();
        InputStream imageStream = repositoryService.getResourceAsStream(
                processDefinition.getDeploymentId(), diagramResourceName);
        try {
            IOUtils.copy(imageStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class TaskRepresentation {

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
