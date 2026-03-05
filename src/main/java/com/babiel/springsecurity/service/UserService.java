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

    /**
     * checks if a user with the given username already exists in the database
     *
     * @param username username to compare with in the database
     * @return boolean that indicates whether the given username already exists in the database or not
     */
    boolean existsUserByUsername(String username);

    /**
     * checks if a user with the given email address already exists in the database
     *
     * @param emailAddress email address to compare with in the database
     * @return boolean that indicates whether the given email address already exists in the database or not
     */
    boolean existsUserByEmailAddress(String emailAddress);
}
