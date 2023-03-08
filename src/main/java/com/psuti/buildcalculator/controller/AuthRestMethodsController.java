package com.psuti.buildcalculator.controller;

import com.psuti.buildcalculator.dto.AuthDto;
import com.psuti.buildcalculator.dto.RegDto;
import com.psuti.buildcalculator.event.OnAuthorizationEvent;
import com.psuti.buildcalculator.event.OnRegistrationEvent;
import com.psuti.buildcalculator.service.impl.UserAuthService;
import com.psuti.buildcalculator.service.impl.UserRegService;
import com.psuti.buildcalculator.util.ResponseMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/method")
@RestController
public class AuthRestMethodsController {

    private final UserAuthService userAuthService;
    private final UserRegService userRegService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public AuthRestMethodsController(UserAuthService userAuthService, UserRegService userRegService,
                                     ApplicationEventPublisher applicationEventPublisher) {
        this.userAuthService = userAuthService;
        this.userRegService = userRegService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostMapping("auth.login")
    public Map<String, Object> login(@RequestParam("email") String email,
                                     @RequestParam("pass") String pass) {
        HashMap<String, Object> responseMap = new HashMap<>();

        AuthDto authDto = new AuthDto();
        authDto.setEmail(email);
        authDto.setPassword(pass);

        applicationEventPublisher.publishEvent(new OnAuthorizationEvent(authDto));

        try {
            HashMap<String, Object> loginMap = new HashMap<>();
            loginMap.put("token", userAuthService.authorization(authDto));
            responseMap.put("response", loginMap);
        } catch (Exception e) {
            responseMap.put("error", invalidAuthError());
        }

        return responseMap;
    }

    @PostMapping("auth.registration")
    public Map<String, Object> registration(@RequestParam("email") String email,
                                            @RequestParam("pass") String pass,
                                            @RequestParam(value = "lastName") String lastName,
                                            @RequestParam(value = "firstName") String firstName,
                                            @RequestParam(value = "surname") String surname,
                                            @RequestParam(value = "phone") String phone) {
        HashMap<String, Object> responseMap = new HashMap<>();

        RegDto regDto = new RegDto();
        regDto.setEmail(email);
        regDto.setPassword(pass);
        regDto.setLastname(lastName);
        regDto.setFirstname(firstName);
        regDto.setSurname(surname);
        regDto.setPhone(phone);

        applicationEventPublisher.publishEvent(new OnRegistrationEvent(regDto));

        try {
            HashMap<String, Object> loginMap = new HashMap<>();
            loginMap.put("token", userRegService.registration(regDto));
            responseMap.put("response", loginMap);
        } catch (Exception e) {
            responseMap.put("error", invalidRegistrationError());
        }

        return responseMap;
    }


    private HashMap<String, Object> invalidAuthError(){
        return ResponseMapUtil.wrapInError("Неправильный логин или пароль", 503);
    }

    private HashMap<String, Object> invalidRegistrationError(){
        return ResponseMapUtil.wrapInError("Такой пользователь уже существует", 504);
    }

}
