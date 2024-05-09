package com.example.board.repository;

import com.example.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 아이디로 회원 정보 조회( SELECT * FROM user_table WHERE userId=?)
    Optional<UserEntity> findByUserId(String no);   // Optional null을 방지?

    Optional<UserEntity> findByNo(Long no);

    Optional<UserEntity> findByEmail(String email);
//
//    Optional<UserEntity> findByUserIdCheck(String userId);
}
