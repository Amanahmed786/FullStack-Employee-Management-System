package com.Amanproject.em.project;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController

@CrossOrigin(origins = "*")

public class EmpController {

  //List<Employee> employees = new ArrayList<>();

  
  //EmployeeService employeeService = new  EmployeeServiceImpl() ;

   //Dependcy Injection


   //@Autowired
  
   //EmployeeService employeeService;

   // Better practice to use contructor for Dependency Injection

   private final EmployeeService employeeService;

    public EmpController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

   @GetMapping("employees")
   public List<Employee> getAllEmployees(){
 
      return employeeService.readEmployees();
   }


   @GetMapping("employees/{id}")
   public Employee getEmployeeById(@PathVariable Long id){
 
      return employeeService.readEmployee(id);
   }



   @PostMapping("employees")
   public String createEmployee(@Valid @RequestBody Employee employee) {
    return employeeService.createEmployee(employee);
}

   @DeleteMapping("employees/{id}")
   public String deleteEmmployee(@PathVariable Long id)
   {
      if(employeeService.deleteEmployee(id))
      {
         return "Delete Successfully";
      }
      return "Not found";
   }


   @PutMapping("employees/{id}")
   public String putMethodName(@PathVariable Long id, @Valid @RequestBody Employee employee) {
      return employeeService.updateEmployee(id, employee);
   }


}
    

