package com.babiel.springsecurity.model;

import com.babiel.springsecurity.enums.Role;
import com.babiel.springsecurity.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * An user.
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends AbstractEntity {
    /**
     * User name. Must be unique.
     */
    @Column(unique = true)
    @NotNull
    @Size(min = 2)
    private String username;

    /**
     * Email address. Must be unique.
     */
    @Column(unique = true)
    @NotEmpty
    @Email
    private String emailAddress;

    /**
     * Real name.
     */
    @NotNull
    @NotEmpty
    private String realname;

    /**
     * Password.
     */
    @NotNull
    @Size(min = 1)
    private String password;

    /**
     * A list of roles.
     */
    @NotNull
    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    private UserStatus userStatus;

    public UserEntity(String username, String emailAddress, String realname, String password, List<Role> roles, UserStatus userStatus) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.realname = realname;
        this.password = password;
        this.roles = roles;
        this.userStatus = userStatus;
    }
}