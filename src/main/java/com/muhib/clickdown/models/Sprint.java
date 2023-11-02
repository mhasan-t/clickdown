package com.muhib.clickdown.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Sprint {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;
    @Column(nullable = false)
    @NotBlank(message = "Title must be given.")
    private String title;
    @Column(nullable = false) private String description;
    @Column  private Timestamp startDate;
    @Column private Timestamp endDate;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false, insertable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(
            name = "board_id",
            nullable = false
    )
    @JsonIgnore
    private Board board;

    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks;
}
