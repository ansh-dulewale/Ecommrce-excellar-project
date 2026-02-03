package com.shopease.demo.service.impl;

import com.shopease.demo.dto.RegisterRequest;
import com.shopease.demo.dto.UserResponse;
import com.shopease.demo.entity.User;
import com.shopease.demo.repository.UserRepository;
import com.shopease.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    public static UserResponse touserResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole().name());
    }

    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())) throw new RuntimeException("Email Already Exist");
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        return touserResponse(userRepository.save(user));
    }
}
