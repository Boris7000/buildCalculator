package com.psuti.buildcalculator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @SequenceGenerator(name="users_seq", sequenceName="users_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
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

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "state")
    private UserState state;

    @ManyToOne
    @JoinColumn(name = "post")
    private UserPost post;

    @JsonIgnore
    private boolean enabled = true;

}
