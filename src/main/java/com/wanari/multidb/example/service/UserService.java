package com.wanari.multidb.example.service;

import com.wanari.multidb.example.domain.User;
import com.wanari.multidb.example.repository.UserRepository;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Option<User> findByEmail(String email) {
        return Option.of(userRepository.findByEmail(email));
    }
}
