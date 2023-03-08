package com.psuti.buildcalculator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @SequenceGenerator(name="customers_seq", sequenceName="customers_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, length = 25, name = "first_name")
    private String firstName;

    @Column(nullable = false, length = 25, name = "last_name")
    private String lastName;

    @Column(nullable = false, length = 25, name = "surname")
    private String surname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

}
