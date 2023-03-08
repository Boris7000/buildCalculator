package com.psuti.buildcalculator.service.impl;

import com.psuti.buildcalculator.util.TokenParseFromRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LogoutService {

    private final TokenParseFromRequestUtil tokenParseFromRequestUtil;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public LogoutService(TokenParseFromRequestUtil tokenParseFromRequestUtil,
                         JwtTokenService jwtTokenService) {
        this.tokenParseFromRequestUtil = tokenParseFromRequestUtil;
        this.jwtTokenService = jwtTokenService;
    }

    public void logout(HttpServletRequest request){
        logout(tokenParseFromRequestUtil.parse(request));
    }

    public void logout(String token){
        jwtTokenService.kill(token);
    }
}
