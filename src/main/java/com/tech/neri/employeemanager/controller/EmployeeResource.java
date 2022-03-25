package com.tech.neri.employeemanager.controller;

import com.tech.neri.employeemanager.model.Employee;
import com.tech.neri.employeemanager.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeResource {

    @Autowired
    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = employeeService.findAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity(employee, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/addInLot")
    public ResponseEntity<List<Employee>> addInLot(@RequestBody List<Employee> employess) {
        List<Employee> newsEmployees = new ArrayList<Employee>();
        for (Employee employee : employess) {
            Employee newEmployee = employeeService.addEmployee(employee);
            newsEmployees.add(newEmployee);
        }
        return new ResponseEntity<>(newsEmployees, HttpStatus.CREATED);
    }

    @GetMapping("/countEmployees")
    public ResponseEntity<Long> buscaQuantidadeDeEmpregados() {
        final long l = employeeService.buscaQuantidadeDeEmpregados();
        return new ResponseEntity<>(l, HttpStatus.OK);

    }

}
