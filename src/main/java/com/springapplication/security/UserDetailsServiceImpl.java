package com.springapplication.security;

import com.springapplication.model.User;
import com.springapplication.repository.UserRepository;
import com.springapplication.security.jwt.JwtUserFactory;
import com.springapplication.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException("User with username: " + username + " not found")
        );

        return JwtUserFactory.create(user);
    }
}
