package com.ems.ui;

import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {

    private MainFrame mainFrame;

    public Sidebar(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setPreferredSize(new Dimension(220, 0));
        setBackground(new Color(30, 30, 40));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(30));
        
        JLabel title = new JLabel("EMS PANEL");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        add(Box.createVerticalStrut(40));

        addMenuButton("Dashboard", "Dashboard");
        addMenuButton("Employees", "EmployeeList");
        addMenuButton("Add Employee", "EmployeeForm");

        add(Box.createVerticalGlue());
        
        JButton logoutBtn = new JButton("Exit System");
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setBackground(new Color(180, 50, 50));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.addActionListener(e -> System.exit(0));
        add(logoutBtn);
        add(Box.createVerticalStrut(20));
    }

    private void addMenuButton(String label, String panelName) {
        JButton btn = new JButton(label);
        btn.setMaximumSize(new Dimension(180, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.addActionListener(e -> {
            if (panelName.equals("EmployeeForm")) {
                mainFrame.getEmployeeFormPanel().clearForm();
            }
            if (panelName.equals("EmployeeList")) {
                mainFrame.getEmployeeListPanel().refreshTable();
            }
            mainFrame.showPanel(panelName);
        });
        add(btn);
        add(Box.createVerticalStrut(15));
    }
}
