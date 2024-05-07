package com.example.board.service;

import com.example.board.dto.UserDTO;
import com.example.board.entity.UserEntity;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO login(UserDTO userDTO) {
        Optional<UserEntity> byUserUserId = userRepository.findByUserId(userDTO.getUserId());
        System.out.println("DTO에서 값을 가져오니? = " + userDTO);
        if(byUserUserId.isPresent()){
            // 조회 결과가 있다 (해당 아이디를 가진 회원 정보가 있다면)
            System.out.println("값을 가져오면 true = " + byUserUserId.isPresent());
            UserEntity userEntity = byUserUserId.get();
            if(userEntity.getPassword().equals(userDTO.getPassword())){
                // 비밀번호 일치
                // Entity -> DTO 변환후 리턴
                System.out.println("비밀번호 = " + userDTO.getPassword());
                UserDTO dto = UserDTO.toUserDTO(userEntity);
                return dto;
            }else{
                // 비밀번호 불일치
                System.out.println("비밀번호 불일치");
                return null;
            }
        }else{
            System.out.println("userDTO = " + userDTO);
            System.out.println("아이디 없음");
            return null;
        }
    }

    public void createUser(UserDTO userDTO) {
        // 1. dto -> Entity 변환 (DTO는 데이터 전송을 위한 용도로 사용되고, Entity는 데이터베이스와의 상호작용을 위한 용도)
        // 2. repository의 createUser 메서드 호출
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
        System.out.println("userDTO = " + userDTO);
        // repository의 createUser 메서드 호출 ( 조건. entity객체를 넘겨줘야 함)
    }
}
