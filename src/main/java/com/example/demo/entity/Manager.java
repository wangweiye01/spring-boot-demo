package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "T_MANAGER")
@Entity
@Data
public class Manager {
    @Column(name = "ID")
    @GeneratedValue
    @Id
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(mappedBy = "manager", optional = true)
    private Department department;
}
