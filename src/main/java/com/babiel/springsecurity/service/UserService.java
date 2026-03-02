package com.babiel.springsecurity.service;

import com.babiel.springsecurity.model.UserEntity;

public interface UserService {
    void createUser(UserEntity user);
    void updateUser(UserEntity user);
    void deleteUser(UserEntity user);
}
