package com.babiel.springsecurity.service;

import com.babiel.springsecurity.model.SecurityUser;
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
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByUsername(username).isPresent()) {
            return userRepository
                    .findByUsername(username)
                    .map(SecurityUser::new)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
        }
        return userRepository
                .findByEmailAddress(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
