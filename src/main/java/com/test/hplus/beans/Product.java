package com.test.hplus.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Product {

    @Id
    private int id;
    private String name;
    private String imagePath;
}
