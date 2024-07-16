package com.example.bestalbam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bestalbam.model.User;
import com.example.bestalbam.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 一覧
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // 追加
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
