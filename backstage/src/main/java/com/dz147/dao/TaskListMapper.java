package com.dz147.dao;

import com.dz147.entity.TaskList;
import java.util.List;

public interface TaskListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskList record);

    TaskList selectByPrimaryKey(Integer id);

    List<TaskList> selectAll();

    int updateByPrimaryKey(TaskList record);

}