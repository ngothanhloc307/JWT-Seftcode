package com.spring.jwt.service;

import com.spring.jwt.request.UserReq;
import com.spring.jwt.utils.BaseResponseDTO;

public interface UserService {

    BaseResponseDTO regiterAccount(UserReq userReq);
}
