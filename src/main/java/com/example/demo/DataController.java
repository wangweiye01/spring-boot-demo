package com.example.demo;

import com.example.demo.entity.Company;
import com.example.demo.entity.Department;
import com.example.demo.entity.Manager;
import com.example.demo.repository.CompanyRepository;
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

    @Autowired
    private CompanyRepository companyRepository;

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

    @RequestMapping("/saveCompany")
    public void saveCompany() {
        Manager manager = new Manager();
        manager.setName("wangjingli");

        Department department = new Department();
        department.setName("kaifabu");

        department.setManager(manager);
        manager.setDepartment(department);

        Company company = new Company();
        company.setName("heli");
        company.getDepartmentSet().add(department);

        department.setCompany(company);

        companyRepository.save(company);

        System.out.println("OK");
    }

    @RequestMapping("/deleteCompany")
    public void deleteCompany() {

        companyRepository.delete(1);

        System.out.println("OK");
    }
}
