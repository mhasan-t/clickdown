package com.muhib.clickdown.services;


import com.muhib.clickdown.models.Comment;
import com.muhib.clickdown.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

//    public List<Comment> getByTaskId(int taskId){
//        return commentRepository.findByTaskId(taskId);
//    }
//
//    public List<Comment> getByUserId(int userId){
//        return commentRepository.findByUserId(userId);
//    }
}
