package com.example.board.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long no;
    private String userid;
    private String password;
    private String email;
    private String username;
}
