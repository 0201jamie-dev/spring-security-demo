package com.babiel.springsecurity.service;

import com.babiel.springsecurity.model.CustomUser;
import com.babiel.springsecurity.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * service that loads the current user
 *
 * @author Jamie Augustin
 */
@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository
                    .findByUsernameOrEmailAddress(username, username)
                    .map(CustomUser::new)
                    .orElseThrow(() -> new UsernameNotFoundException("username/email address not found: " + username));
    }
}
