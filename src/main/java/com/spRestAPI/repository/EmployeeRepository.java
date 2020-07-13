package com.spRestAPI.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spRestAPI.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Long>{

//	Employee findOne(Long empId);

}
