package com.example.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "HOGE")
    @SequenceGenerator(name = "HOGE",
            sequenceName = "hibernate_sequence",
            initialValue = 5,
            allocationSize = 1) // このアノテーションがないとBootではIDが自動生成されない
    public Integer id;

    public String firstName;

    public String lastName;

    public LocalDate joinedDate;
}
