package com.psuti.buildcalculator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_posts")
public class UserPost {

    @Id
    @SequenceGenerator(name="user_posts_seq", sequenceName="user_posts_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_posts_seq")
    //@JsonIgnore
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;

}
