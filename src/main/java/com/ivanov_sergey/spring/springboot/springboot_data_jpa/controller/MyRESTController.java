package com.ivanov_sergey.spring.springboot.springboot_data_jpa.controller;

import com.ivanov_sergey.spring.springboot.springboot_data_jpa.entity.Employee;
import com.ivanov_sergey.spring.springboot.springboot_data_jpa.exception_handling.EmployeeIncorrectData;
import com.ivanov_sergey.spring.springboot.springboot_data_jpa.exception_handling.NoSuchEmployeeException;
import com.ivanov_sergey.spring.springboot.springboot_data_jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping( "/employees")
    public List<Employee> showAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}") // this {id} passing to the parameter of method
    public Employee getEmployee(@PathVariable int id){ // @PathVariable is using for getting value of request from address
        Employee employee = employeeService.getEmployee(id);

        if (employee == null){
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in the DataBase");
        }
        return employee; //passing is not object employee and JSON, because Spring and Jackson
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){ //we are passing JSON? but Spring convert parameter to class
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
        if (employee == null){
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in the DataBase");
        }


        employeeService.deleteEmployee(id);
        return "employee with id = " + id + " was deleted";
    }

    @GetMapping("/employees/name/{name}")
    public List<Employee> showAllEmployeesByName(@PathVariable String name){
        return  employeeService.findAllByName(name);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception){
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception){
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
