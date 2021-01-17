package com.spring.golub.service;

import com.spring.golub.dto.UserLoginDTO;
import com.spring.golub.entity.User;
import com.spring.golub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> findByUserLogin(UserLoginDTO userLoginDTO) {
        return userRepository.findByEmailAndPassword(
                userLoginDTO.getEmail(), encodePassword(userLoginDTO.getPassword()));
    }

    private static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
