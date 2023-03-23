package com.spring.jwt.controller;

import com.spring.jwt.request.UserReq;
import com.spring.jwt.service.UserService;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDTO> register(@RequestBody UserReq userReq) {
        return ResponseEntity.ok(userService.regiterAccount(userReq));
    }

}
