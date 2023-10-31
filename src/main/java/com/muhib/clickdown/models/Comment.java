package com.muhib.clickdown.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String text;


    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User createdBy;


    @ManyToOne
    @JoinColumn(
            name = "task_id"
    )
    @JsonIgnore
    private Task postedTo;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false, insertable = false)
    private Timestamp createdAt;

    public Comment(String text, Task postedTo, Timestamp createdAt) {
        this.text = text;
        this.postedTo = postedTo;
        this.createdAt = createdAt;
    }
}
