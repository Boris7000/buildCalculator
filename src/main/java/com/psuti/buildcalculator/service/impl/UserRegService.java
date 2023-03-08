package com.psuti.buildcalculator.service.impl;

import com.psuti.buildcalculator.dto.AuthDto;
import com.psuti.buildcalculator.dto.RegDto;
import com.psuti.buildcalculator.entities.Token;
import com.psuti.buildcalculator.entities.User;
import com.psuti.buildcalculator.service.UserCrudService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UserRegService {
    private final UserCrudService userCrudService;
    private final UserAuthService userAuthService;


    public UserRegService(UserCrudService userCrudService,
                          UserAuthService userAuthService) {
        this.userCrudService = userCrudService;
        this.userAuthService = userAuthService;
    }

    public Token registration(RegDto regDto) throws Exception {
        if (userCrudService.existsByUsername(regDto.getEmail())) {
            throw new EntityExistsException("User with email: " + regDto.getEmail() + " already exists");
        }
        User user = fromRegDto(regDto);
        userCrudService.create(user);

        return userAuthService.authorization(new AuthDto(user.getEmail(), regDto.getPassword()));
    }

    private User fromRegDto(RegDto regDto){
        User user = new User();
        user.setEnabled(true);
        user.setEmail(regDto.getEmail());
        user.setPassword(regDto.getPassword());
        user.setFirstName(regDto.getFirstname());
        user.setLastName(regDto.getLastname());
        user.setSurname(regDto.getSurname());
        user.setPhone(regDto.getPhone());
        return user;
    }
}
