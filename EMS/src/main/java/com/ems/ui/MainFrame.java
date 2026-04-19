package com.ems.ui;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private Sidebar sidebar;

    public MainFrame() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        // Set Layout
        setLayout(new BorderLayout());

        // Card Layout for main content
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Sidebar
        sidebar = new Sidebar(this);
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Register Panels
        contentPanel.add(new DashboardPanel(), "Dashboard");
        contentPanel.add(new EmployeeListPanel(this), "EmployeeList");
        contentPanel.add(new EmployeeFormPanel(this), "EmployeeForm");

        // Initial view
        showPanel("Dashboard");
    }

    public void showPanel(String name) {
        cardLayout.show(contentPanel, name);
    }

    public EmployeeFormPanel getEmployeeFormPanel() {
        for (Component comp : contentPanel.getComponents()) {
            if (comp instanceof EmployeeFormPanel) {
                return (EmployeeFormPanel) comp;
            }
        }
        return null;
    }

    public EmployeeListPanel getEmployeeListPanel() {
        for (Component comp : contentPanel.getComponents()) {
            if (comp instanceof EmployeeListPanel) {
                return (EmployeeListPanel) comp;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            // Optional: Customize some colors
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            UIManager.put("TextComponent.arc", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
