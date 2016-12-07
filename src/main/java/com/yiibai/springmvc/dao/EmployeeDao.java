package com.yiibai.springmvc.dao;

import java.util.List;

import com.yiibai.springmvc.entity.Employee;

/**
 * @author Administrator
 *	Ô±¹¤dao
 */
public interface EmployeeDao {
	
	Employee findById(int id);

	void saveEmployee(Employee employee);

	void deleteEmployeeBySsn(String ssn);

	List<Employee> findAllEmployees();

	Employee findEmployeeBySsn(String ssn);
}
