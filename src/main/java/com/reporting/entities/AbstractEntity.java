package com.reporting.entities;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "random_string_8")
    private String id;
}
