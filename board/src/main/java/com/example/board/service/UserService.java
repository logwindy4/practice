package com.example.board.service;

import com.example.board.dao.UserDAO;
import com.example.board.dto.UserDTO;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(UserDTO userDTO) {

    }
}
