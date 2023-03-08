package com.psuti.buildcalculator.controller;

import com.psuti.buildcalculator.dao.CalculationRepository;
import com.psuti.buildcalculator.dao.CalculationStateRepository;
import com.psuti.buildcalculator.dao.CustomerRepository;
import com.psuti.buildcalculator.entities.Calculation;
import com.psuti.buildcalculator.entities.CalculationState;
import com.psuti.buildcalculator.entities.Customer;
import com.psuti.buildcalculator.entities.User;
import com.psuti.buildcalculator.service.impl.UserMeService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.psuti.buildcalculator.util.ErrorCodesUtil.doesNotExistByIdError;
import static com.psuti.buildcalculator.util.ResponseMapUtil.wrapInItem;

@RequestMapping("/method")
@RestController
public class CalculationRestMethodsController {

    private final UserMeService userMeService;
    private final CustomerRepository customerRepository;
    private final CalculationRepository calculationRepository;
    private final CalculationStateRepository calculationStateRepository;

    public CalculationRestMethodsController(UserMeService userMeService,
                                            CustomerRepository customerRepository,
                                            CalculationRepository calculationRepository,
                                            CalculationStateRepository calculationStateRepository) {
        this.userMeService = userMeService;
        this.customerRepository = customerRepository;
        this.calculationRepository = calculationRepository;
        this.calculationStateRepository = calculationStateRepository;
    }

    @PostMapping("calculations.add")
    public Map<String, Object> add(@RequestParam(value = "customer_id") Integer customerId,
                                   @RequestParam(value = "address") String address,
                                   @RequestParam(value = "time_stump") Long timeStump){
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            Calculation calculation = new Calculation();
            calculation.setManagerId(userMeService.get().getId());
            calculation.setCustomerId(optionalCustomer.get().getId());
            calculation.setAddress(address);
            calculation.setDate(timeStump);
            Optional<CalculationState> optionalCalculationState = calculationStateRepository.findById(1);
            optionalCalculationState.ifPresent(calculation::setState);
            responseMap.put("response",wrapInItem(calculationRepository.save(calculation),"calculation"));
        } else {
            responseMap.put("error", doesNotExistByIdError(customerId,"Заказчика"));
        }
        return responseMap;
    }

    @GetMapping("calculations.get")
    public Map<String, Object> get(@RequestParam(value = "customer_id") Integer customerId){
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            User user = userMeService.get();
            HashMap<String, Object> responseBodyMap = new HashMap<>();
            responseBodyMap.put("items",calculationRepository.findByCustomerIdAndManagerId(customerId,user.getId()));
            responseBodyMap.put("manger_id",user.getId());
            responseBodyMap.put("customer_id",customerId);
            responseMap.put("response", responseBodyMap);
        } else {
            responseMap.put("error", doesNotExistByIdError(customerId,"Заказчика"));
        }
        return responseMap;
    }

    @GetMapping("calculations.getById")
    public Map<String, Object> getById(@RequestParam(value = "calculation_id") Integer calculationId,
                                   @RequestParam(value = "customer_id") Integer customerId){
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            User user = userMeService.get();
            Optional<Calculation> optionalCalculation = calculationRepository.findByIdAndCustomerIdAndManagerId(calculationId,customerId,user.getId());
            if(optionalCalculation.isPresent()){
                HashMap<String, Object> responseBodyMap = new HashMap<>();
                responseBodyMap.put("calculation",optionalCalculation.get());
                responseBodyMap.put("manger_id",user.getId());
                responseBodyMap.put("customer_id",customerId);
                responseMap.put("response",responseBodyMap);
            } else {
                responseMap.put("error", doesNotExistByIdError(calculationId,"Расчета"));
            }
        } else {
            responseMap.put("error", doesNotExistByIdError(customerId,"Заказчика"));
        }
        return responseMap;
    }

}
