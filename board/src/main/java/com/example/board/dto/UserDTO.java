package com.example.board.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String password;
    private String email;
    private String username;
}
