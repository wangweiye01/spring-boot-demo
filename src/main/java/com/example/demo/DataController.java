package com.example.demo;

import com.example.demo.entity.Department;
import com.example.demo.entity.Manager;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/department/ready")
    public void departmentReady() {
        Manager manager = new Manager();
        manager.setName("manger w");

        Department department = new Department();
        department.setName("department y");


        manager.setDepartment(department);
        department.setManager(manager);

        departmentRepository.save(department);
    }

    @RequestMapping("/departments")
    public void departments() {
        List<Department> departments = departmentRepository.findAll();

        departments.forEach(x -> System.out.println(x.getName()));
    }
}
