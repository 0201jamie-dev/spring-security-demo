package com.babiel.springsecurity.service.Impl;

import com.babiel.springsecurity.model.UserEntity;
import com.babiel.springsecurity.repo.UserRepository;
import com.babiel.springsecurity.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserEntity user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(UserEntity user) {
        userRepository.delete(user);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean existsUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress).isPresent();
    }
}