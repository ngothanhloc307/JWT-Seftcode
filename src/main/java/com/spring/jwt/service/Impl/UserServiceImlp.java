package com.spring.jwt.service.Impl;

import com.spring.jwt.entity.Role;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.BaseException;
import com.spring.jwt.repository.RoleRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.request.UserReq;
import com.spring.jwt.service.UserService;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImlp implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public BaseResponseDTO regiterAccount(UserReq userReq) {
        BaseResponseDTO response = new BaseResponseDTO();

        validateAccount(userReq);

        User user = insertUser(userReq);

        try {
            userRepository.save(user);
            response.setCode(String.valueOf(HttpStatus.OK.value()));
            response.setMessage("Create new account successfully");
        } catch (Exception e) {
            response.setCode(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));
            response.setMessage("Service Unavailable");
        }
        return response;
    }

    private User insertUser(UserReq userReq) {
        User user = new User();
        user.setUsername(userReq.getUsername());
        user.setPassword(passwordEncoder.encode(userReq.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(userReq.getRole()));
        user.setRoles(roles);
        return user;
    }

    private void validateAccount(UserReq userReq) {
        if (ObjectUtils.isEmpty(userReq)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Data should not empty");
        }

        // validate duplicate username
        User user = userRepository.findByUsername(userReq.getUsername());
        if (!ObjectUtils.isEmpty(user)) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Username had exist");
        }

        // validate role
        List<String> roles = roleRepository.findAll().stream().map(Role::getName).toList();
        if (!roles.contains(userReq.getRole())) {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid role");

        }
    }
}
