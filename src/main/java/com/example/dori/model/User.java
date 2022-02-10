package com.example.dori.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String email;
    private String password;
    @OneToMany( fetch=FetchType.EAGER, targetEntity=User.class )
    private Set<User> frieds;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.frieds = new HashSet<>();
    }

    public User() {
    }
}
