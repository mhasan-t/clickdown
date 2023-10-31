package com.muhib.clickdown.services;

import com.muhib.clickdown.models.Task;
import com.muhib.clickdown.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task getTaskById(int id){
        return taskRepository.findById(id).get();
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public List<Task> getTasksBySprint(int sprintId){
        return taskRepository.findBySprintId(sprintId);
    }

}
