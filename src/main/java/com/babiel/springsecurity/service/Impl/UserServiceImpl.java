package com.babiel.springsecurity.service.Impl;

import com.babiel.springsecurity.model.UserEntity;
import com.babiel.springsecurity.repo.UserRepository;
import com.babiel.springsecurity.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final AuthenticationManager authManager;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.authManager = authManager;
    }

    @Override
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(UserEntity user) {
        userRepository.delete(user);
    }

    @Override
    public boolean existsUserByUsernameOrEmailAddress(String username, String emailAddress) {
        return userRepository.findByUsernameOrEmailAddress(username, emailAddress).isPresent();
    }
    @Override
    public boolean existsUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress).isPresent();
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserEntity getUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress).get();
    }
}