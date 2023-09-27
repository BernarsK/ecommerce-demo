package com.bernarsk.userservice.service;

import com.bernarsk.userservice.model.User;
import com.bernarsk.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}
