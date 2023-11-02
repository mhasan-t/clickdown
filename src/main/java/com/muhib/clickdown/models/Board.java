package com.muhib.clickdown.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT NOT NULL")
    private String description;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false, insertable = false)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<BoardUser> boardUsers;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    private List<Sprint> sprints;

    public Board(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
