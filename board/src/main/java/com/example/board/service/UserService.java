package com.example.board.service;

import com.example.board.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDAO userDAO;
    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    public void createUser(String id, String password, String email) {
        userDAO.createUser(id, password, email);
    }
}
