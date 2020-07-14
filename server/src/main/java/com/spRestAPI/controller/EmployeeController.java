package com.spRestAPI.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spRestAPI.dao.EmployeeDAO;
import com.spRestAPI.model.Employee;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/company")
public class EmployeeController {
	  

    
	@Autowired
	EmployeeDAO employeeDAO;
	
//	to save an employee
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}
//	get all employee
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeDAO.findAll();
	}  
//	get specific employee
	@GetMapping("/notes/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long empId){
		Employee emp = employeeDAO.findOne(empId);
		
		if(emp == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}
	
//	update a employee by Id
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmplyee(@PathVariable(value="id") Long empId, @Valid @RequestBody Employee empDetails){
		Employee emp = employeeDAO.findOne(empId);
		if(emp == null) {
			return ResponseEntity.notFound().build();
		}
		emp.setName(empDetails.getName());
		emp.setOffice(empDetails.getOffice());
		emp.setPosition(empDetails.getPosition());
		emp.setSalary(empDetails.getSalary());
//		emp.setDesignation(empDetails.getDesignation());
		Employee updateEmployee = employeeDAO.save(emp);
		return ResponseEntity.ok().body(updateEmployee);
	} 
	
//	delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empId){
		Employee emp = employeeDAO.findOne(empId);
		if(emp == null) {
			return ResponseEntity.notFound().build();
		}
		employeeDAO.delete(emp);
		return ResponseEntity.ok().build();
	}

}
