package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "T_DEPARTMENT")
@Entity
@Getter
@Setter
public class Department {
    @Column(name = "ID")
    @GeneratedValue
    @Id
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @JoinColumn(name = "MANAGER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

}
