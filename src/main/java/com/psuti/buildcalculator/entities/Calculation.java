package com.psuti.buildcalculator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "calculations")
public class Calculation {

    @Id
    @SequenceGenerator(name="calculations_seq", sequenceName="calculations_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calculations_seq")
    @Column(nullable = false, name = "id")
    private Integer id;

    @JsonIgnore
    @Column(nullable = false, name = "manager")
    private Integer managerId;

    @JsonIgnore
    @Column(nullable = false, name = "customer")
    private Integer customerId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "date")
    private Long date;

    @ManyToOne
    @JoinColumn(name = "state")
    private CalculationState state;

}
