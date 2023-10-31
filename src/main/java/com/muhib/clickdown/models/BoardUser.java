package com.muhib.clickdown.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muhib.clickdown.services.types.BoardUserKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class BoardUser {
    @EmbeddedId
    private BoardUserKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @MapsId("boardId")
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private Board board;

    @Column
    @ColumnDefault("0")
    private boolean isAdmin;

    public BoardUser(User user, Board board, boolean isAdmin) {
        this.user = user;
        this.board = board;
        this.isAdmin = isAdmin;
    }

}
