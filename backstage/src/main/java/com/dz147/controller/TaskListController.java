package com.dz147.controller;

import com.dz147.dao.TaskListMapper;
import com.dz147.entity.TaskList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@CrossOrigin(value = "*", methods = {RequestMethod.GET, RequestMethod.POST,
        RequestMethod.DELETE, RequestMethod.PUT},
        allowedHeaders = {"Content-Type", "myCross"}, allowCredentials = "true",
        maxAge = 888888)
public class TaskListController {

    @Autowired
    private TaskListMapper taskListMapper;


    @RequestMapping(value = "/todo", method = RequestMethod.GET, produces = "application/json;charset=utf-8;")
    public @ResponseBody
    List<TaskList> getTaskList() {
        System.out.println(taskListMapper.selectAll());
        return taskListMapper.selectAll();
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
    public @ResponseBody
    String addTask(@RequestBody TaskList taskList) {
        taskList.setStatus("未完成");
        int insert = taskListMapper.insert(taskList);
        if (insert > 0)
        {
            return "{\"msg\":\"成功\"}";
        }
            return "{\"msg\":\"失败\"}";
    }

    @RequestMapping(value = "/delTask/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8;")
    public @ResponseBody
    String deleteTask(@PathVariable("id") int id) {
        int i = taskListMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return "{\"msg\":\"删除成功\"}";
        }
        return "{\"msg\":\"删除失败\"}";
    }

    // @ExceptionHandler 进行类内异常处理:
    @ExceptionHandler()
    public @ResponseBody
    Object exceptionHandler(Exception ex) {
        return ex;
    }
}
