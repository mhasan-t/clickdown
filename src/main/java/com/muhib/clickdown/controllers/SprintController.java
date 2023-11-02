package com.muhib.clickdown.controllers;

import com.muhib.clickdown.models.Sprint;
import com.muhib.clickdown.models.Task;
import com.muhib.clickdown.models.User;
import com.muhib.clickdown.services.BoardService;
import com.muhib.clickdown.services.SprintService;
import com.muhib.clickdown.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController @RequiredArgsConstructor
@RequestMapping("/sprints")
public class SprintController {
    private final SprintService sprintService;
    private final UserService userService;
    private final BoardService boardService;

    @GetMapping("/{sprintId}")
    public @ResponseBody ResponseEntity<?> getSprintById(@PathVariable(value = "sprintId") int id){
        Sprint s = sprintService.findSprintById(id);
        return new ResponseEntity<Sprint>(s, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> createSprint(@Valid @RequestBody Sprint request, Principal principal){
//        if (request.getFlag){
//            return new ResponseEntity<String>("Bad request.",HttpStatus.BAD_REQUEST);
//        }
        User currentUser = userService.getUserByEmail(principal.getName());

        try {
            Sprint sprint = new Sprint();
            sprint.setTitle(request.getTitle());
            sprint.setDescription(request.getDescription());
            sprint.setStartDate(Timestamp.valueOf(request.getStartDate().toLocalDateTime()));
            sprint.setEndDate(request.getEndDate());
//            sprint.setBoard(boardService.getBoardById(request.getBoardId()).get());
            sprint.setBoard(request.getBoard());
            sprintService.save(sprint);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<String>("Board does not exist.",HttpStatus.NOT_FOUND);
        }
        catch (HttpMessageNotReadableException e){
            return new ResponseEntity<String>("Bad time format.",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Created",HttpStatus.CREATED);
    }

    @GetMapping("/{sprintId}/tasks")
    public @ResponseBody ResponseEntity<?> getSprintTasks(@PathVariable(value = "sprintId") int id){
        Sprint s = sprintService.findSprintById(id);
        return new ResponseEntity<List<Task>>(s.getTasks(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
