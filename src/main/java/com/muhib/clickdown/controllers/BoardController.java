package com.muhib.clickdown.controllers;

import com.muhib.clickdown.models.Board;
import com.muhib.clickdown.models.BoardUser;
import com.muhib.clickdown.models.Sprint;
import com.muhib.clickdown.models.User;
import com.muhib.clickdown.services.BoardService;
import com.muhib.clickdown.services.SprintService;
import com.muhib.clickdown.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final SprintService sprintService;
    private final UserService userService;

    @GetMapping("/{boardId}")
    public @ResponseBody ResponseEntity<?> getBoardById(@PathVariable(value = "boardId") int id){
        Optional<Board> b = boardService.getBoardById(id);
        if (b.isPresent()) {
            Board board = b.get();
            return new ResponseEntity<Board>(board, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Does not exist.",HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<String> createBoard(@Valid @RequestBody Board request, Principal principal){
        User currentUser = userService.getUserByEmail(principal.getName());

        Board newBoard = new Board();
        newBoard.setName(request.getName());
        newBoard.setDescription(request.getDescription());

        boardService.saveBoard(newBoard, currentUser);
        return new ResponseEntity<String>("Board Created",HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getMyBoards(Principal principal){
        User currentUser = userService.getUserByEmail(principal.getName());
        List<Board> boards = currentUser.getBoardUsers().stream().map(
                BoardUser::getBoard
        ).toList();
        return new ResponseEntity<List<Board>>(boards, HttpStatus.OK);
    }

    @GetMapping("/{boardId}/sprints")
    public @ResponseBody ResponseEntity<?> getBoardSprints(@PathVariable(value = "boardId") int id){
        Optional<Board> b = boardService.getBoardById(id);
        if (b.isEmpty()){
            return new ResponseEntity<String>("Does not exist.",HttpStatus.NOT_FOUND);
        }
        System.out.println("HERE");
        List<Sprint> sprints = sprintService.findByBoard(id);
        System.out.println(sprints.get(0).getId());

        return new ResponseEntity<List<Sprint>>(sprints, HttpStatus.OK);
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
