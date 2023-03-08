package com.psuti.buildcalculator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "token")
@Table(name = "tokens")
public class Token implements Serializable {

    @Id
    @Column(nullable = false, name = "user_id")
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String value;

    @JsonIgnore
    private boolean killed;

}
