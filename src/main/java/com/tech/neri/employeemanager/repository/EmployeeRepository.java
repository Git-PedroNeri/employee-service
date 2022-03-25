package com.tech.neri.employeemanager.repository;

import com.tech.neri.employeemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeById(Long id);

    Optional<Employee> getByEmail(String email);

    void deleteEmployeeById(Long id);

    List<Employee> findByOrderByNameAsc();

}
