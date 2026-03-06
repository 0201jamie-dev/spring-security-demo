package com.babiel.springsecurity.service;

import com.babiel.springsecurity.model.UserEntity;

public interface UserService {
    /**
     * tells the repository to create a user in the database
     *
     * @param user user object
     */
    void createUser(UserEntity user);

    /**
     * tells the repository to update the user in the database
     *
     * @param user user object
     */
    void updateUser(UserEntity user);

    /**
     * tells the repository to delete the user out of the database
     *
     * @param user user object
     */
    void deleteUser(UserEntity user);

    boolean existsUserByUsernameOrEmailAddress(String username, String emailAddress);

    boolean existsUserByEmailAddress(String emailAddress);

    boolean existsUserByUsername(String username);
}
