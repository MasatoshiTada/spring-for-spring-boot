package com.example.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    public Integer id;

    public String firstName;

    public String lastName;

    public LocalDate joinedDate;
}
