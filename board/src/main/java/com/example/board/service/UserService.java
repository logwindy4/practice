package com.example.board.service;

import com.example.board.dto.UserDTO;
import com.example.board.entity.UserEntity;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(UserDTO userDTO) {
        // 1. dto -> Entity 변환 (DTO는 데이터 전송을 위한 용도로 사용되고, Entity는 데이터베이스와의 상호작용을 위한 용도)
        // 2. repository의 createUser 메서드 호출
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
        System.out.println("userDTO = " + userDTO);
        // repository의 createUser 메서드 호출 ( 조건. entity객체를 넘겨줘야 함)
    }
}
