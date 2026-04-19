package com.ems.ui;

import com.ems.dao.EmployeeDAO;
import com.ems.model.Employee;
import javax.swing.*;
import java.awt.*;

public class EmployeeFormPanel extends JPanel {

    private JTextField nameField, emailField, deptField, salaryField;
    private JLabel titleLabel;
    private JButton saveBtn;
    private int currentEditingId = -1;
    private EmployeeDAO dao = new EmployeeDAO();
    private MainFrame mainFrame;

    public EmployeeFormPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        titleLabel = new JLabel("Add New Employee");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++; gbc.gridx = 0; add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; nameField = new JTextField(20); add(nameField, gbc);

        gbc.gridy++; gbc.gridx = 0; add(new JLabel("Email Address:"), gbc);
        gbc.gridx = 1; emailField = new JTextField(20); add(emailField, gbc);

        gbc.gridy++; gbc.gridx = 0; add(new JLabel("Department:"), gbc);
        gbc.gridx = 1; deptField = new JTextField(20); add(deptField, gbc);

        gbc.gridy++; gbc.gridx = 0; add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1; salaryField = new JTextField(20); add(salaryField, gbc);

        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        saveBtn = new JButton("Save Employee");
        saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveBtn.setBackground(new Color(50, 150, 50));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.addActionListener(e -> saveEmployee());
        add(saveBtn, gbc);

        gbc.gridy++;
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> mainFrame.showPanel("EmployeeList"));
        add(cancelBtn, gbc);
    }

    public void clearForm() {
        currentEditingId = -1;
        titleLabel.setText("Add New Employee");
        nameField.setText("");
        emailField.setText("");
        deptField.setText("");
        salaryField.setText("");
        saveBtn.setText("Save Employee");
    }

    public void loadEmployee(Employee emp) {
        currentEditingId = emp.getId();
        titleLabel.setText("Edit Employee #" + currentEditingId);
        nameField.setText(emp.getName());
        emailField.setText(emp.getEmail());
        deptField.setText(emp.getDepartment());
        salaryField.setText(String.valueOf(emp.getSalary()));
        saveBtn.setText("Update Employee");
    }

    private void saveEmployee() {
        String name = nameField.getText();
        String email = emailField.getText();
        String dept = deptField.getText();
        String salaryStr = salaryField.getText();

        if (name.isEmpty() || email.isEmpty() || dept.isEmpty() || salaryStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        try {
            double salary = Double.parseDouble(salaryStr);
            Employee emp = new Employee(name, email, dept, salary);
            
            if (currentEditingId == -1) {
                dao.addEmployee(emp);
                JOptionPane.showMessageDialog(this, "Employee added successfully!");
            } else {
                emp.setId(currentEditingId);
                dao.updateEmployee(emp);
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            }
            
            mainFrame.getEmployeeListPanel().refreshTable();
            mainFrame.showPanel("EmployeeList");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid salary format!");
        }
    }
}
