package com.muhib.clickdown.controllers.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BasicResponse {
    int status;
    String message;
}
