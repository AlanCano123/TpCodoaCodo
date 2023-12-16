package com.TP.TP.models.dtos;


import com.TP.TP.models.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String username;

    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String dni;

    private UserRole role;

}

