package com.tothenew.sharda.Dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tothenew.sharda.Model.Employee;

public interface EmployeeRepositoryForPagingAndSorting extends PagingAndSortingRepository<Employee, Integer> {
}