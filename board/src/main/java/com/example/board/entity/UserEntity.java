package com.example.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="user_table")
public class UserEntity {
    @Id // pk 지정
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column
    private String id;

    @Column(unique = true)  // unique 제약조건 추가
    private String password;

    @Column
    private String email;

    @Column
    private String username;
}
