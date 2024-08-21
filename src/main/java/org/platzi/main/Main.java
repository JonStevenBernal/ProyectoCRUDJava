package org.platzi.main;

import org.platzi.model.Employee;
import org.platzi.repository.EmployeeRepository;
import org.platzi.repository.Repository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        try {

            Repository<Employee> repository = new EmployeeRepository();

            System.out.println("---- Mostrando todos los empleados ----");
            repository.findAll().forEach(System.out::println);
//            System.out.println(repository.getById(3));

            System.out.println("---- Insertando un nuevo empleado ----");

            Employee employee = new Employee();
            employee.setFirst_name("Javier");
            employee.setPa_surname("Gonzalez");
            employee.setMa_surname("Gonzalez");
            employee.setEmail("javier@example.com");
            employee.setSalary(1000f);
            repository.save(employee);

            System.out.println("---- Mostrando todos los empleados ----");
            repository.findAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("La conexion fallo");
        }

    }
}
