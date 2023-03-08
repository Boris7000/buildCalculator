package com.psuti.buildcalculator.controller;

import com.psuti.buildcalculator.dao.UserPostRepository;
import com.psuti.buildcalculator.dao.UserRepository;
import com.psuti.buildcalculator.dao.UserStateRepository;
import com.psuti.buildcalculator.entities.User;
import com.psuti.buildcalculator.entities.UserPost;
import com.psuti.buildcalculator.entities.UserState;
import com.psuti.buildcalculator.service.impl.UserMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.psuti.buildcalculator.util.ErrorCodesUtil.doesNotExistByIdError;
import static com.psuti.buildcalculator.util.ResponseMapUtil.wrapInItem;

@RequestMapping("/method")
@RestController
public class MeRestMethodsController {

    private final UserMeService userMeService;
    private final UserRepository userRepository;
    private final UserStateRepository userStateRepository;
    private final UserPostRepository userPostRepository;

    @Autowired
    public MeRestMethodsController(UserMeService userMeService, UserRepository userRepository,
                                      UserStateRepository userStateRepository,
                                   UserPostRepository userPostRepository){
        this.userMeService = userMeService;
        this.userRepository = userRepository;
        this.userStateRepository = userStateRepository;
        this.userPostRepository = userPostRepository;
    }

    @GetMapping("me.get")
    public Map<String, Object> get(){
        HashMap<String, Object> responseMap = new HashMap<>();
        User user = userMeService.get();
        responseMap.put("response",wrapInItem(userRepository.save(user),"user"));
        return responseMap;
    }

    @PostMapping("me.editPersonalData")
    public Map<String, Object> editPersonalData(@RequestParam(value = "lastName") String lastName,
                                                @RequestParam(value = "firstName") String firstName,
                                                @RequestParam(value = "surname") String surname,
                                                @RequestParam(value = "phone") String phone){
        HashMap<String, Object> responseMap = new HashMap<>();
        User user = userMeService.get();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setSurname(surname);
        user.setPhone(phone);
        responseMap.put("response",wrapInItem(userRepository.save(user),"user"));
        return responseMap;
    }

    @PostMapping("me.editPost")
    public Map<String, Object> editPost(@RequestParam(value = "post_id") Integer postId){
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<UserPost> optionalUserPost = userPostRepository.findById(postId);
        if(optionalUserPost.isPresent()){
            User user = userMeService.get();
            user.setPost(optionalUserPost.get());
            responseMap.put("response",wrapInItem(userRepository.save(user),"user"));
        } else {
            responseMap.put("error", doesNotExistByIdError(postId,"Должности"));
        }
        return responseMap;
    }

    @PostMapping("me.editState")
    public Map<String, Object> editState(@RequestParam(value = "state_id") Integer stateId){
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<UserState> optionalUserState = userStateRepository.findById(stateId);
        if(optionalUserState.isPresent()){
            User user = userMeService.get();
            user.setState(optionalUserState.get());
            responseMap.put("response",wrapInItem(userRepository.save(user),"user"));
        } else {
            responseMap.put("error", doesNotExistByIdError(stateId,"Состояние"));
        }
        return responseMap;
    }

}
