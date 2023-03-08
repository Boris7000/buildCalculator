package com.psuti.buildcalculator.dao;

import com.psuti.buildcalculator.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByLastNameAndFirstNameAndSurname(String lastName, String firstName, String surname);
    List<Customer> findByLastNameAndFirstName(String lastName, String firstName);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByPhone(String phone);
    List<Customer> findByEmail(String email);
    boolean existsByLastNameAndFirstNameAndSurnameAndEmail(String lastName, String firstName, String surname, String email);
}
