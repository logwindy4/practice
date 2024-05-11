package com.example.board.dto;

import com.example.board.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long no;
    private String userId;
    private String password;
    private String email;
    private String username;
    private String role;

    // DTO는 화면단에서 데이터를 전달받거나 전달할때 사용
    public static UserDTO toUserDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setNo(userEntity.getNo());
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }
}
