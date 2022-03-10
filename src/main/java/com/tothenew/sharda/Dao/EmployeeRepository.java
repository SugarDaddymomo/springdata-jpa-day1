package com.tothenew.sharda.Dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.tothenew.sharda.Model.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	List<Employee> findByName(String name);
	List<Employee> findByNameStartsWith(String name);
	List<Employee> findByAgeBetween(Integer start, Integer end);
}