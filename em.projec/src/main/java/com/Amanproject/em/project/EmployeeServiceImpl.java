package com.Amanproject.em.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Amanproject.em.project.Entity.EmployeeEntity;

@Service

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //List<Employee> employees = new ArrayList<>();
    


    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        //employees.add(employee);
        return "Saved Successfully";
    }


    @Override
    public Employee readEmployee(Long id) {
    EmployeeEntity entity = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    Employee employee = new Employee();
    BeanUtils.copyProperties(entity, employee);
    return employee;
}

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        for(EmployeeEntity employeeEntity : employeesList)
        {
            Employee emp = new Employee();

            emp.setId(employeeEntity.getId());
            emp.setName(employeeEntity.getName());
            emp.setEmail(employeeEntity.getEmail());
            emp.setPhone(employeeEntity.getPhone());

            employees.add(emp);
        }
        
        return employees;
    }

   /*  @Override
    
    public boolean deleteEmployee(long id) {
        //return emp.removeIf(emp -> emp.getId().equals(id));

        return true;
    }*/

    /*@Override
    public boolean deleteEmployee(long id) {
        try {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (org.springframework.orm.ObjectOptimisticLockingFailureException e) {
            // Row was already deleted by a concurrent/duplicate request — treat as "not found"
            return false;
        }
    }*/

   @Override
    public boolean deleteEmployee(long id) {
    if (!employeeRepository.existsById(id)) {
        return false;
    }
    employeeRepository.deleteById(id);
    return true;
}

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity existing = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    existing.setEmail(employee.getEmail());
    existing.setName(employee.getName());
    existing.setPhone(employee.getPhone());
    employeeRepository.save(existing);
    return "Updated Successfully";
}
    

    
    
}
