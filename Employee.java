
/**
 * Program Purpose : Lab Test
 * Programmer : Adila
 * Date : 12 March 2024
 */

//import the respective package
import java.io.*;
import java.util.*;
public class Employee 
{
    //Declaration of the instance variables
    String name;
    double baseSalary;
    int yearsOfService;
    
    //constructor without param
    Employee(String name, double baseSalary, int yearsOfService) 
    {
        this.name = name;
        this.baseSalary = baseSalary;
        this.yearsOfService = yearsOfService;
    }//end of constructor
    
    //Accessor/retriever/getter
     public String getName()
    {
        return name;        
    }//end of accessor
    
     public double getbaseSalary()
    {
        return baseSalary;        
    }//end of accessor
    
     public int getyearsOfService()
    {
        return yearsOfService;        
    }//end of accessor
    
     public double calculateAnnualSalary()
    {
        return baseSalary + (baseSalary * 0.05 * yearsOfService);      
    }//end of accessor
}

class EmployeeSalaryCalculator
 {
    public static void main(String[] args)
    {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("employeeSalaries.txt"));
             PrintWriter writer = new PrintWriter(new FileWriter("employeeData.txt"))) 
             {

            Employee topPerformer = null;
            Employee leastYearsOfService = null;
            double maxSalary = Double.MIN_VALUE;

            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                if (parts.length == 3)
                {
                    Employee employee = new Employee(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]));
                    employees.add(employee);

                    double annualSalary = employee.calculateAnnualSalary();
                    writer.printf("Name: , Annual Salary: RM , Years of Service: ", employee.name, annualSalary, parts[2]);

                    if (annualSalary > maxSalary)
                    {
                        maxSalary = annualSalary;
                        topPerformer = employee;
                    }

                    if (leastYearsOfService == null || Integer.parseInt(parts[2]) < leastYearsOfService.yearsOfService) 
                    {
                        leastYearsOfService = employee;
                    }
                }
            }

            writer.printf("Top-performing employee: ", topPerformer.name);
            writer.printf("Employee with least years of service: ", leastYearsOfService.name);
        } 
        
        catch (IOException | NumberFormatException e) 
        {
            System.err.println("Error: " + e.getMessage());
        }
    }//end of main
}//end of class


