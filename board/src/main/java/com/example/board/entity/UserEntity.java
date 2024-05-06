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
    private Long no;

    @Column
    private String userid;

    @Column
    private String password;

    @Column(unique = true)  // unique 제약조건 추가
    private String email;

    @Column
    private String username;

    public static UserEntity toUserEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setNo(userDTO.getNo());
        userEntity.setUserid(userDTO.getUserid());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUsername(userDTO.getUsername());
        return userEntity;
    }
}
