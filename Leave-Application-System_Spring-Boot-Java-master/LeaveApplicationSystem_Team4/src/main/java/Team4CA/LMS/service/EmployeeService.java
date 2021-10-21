package Team4CA.LMS.service;

import java.util.ArrayList;

import Team4CA.LMS.domain.Employee;


public interface EmployeeService {
	public ArrayList<Employee> findAll();
	public void saveEmployee(Employee emp);
	public void deleteEmployee(Integer id);
	public ArrayList<String> findAllEmployeeNames(String name);
	public Employee findEmployeeById(Integer id);
}