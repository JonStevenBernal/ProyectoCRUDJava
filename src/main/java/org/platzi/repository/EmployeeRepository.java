package org.platzi.repository;

import org.platzi.model.Employee;
import org.platzi.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try(Statement myStamt = getConnection().createStatement(); ResultSet myRes = myStamt.executeQuery("SELECT * FROM Employees")) {
            while (myRes.next()) {
                employees.add(createEmployee(myRes));
            }
        }
        return employees;
    }

    @Override
    public Employee getById(int id) throws SQLException {
        Employee employee = null;

        try(PreparedStatement myStamt = getConnection().prepareStatement("SELECT * FROM employees WHERE  id = ?")) {
            myStamt.setInt(1, id);

            try(ResultSet myRes = myStamt.executeQuery()) {
                if (myRes.next()) {
                    employee = createEmployee(myRes);
                }
            }
        }
        return employee;
    }

    @Override
    public void update(int id, Employee employee) throws SQLException {
        String sql = "UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ? WHERE id = ?";
        try(PreparedStatement myStamt = getConnection().prepareStatement(sql)) {
            myStamt.setString(1, employee.getFirst_name() != null ? employee.getFirst_name() : "");
            myStamt.setString(2, employee.getPa_surname() != null ? employee.getPa_surname() : "");
            myStamt.setString(3, employee.getMa_surname() != null ? employee.getMa_surname() : "");
            myStamt.setString(4, employee.getEmail() != null ? employee.getEmail() : "");
            myStamt.setFloat(5, employee.getSalary() != null ? employee.getSalary() : 0.0f);

            // Asignar el ID al último parámetro
            myStamt.setInt(6, id);

            // Ejecutar la actualización
            myStamt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement myStamt = getConnection().prepareStatement(sql)) {

            myStamt.setString(1, employee.getFirst_name());
            myStamt.setString(2, employee.getPa_surname());
            myStamt.setString(3, employee.getMa_surname());
            myStamt.setString(4, employee.getEmail());
            myStamt.setFloat(5, employee.getSalary());
            myStamt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Employee employee) {

    }

    private Employee createEmployee(ResultSet myRes) throws SQLException {
        Employee employee = new Employee();
        employee.setId(myRes.getInt("id"));
        employee.setFirst_name(myRes.getString("first_name"));
        employee.setPa_surname(myRes.getString("pa_surname"));
        employee.setMa_surname(myRes.getString("ma_surname"));
        employee.setEmail(myRes.getString("email"));
        employee.setSalary(myRes.getFloat("salary"));
        return employee;
    }
}
