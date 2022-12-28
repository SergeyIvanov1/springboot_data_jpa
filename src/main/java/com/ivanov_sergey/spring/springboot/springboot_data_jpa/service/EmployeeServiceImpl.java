package com.ivanov_sergey.spring.springboot.springboot_data_jpa.service;

import com.ivanov_sergey.spring.springboot.springboot_data_jpa.dao.EmployeeRepository;
import com.ivanov_sergey.spring.springboot.springboot_data_jpa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
//    @Transactional //springboot do this itself
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
//    @Transactional
    public Employee getEmployee(int id) {
        Employee employee = null;
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()){
            employee = employeeOptional.get();
        }
        return employee;
    }

    @Override
//    @Transactional
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
//    @Transactional
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllByName(String name) {
        return employeeRepository.findAllByName(name);
    }
}
