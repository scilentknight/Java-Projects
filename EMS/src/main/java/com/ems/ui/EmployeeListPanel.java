package com.ems.ui;

import com.ems.dao.EmployeeDAO;
import com.ems.model.Employee;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeListPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private EmployeeDAO dao = new EmployeeDAO();
    private MainFrame mainFrame;

    public EmployeeListPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Employee Directory");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Table setup
        String[] columns = {"ID", "Name", "Email", "Department", "Salary", "Actions"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(35);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editBtn = new JButton("Edit Selected");
        JButton deleteBtn = new JButton("Delete Selected");
        JButton refreshBtn = new JButton("Refresh");

        editBtn.addActionListener(e -> editEmployee());
        deleteBtn.addActionListener(e -> deleteEmployee());
        refreshBtn.addActionListener(e -> refreshTable());

        btnPanel.add(refreshBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        add(btnPanel, BorderLayout.SOUTH);

        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Employee> list = dao.getAllEmployees();
        for (Employee e : list) {
            tableModel.addRow(new Object[]{
                    e.getId(),
                    e.getName(),
                    e.getEmail(),
                    e.getDepartment(),
                    e.getSalary(),
                    "Edit/Delete"
            });
        }
    }

    private void editEmployee() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int id = (int) tableModel.getValueAt(row, 0);
            Employee emp = dao.getEmployeeById(id);
            mainFrame.getEmployeeFormPanel().loadEmployee(emp);
            mainFrame.showPanel("EmployeeForm");
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit.");
        }
    }

    private void deleteEmployee() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int id = (int) tableModel.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao.deleteEmployee(id);
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }
}
