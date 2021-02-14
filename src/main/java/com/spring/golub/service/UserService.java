package com.spring.golub.service;

import com.spring.golub.dto.UserLoginDTO;
import com.spring.golub.dto.UserRegistrationDTO;
import com.spring.golub.entity.RoleType;
import com.spring.golub.entity.User;
import com.spring.golub.exceptions.IllegalEmailException;
import com.spring.golub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void saveNewUser(UserRegistrationDTO userRegDTO) {
        try {
            User user = User.builder()
                    .firstName(userRegDTO.getFirstName())
                    .lastName(userRegDTO.getLastName())
                    .email(userRegDTO.getEmail())
                    .password(encodePassword(userRegDTO.getPassword()))
                    .role(RoleType.USER)
                    .balance(new BigDecimal(0.0))
                    .build();
            userRepository.save(user);
        } catch (Exception ex) {
            log.info("{This Email already exists}");
            throw new IllegalEmailException(ex);
        }
    }

    private static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
