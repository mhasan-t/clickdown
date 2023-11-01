package com.muhib.clickdown.controllers.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

public class REQUESTS {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CreateSprintRequest {
        String name;
        String description;
        int boardId;
        Timestamp startDate;
        Timestamp endDate;
    }


    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CreateBoardRequest {
        String name;
        String description;
    }
}
