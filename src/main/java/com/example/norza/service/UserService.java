package com.example.norza.service;

import com.example.norza.domain.User;
import com.example.norza.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void save(@Validated User user) {
        userRepository.save(user);
    }

}
