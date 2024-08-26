package com.foodOrder.service;

import com.foodOrder.model.User;

public interface IUserService {
    public User findUserByJwt(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
