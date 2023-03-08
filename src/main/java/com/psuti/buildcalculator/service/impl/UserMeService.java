package com.psuti.buildcalculator.service.impl;

import com.psuti.buildcalculator.entities.User;
import com.psuti.buildcalculator.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserMeService{

    private final UserCrudService userCrudService;

    @Autowired
    public UserMeService(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }

    public User get(){
        return getAuthenticatedUser();
    }

    private User getAuthenticatedUser(){
        return userCrudService.getByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()
        );
    }
}
