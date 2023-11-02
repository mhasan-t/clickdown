package com.muhib.clickdown.controllers;

import com.muhib.clickdown.models.Comment;
import com.muhib.clickdown.models.Sprint;
import com.muhib.clickdown.models.Task;
import com.muhib.clickdown.models.User;
import com.muhib.clickdown.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tasks ")
@RequiredArgsConstructor
public class TaskController {

    private final SprintService sprintService;
    private final UserService userService;
    private final TaskService taskService;
    private final CommentService commentService;

    @GetMapping("/{taskId}")
    public @ResponseBody ResponseEntity<?> getTaskById(@PathVariable(value = "taskId") int id){
        Task t = taskService.getTaskById(id);
        System.out.println("TASK - " + t.getId());
        return new ResponseEntity<Task>(t, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createTask(@Validated @RequestBody Map<String, String> request, Principal principal){
//        if (request.getFlag){
//            return new ResponseEntity<String>("Bad request.",HttpStatus.BAD_REQUEST);
//        }
        User currentUser = userService.getUserByEmail(principal.getName());

        try {
            Task task = new Task();
            task.setTitle(request.get("title"));
            task.setDescription(request.get("description"));
            task.setSprint(sprintService.getSprintRefById(Integer.parseInt(request.get("sprintId"))));

            taskService.save(task);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<String>("Sprint does not exist.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Created",HttpStatus.CREATED);
    }

    @GetMapping("/{sprintId}/tasks")
    public @ResponseBody ResponseEntity<?> getSprintTasks(@PathVariable(value = "sprintId") int id){
        Sprint s = sprintService.findSprintById(id);
        return new ResponseEntity<List<Task>>(s.getTasks(), HttpStatus.OK);
    }

    @PostMapping("/{taskId}/comment")
    public ResponseEntity<?> createComment(@RequestBody Map<String, String> request, @PathVariable(value = "taskId") int taskId, Principal principal){
        User currentUser = userService.getUserByEmail(principal.getName());
        Task task = taskService.getTaskById(taskId);

        try {
            Comment comment = new Comment();
            comment.setCreatedBy(currentUser);
            comment.setPostedTo(task);
            comment.setText(request.get("text"));

            commentService.save(comment);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<String>("Sprint does not exist.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Created",HttpStatus.CREATED);
    }
}
