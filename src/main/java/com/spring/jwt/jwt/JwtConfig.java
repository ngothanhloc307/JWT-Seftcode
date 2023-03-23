package com.spring.jwt.jwt;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {

    @Value("${jwt.url:/jwt/login}")
    private String url;

    @Value("${jwt.header:Authorization}")
    private String header;

    @Value("${jwt.prefix:Bearer}")
    private String prefix;

    @Value("${jwt.expiration:#{60*60}}")
    private Long expiration;

    @Value("${jwt.secret:7638792F423F4528482B4D6250655368566D597133743677397A24432646294A}")
    private String secret;
}
