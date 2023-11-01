package com.muhib.clickdown.controllers;

import com.muhib.clickdown.controllers.types.REQUESTS;
import com.muhib.clickdown.models.Board;
import com.muhib.clickdown.models.BoardUser;
import com.muhib.clickdown.models.Sprint;
import com.muhib.clickdown.models.User;
import com.muhib.clickdown.services.BoardService;
import com.muhib.clickdown.services.SprintService;
import com.muhib.clickdown.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
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
    public ResponseEntity<String> createBoard(@RequestBody REQUESTS.CreateBoardRequest request, Principal principal){
        if (request.getName() == null || request.getDescription() == null){
            return new ResponseEntity<String>("Bad request.",HttpStatus.BAD_REQUEST);
        }
        User currentUser = userService.getUserByEmail(principal.getName());

        Board newBoard = new Board();
        newBoard.setName(request.getName());
        newBoard.setDescription(request.getDescription());

        boardService.saveBoard(newBoard, currentUser);
        return new ResponseEntity<String>("Created",HttpStatus.CREATED);
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

}
