package com.muhib.clickdown.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muhib.clickdown.models.types.StatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@DynamicInsert
public class Task {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT NULL")
    private String description;

    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.PENDING;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false, insertable = false)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "postedTo")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    @JsonIgnore
    private Sprint sprint;

    public Task(String title, String description, List<Comment> comments, Sprint sprint) {
        this.title = title;
        this.description = description;
        this.comments = comments;
        this.sprint = sprint;
    }
}
