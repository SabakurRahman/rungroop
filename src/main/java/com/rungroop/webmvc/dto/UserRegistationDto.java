package com.rungroop.webmvc.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistationDto {
    private Long Id;
    @NotEmpty(message = "username is mandatory")
    private String username;
    @NotEmpty(message = "email is mandatory")
    private String email;
    @NotEmpty(message = "password is mandatory")
    private String password;
}
