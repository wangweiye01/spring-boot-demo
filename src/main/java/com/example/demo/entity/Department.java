package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "T_DEPARTMENT")
@Entity
@Data
public class Department {
    @Column(name = "ID")
    @GeneratedValue
    @Id
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @JoinColumn(name = "MANAGER_ID")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Manager manager;
}
