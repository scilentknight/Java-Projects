package com.ems.app;

import com.ems.dao.EmployeeDAO;
import com.ems.model.Employee;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== EMS MENU =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Department: ");
                    String dept = sc.nextLine();

                    System.out.print("Salary: ");
                    double salary = sc.nextDouble();

                    dao.addEmployee(new Employee(name, email, dept, salary));
                    break;

                case 2:
                    List<Employee> list = dao.getAllEmployees();
                    for (Employee e : list) {
                        System.out.println(
                                e.getId() + " | " +
                                        e.getName() + " | " +
                                        e.getEmail() + " | " +
                                        e.getDepartment() + " | " +
                                        e.getSalary()
                        );
                    }
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Employee emp = dao.getEmployeeById(id);

                    if (emp == null) {
                        System.out.println("❌ Employee ID does not exist!");
                        break;
                    }

                    System.out.println("Current Name: " + emp.getName());
                    System.out.print("New Name (press Enter to keep): ");
                    String n = sc.nextLine();
                    if (!n.isEmpty()) emp.setName(n);

                    System.out.println("Current Email: " + emp.getEmail());
                    System.out.print("New Email (press Enter to keep): ");
                    String em = sc.nextLine();
                    if (!em.isEmpty()) emp.setEmail(em);

                    System.out.println("Current Department: " + emp.getDepartment());
                    System.out.print("New Department (press Enter to keep): ");
                    String d = sc.nextLine();
                    if (!d.isEmpty()) emp.setDepartment(d);

                    System.out.println("Current Salary: " + emp.getSalary());
                    System.out.print("New Salary (press Enter to keep): ");
                    String s = sc.nextLine();
                    if (!s.isEmpty()) emp.setSalary(Double.parseDouble(s));

                    dao.updateEmployee(emp);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int delId = sc.nextInt();
                    dao.deleteEmployee(delId);
                    break;

                case 5:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}