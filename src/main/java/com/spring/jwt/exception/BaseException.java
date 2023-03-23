package com.spring.jwt.exception;

import com.spring.jwt.utils.BaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseException extends RuntimeException {

    private String code;

    private String message;

}
