package com.psuti.buildcalculator.dao;

import com.psuti.buildcalculator.entities.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalculationRepository extends JpaRepository<Calculation, Integer> {
    Optional<Calculation> findByIdAndCustomerIdAndManagerId(Integer id, Integer customerId, Integer managerId);
    List<Calculation> findByCustomerIdAndManagerId(Integer customerId, Integer managerId);
}
