package com.example.board.repository;

import com.example.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //                            Repository에 종속될 <Entity 이름, 데이터타입>
    // 아이디로 회원 정보 조회( SELECT * FROM user_table WHERE userId=?)
    Optional<UserEntity> findByUserId(String no);   // Optional null을 방지?

    Optional<UserEntity> findByNo(Long no);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByUserId(String userId);

//    Optional<UserEntity> findByUserIdCheck(String userId);
}
