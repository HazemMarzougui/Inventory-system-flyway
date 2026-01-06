package com.example.invertorymanagementsystem.Dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private String password;   // only used in creation, will be hashed
    private Set<String> roles;
}

