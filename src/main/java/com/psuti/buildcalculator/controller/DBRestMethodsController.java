package com.psuti.buildcalculator.controller;

import com.psuti.buildcalculator.dao.CalculationStateRepository;
import com.psuti.buildcalculator.dao.UserPostRepository;
import com.psuti.buildcalculator.dao.UserStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.psuti.buildcalculator.util.ResponseMapUtil.wrapInItem;

@RequestMapping("/method")
@RestController
public class DBRestMethodsController {

    private final UserStateRepository userStateRepository;
    private final UserPostRepository userPostRepository;

    private final CalculationStateRepository calculationStateRepository;

    @Autowired
    public DBRestMethodsController(UserStateRepository userStateRepository,
                                   UserPostRepository userPostRepository,
                                   CalculationStateRepository calculationStateRepository){
        this.userStateRepository = userStateRepository;
        this.userPostRepository = userPostRepository;
        this.calculationStateRepository = calculationStateRepository;
    }

    @GetMapping("db.getAllExistingUserPosts")
    public Map<String, Object> getAllExistingUserPosts(){
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("response", wrapInItem(userPostRepository.findAll(),"items"));
        return responseMap;
    }

    @GetMapping("db.getAllExistingUserStates")
    public Map<String, Object> getAllExistingUserStates(){
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("response", wrapInItem(userStateRepository.findAll(),"items"));
        return responseMap;
    }

    @GetMapping("db.getAllExistingCalculatingStates")
    public Map<String, Object> getAllExistingCalculatingStates(){
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("response", wrapInItem(calculationStateRepository.findAll(),"items"));
        return responseMap;
    }


}
