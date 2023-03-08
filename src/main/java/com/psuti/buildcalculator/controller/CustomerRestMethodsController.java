package com.psuti.buildcalculator.controller;

import com.psuti.buildcalculator.dao.CustomerRepository;
import com.psuti.buildcalculator.entities.Customer;
import com.psuti.buildcalculator.util.ResponseMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.psuti.buildcalculator.util.ErrorCodesUtil.doesNotExistByIdError;
import static com.psuti.buildcalculator.util.ResponseMapUtil.wrapInItem;

@RequestMapping("/method")
@RestController
public class CustomerRestMethodsController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerRestMethodsController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping("customers.getById")
    public Map<String, Object> getById(@RequestParam("id") Integer id){
        HashMap<String, Object> responseMap = new HashMap<>();
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            responseMap.put("response",wrapInItem(optionalCustomer.get(),"customer"));
        } else {
            responseMap.put("error", doesNotExistByIdError(id,"Заказчика"));
        }
        return responseMap;
    }

    @GetMapping("customers.getByName")
    public Map<String, Object> getByName(
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "surname", required = false) String surname){
        List<Customer> customers;
        if(firstName!=null&&surname!=null){
            customers = customerRepository.findByLastNameAndFirstNameAndSurname(lastName,firstName,surname);
        } else {
            if(firstName!=null) {
                customers = customerRepository.findByLastNameAndFirstName(lastName, firstName);
            } else {
                customers = customerRepository.findByLastName(lastName);
            }
        }
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("response", wrapInItem(customers,"items"));
        return responseMap;
    }

    @GetMapping("customers.getByPhone")
    public Map<String, Object> getByPhone(@RequestParam("phone") String phone){
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("response", wrapInItem(customerRepository.findByPhone(phone),"items"));
        return responseMap;
    }

    @GetMapping("customers.getByEmail")
    public Map<String, Object> getByEmail(@RequestParam("email") String email){
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("response", wrapInItem(customerRepository.findByEmail(email),"items"));
        return responseMap;
    }

    @PostMapping("customers.add")
    public Map<String, Object> add(@RequestParam(value = "lastName") String lastName,
                                   @RequestParam(value = "firstName") String firstName,
                                   @RequestParam(value = "surname") String surname,
                                   @RequestParam(value = "phone") String phone,
                                   @RequestParam(value = "email") String email,
                                   @RequestParam(value = "address") String address){
        HashMap<String, Object> responseMap = new HashMap<>();
        if(customerRepository.existsByLastNameAndFirstNameAndSurnameAndEmail(lastName,firstName,surname,email)){
            responseMap.put("error", alreadyExistError(lastName, firstName, surname, email));
        } else {
            Customer customer = fromParams(lastName,firstName,surname,phone,email,address);
            responseMap.put("response",wrapInItem(customerRepository.save(customer),"customer"));
        }
        return responseMap;
    }

    @PostMapping("customers.edit")
    public Map<String, Object> edit(@RequestParam(value = "id") Integer id,
                        @RequestParam(value = "lastName") String lastName,
                        @RequestParam(value = "firstName") String firstName,
                        @RequestParam(value = "surname") String surname,
                        @RequestParam(value = "phone") String phone,
                        @RequestParam(value = "email") String email,
                        @RequestParam(value = "address") String address){
        HashMap<String, Object> responseMap = new HashMap<>();
        if(customerRepository.existsById(id)){
            if(customerRepository.existsByLastNameAndFirstNameAndSurnameAndEmail(lastName,firstName,surname,email)){
                responseMap.put("error", alreadyExistError(lastName,firstName,surname,email));
            } else {
                Customer customer = fromParams(lastName,firstName,surname,phone,email,address);
                customer.setId(id);
                responseMap.put("response",wrapInItem(customerRepository.save(customer),"customer"));
            }
        } else {
            responseMap.put("error", doesNotExistByIdError(id,"Заказчика"));
        }
        return responseMap;
    }

    @PostMapping("customers.remove")
    public Map<String, Object> remove(@RequestParam("id") Integer id){
        HashMap<String, Object> responseMap = new HashMap<>();
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            responseMap.put("response", 1);
        } else {
            responseMap.put("error", doesNotExistByIdError(id,"Заказчика"));
        }
        return responseMap;
    }

    private Customer fromParams(String lastName, String firstName, String surname,
                                String phone, String email, String address){
        Customer customer = new Customer();
        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        customer.setSurname(surname);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setAddress(address);
        return customer;
    }



    private HashMap<String, Object> alreadyExistError(String lastName, String firstName,
                                                      String surname, String email){
        return ResponseMapUtil.wrapInError(
                String.format(Locale.getDefault(),
                        "Заказчик с ФИО: %s %s %s и почтой %s уже существует",
                        lastName, firstName, surname, email), 502);
    }

}
