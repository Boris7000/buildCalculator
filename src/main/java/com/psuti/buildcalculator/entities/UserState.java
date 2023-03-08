package com.psuti.buildcalculator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_states")
public class UserState {

    @Id
    @SequenceGenerator(name="user_states_seq", sequenceName="user_states_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_states_seq")
    //@JsonIgnore
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;

}
