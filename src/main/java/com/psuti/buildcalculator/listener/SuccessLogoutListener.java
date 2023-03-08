package com.psuti.buildcalculator.listener;

import com.psuti.buildcalculator.service.impl.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SuccessLogoutListener implements LogoutSuccessHandler {

    private final LogoutService logoutService;

    @Autowired
    public SuccessLogoutListener(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication){
        logoutService.logout(request);
    }
}

