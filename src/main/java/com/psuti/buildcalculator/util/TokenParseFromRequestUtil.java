package com.psuti.buildcalculator.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenParseFromRequestUtil {

    private final JwtUserIdTokenUtil jwtUserIdTokenUtil;

    @Autowired
    public TokenParseFromRequestUtil(JwtUserIdTokenUtil jwtUserIdTokenUtil) {
        this.jwtUserIdTokenUtil = jwtUserIdTokenUtil;
    }

    public String parse(HttpServletRequest request){
        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Authorization token not found");
        }
        return requestTokenHeader.split(" ")[1];
    }
}
