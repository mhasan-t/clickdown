package com.muhib.clickdown.services.types;

import com.muhib.clickdown.models.types.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private Role role;
}