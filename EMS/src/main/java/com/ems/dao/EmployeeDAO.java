package com.ems.dao;

import com.ems.db.DBConnection;
import com.ems.model.Employee;

import java.sql.*;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

//import java.sql.Connection;
//import java.sql.PreparedStatement;

public class EmployeeDAO {

    public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (name, email, department, salary) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());
            ps.setDouble(4, emp.getSalary());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Employee added successfully!");
            } else {
                System.out.println("❌ Failed to add employee!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                );
                list.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // GET EMPLOYEE BY ID
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getDouble("salary")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE EMPLOYEE (IMPROVED)
    public void updateEmployee(Employee emp) {

        String sql = "UPDATE employees SET name=?, email=?, department=?, salary=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());
            ps.setDouble(4, emp.getSalary());
            ps.setInt(5, emp.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Employee updated successfully!");
            } else {
                System.out.println("❌ Employee ID not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {

        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Employee deleted successfully!");
            } else {
                System.out.println("❌ Employee with ID " + id + " not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}