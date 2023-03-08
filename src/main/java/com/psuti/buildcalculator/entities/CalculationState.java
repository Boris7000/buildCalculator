package com.psuti.buildcalculator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "calculation_states")
public class CalculationState {

    @Id
    @SequenceGenerator(name="calculation_states_seq", sequenceName="calculation_states_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calculation_states_seq")
    //@JsonIgnore
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;

}
