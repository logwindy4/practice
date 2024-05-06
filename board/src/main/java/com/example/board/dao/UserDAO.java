package com.example.board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(String id, String password, String email, String name){
        String sql = "INSERT INTO (id, password, email, name) VALUES (?,?,?)";
        jdbcTemplate.update(sql, id, password, email, name);
    }
}
