package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.validation.CustomValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeService {
@Autowired
private  EmployeeRepository employeeRepository ;
@Autowired
    private CustomValidation customValidation;

//Select
public List<Employee> getAllEmployee(){
    return employeeRepository.findAll();
}

//insert
    public Employee saveEmployeeDetails(@RequestBody Employee employee){
        if (!customValidation.checNamelength(employee.getFirstName() ,  employee.getLastName() , employee.getEmailId())){
            return null;
        }
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(@PathVariable long id) {
        return employeeRepository.findById(id).get();
    }

    //update
    public  Employee updateEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //Delete
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
