package com.Amanproject.em.project;

import java.util.List;

public interface EmployeeService {
  String createEmployee(Employee employee);
  List<Employee> readEmployees();

  boolean deleteEmployee(long id);

  String updateEmployee(Long id ,  Employee employee);
  Employee readEmployee(Long id);
}
