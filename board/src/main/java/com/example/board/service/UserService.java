package com.example.board.service;

import com.example.board.dto.UserDTO;
import com.example.board.entity.UserEntity;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    // Repository는 데이터베이스와의 상호작용을 담당하며, 이를 통해 Entity 객체를 저장, 조회, 수정, 삭제할 수 있다.
    // DTO는 Entity와의 상호작용을 담당, DTO는 데이터 전송을 위한 객체로, 주로 서로 다른 시스템 또는 계층 간에 데이터를 전송할 때 사용


    public List<UserDTO> findAll() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserEntity userEntity: userEntityList ){
            userDTOList.add(UserDTO.toUserDTO(userEntity));
            System.out.println("userDTOList = " + userDTOList);
        }
        return userDTOList;
    }

    public UserDTO findByNo(Long no) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByNo(no);
        if(optionalUserEntity.isPresent()){
//            UserEntity userEntity = optionalUserEntity.get();
//            UserDTO.toUserDTO(userEntity);
//            return userDTO;
            return UserDTO.toUserDTO(optionalUserEntity.get());
        }else{
            return null;
        }
    }
}
