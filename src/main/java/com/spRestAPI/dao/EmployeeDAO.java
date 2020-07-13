package com.spRestAPI.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spRestAPI.model.Employee;
import com.spRestAPI.repository.EmployeeRepository;

@Service
public class EmployeeDAO {
	@Autowired
	EmployeeRepository employeRepository;
	
//	to save an employee
	public Employee save(Employee emp) {
		return employeRepository.save(emp);
		
	}
	
//	search all employee
	public List<Employee> findAll(){
		return employeRepository.findAll();
	}
	
//	get an employee 
//	
	public Employee findOne(Long empId) {
		return employeRepository.findById(empId).get();
	}
//	
//	delete employee
	public void delete(Employee emp) {
		employeRepository.delete(emp);
	}


}
