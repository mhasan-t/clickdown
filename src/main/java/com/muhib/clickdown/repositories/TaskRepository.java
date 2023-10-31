package com.muhib.clickdown.repositories;

import com.muhib.clickdown.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    public List<Task> findBySprintId(int sprintId);
}
