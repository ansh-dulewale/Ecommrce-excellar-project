package com.shopease.demo.service.impl;

import com.shopease.demo.dto.RegisterRequest;
import com.shopease.demo.dto.UserResponse;
import com.shopease.demo.repository.UserRepository;
import com.shopease.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        return null;
    }
}
