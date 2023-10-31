package com.muhib.clickdown.repositories;


import com.muhib.clickdown.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
//    public List<Comment> findByTaskId(int taskId);
//    public List<Comment> findByUserId(int userId);
}
