package com.foodOrder.service.impl;

import com.foodOrder.config.JwtProvider;
import com.foodOrder.model.User;
import com.foodOrder.repository.UserRepository;
import com.foodOrder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwt(String jwt) throws Exception {
        return null;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        return null;
    }
}
