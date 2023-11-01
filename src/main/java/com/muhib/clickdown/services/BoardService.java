package com.muhib.clickdown.services;

import com.muhib.clickdown.models.Board;
import com.muhib.clickdown.models.BoardUser;
import com.muhib.clickdown.models.User;
import com.muhib.clickdown.repositories.BoardRepository;
import com.muhib.clickdown.repositories.BoardUserRepository;
import com.muhib.clickdown.services.types.BoardUserKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService{
    private final BoardRepository boardRepository;
    private final BoardUserRepository boardUserRepository;

    public List<Board> getBoards(){
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(int id){
        return boardRepository.findById(id);
    }

    public Board getRefById(int id) {
        return boardRepository.getReferenceById(id);
    }

    public void saveBoard(Board board){
        boardRepository.save(board);
    }

    public void saveBoard(Board board, User user){
        Board b = boardRepository.save(board);

        BoardUser bu = new BoardUser();
        bu.setId(BoardUserKey.builder()
                .boardId(b.getId())
                .userId(user.getId())
                .build()
        );
        bu.setBoard(b);
        bu.setUser(user);
        bu.setAdmin(true);

        boardUserRepository.save(bu);

    }

    public void saveUserOnBoard(Board board, User user, boolean isAdmin){
        boardUserRepository.save(BoardUser.builder()
                        .board(board)
                        .user(user)
                        .isAdmin(isAdmin)
                        .build());
    }
}
