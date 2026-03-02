package com.babiel.springsecurity;

import com.babiel.springsecurity.model.UserEntity;
import com.babiel.springsecurity.roles.Role;
import com.babiel.springsecurity.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExampleData {
    private final UserService userService;

    public ExampleData(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        // create user
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(Role.ROLE_USER);

        UserEntity user1 = new UserEntity();
        user1.setEmailAddress("user1@gmx.de");
        user1.setRealname("Max Mueller");
        user1.setPassword(encoder.encode("1234"));
        user1.setUsername("user1");
        user1.setRoles(userRoles);

        userService.createUser(user1);

        // create admin
        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(Role.ROLE_ADMIN);

        UserEntity admin = new UserEntity();
        admin.setEmailAddress("admin@gmx.de");
        admin.setRealname("Timo Berg");
        admin.setPassword(encoder.encode("1234"));
        admin.setUsername("admin");
        admin.setRoles(adminRoles);

        userService.createUser(admin);

        // create admin user
        List<Role> adminUserRoles = new ArrayList<>();
        adminUserRoles.add(Role.ROLE_USER);
        adminUserRoles.add(Role.ROLE_ADMIN);

        UserEntity adminuser = new UserEntity();
        adminuser.setEmailAddress("adminuser@gmx.de");
        adminuser.setRealname("Lara Schmidt");
        adminuser.setPassword(encoder.encode("1234"));
        adminuser.setUsername("adminuser");
        adminuser.setRoles(adminUserRoles);

        userService.createUser(adminuser);
    }
}
