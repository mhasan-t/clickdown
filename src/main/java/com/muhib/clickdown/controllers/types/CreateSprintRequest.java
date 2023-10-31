package com.muhib.clickdown.controllers.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSprintRequest {
    String name;
    String description;
    int boardId;
    Timestamp startDate;
    Timestamp endDate;
}
