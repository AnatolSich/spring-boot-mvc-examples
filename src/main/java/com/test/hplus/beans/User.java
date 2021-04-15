package com.test.hplus.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class User {

    @Id
    private int id;
    private String username;
    private String password;
    private String gender;
    private String activity;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}
