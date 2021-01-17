package com.spring.golub.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.spring.golub.entity.User;
import com.spring.golub.entity.UserPrincipal;
import com.spring.golub.exceptions.EmailNotFoundException;
import com.spring.golub.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(@NonNull String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new EmailNotFoundException("user with email " + email + " was not found!"));
        return new UserPrincipal(user);
    }
}
