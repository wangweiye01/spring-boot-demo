package com.example.demo;

import com.example.demo.entity.Company;
import com.example.demo.entity.Department;
import com.example.demo.entity.Manager;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.ManagerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "测试controller", tags = {"测试JPA接口"})
public class DataController {
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @ApiOperation(value = "保存部门信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
            @ApiImplicitParam(name = "managerName", value = "经理姓名", dataType = "int", required = true, paramType = "form"),
            @ApiImplicitParam(name = "companyName", value = "公司名称", dataType = "string", required = true, paramType = "form")
    })
    @RequestMapping(value = "/department/ready", method = RequestMethod.POST)
    public ResponseEntity<?> departmentReady(@RequestParam String managerName, @RequestParam String companyName) {
        Manager manager = new Manager();
        manager.setName(managerName);

        Department department = new Department();
        department.setName("department y");

        Company company = new Company();
        company.setName(companyName);


        manager.setDepartment(department);
        department.setManager(manager);
        department.setCompany(company);
        company.getDepartmentSet().add(department);


        return ResponseEntity.ok(companyRepository.save(company));
    }

    @ApiOperation(value = "查询所有部门", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = true, dataType = "string"),
    })
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public ResponseEntity<?> departments() {
        List<Department> departments = departmentRepository.findAll();

        departments.forEach(x -> System.out.println(x.getName()));

        return ResponseEntity.ok(departments);
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
