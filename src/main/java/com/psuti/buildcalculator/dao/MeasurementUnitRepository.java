package com.psuti.buildcalculator.dao;

import com.psuti.buildcalculator.entities.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, Integer> {
}
