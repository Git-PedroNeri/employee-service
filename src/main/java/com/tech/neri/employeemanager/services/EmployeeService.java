package com.tech.neri.employeemanager.services;

import com.tech.neri.employeemanager.exeptions.UserNotFoundException;
import com.tech.neri.employeemanager.model.Employee;
import com.tech.neri.employeemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    /**
     * Method to add a employee
     *
     * @param employee
     * @return
     */
    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }


    /**
     * Busca todos Empoyees
     *
     * @return List<Employee>
     */
    public List<Employee> findAllEmployee() {
        return employeeRepository.findByOrderByNameAsc();
    }

    /**
     * @param employee
     * @return
     */
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.getByEmail(employee.getEmail());
        employee.setEmployeeCode(optionalEmployee.get().getEmployeeCode());
        employee.setId(optionalEmployee.get().getId());
        return employeeRepository.save(employee);
    }

    /**
     * @param id
     */
    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployeeById(id); //
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id).orElseThrow(() -> new UserNotFoundException("User by id" + id + "was not found"));
    }


}
