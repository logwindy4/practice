package com.example.board.entity;

import com.example.board.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="user_table")
public class UserEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "no")
    private Long no;

    @Column(name = "userId", unique = true)
    private String userId;

    @Column(unique = true)
    private String password;

    @Column(name ="userEmail", unique = true)  // unique 제약조건 추가
    private String email;

    @Column
    private String username;
    // Entity는 DB와의 상호작용할때 사용

    @Column(name = "role")
    public String role;

    public static UserEntity toUserEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setNo(userDTO.getNo());
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setRole(userDTO.getRole());
        return userEntity;
    }

    public static UserEntity toUpdateUserEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setNo(userDTO.getNo());
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setRole(userDTO.getRole());
        return userEntity;
    }
}
