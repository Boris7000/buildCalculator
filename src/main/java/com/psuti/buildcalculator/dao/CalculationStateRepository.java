package com.psuti.buildcalculator.dao;

import com.psuti.buildcalculator.entities.CalculationState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationStateRepository extends JpaRepository<CalculationState, Integer> {
}
