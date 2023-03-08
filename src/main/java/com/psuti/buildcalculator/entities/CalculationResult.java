package com.psuti.buildcalculator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "calculation_results")
public class CalculationResult {

    @Id
    @Column(nullable = false, name = "calculation_id")
    private Integer calculation_id;

}
