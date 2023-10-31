package com.muhib.clickdown.controllers;

import com.muhib.clickdown.models.Board;
import com.muhib.clickdown.models.BoardUser;
import com.muhib.clickdown.models.User;
import com.muhib.clickdown.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<?> getMe(Principal principal){
        Optional<User> currentUser = userService.getUserRefByEmail(principal.getName());
        if (currentUser.isEmpty()){
            return new ResponseEntity<>("Not found.", HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<User>(currentUser.get(), HttpStatus.OK);
    }

    @GetMapping("/boards")
    public List<Board> getUserBoards(Principal principal){
        return userService.getUserByEmail(principal.getName())
                .getBoardUsers()
                .stream().map(
                        BoardUser::getBoard
                )
                .toList();
    }

}
