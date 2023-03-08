package com.psuti.buildcalculator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "measurement_units")
public class MeasurementUnit {

    @Id
    @SequenceGenerator(name="measurement_units_seq", sequenceName="measurement_units_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measurement_units_seq")
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;

}
