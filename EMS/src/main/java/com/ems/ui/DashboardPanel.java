package com.ems.ui;

import com.ems.dao.EmployeeDAO;
import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private EmployeeDAO dao = new EmployeeDAO();

    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(50, 50, 300, 50));

        JPanel header = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Dashboard Overview");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        statsPanel.setOpaque(false);
        
        int totalEmp = dao.getAllEmployees().size();
        
        statsPanel.add(createStatCard("Total Employees", String.valueOf(totalEmp), new Color(40, 150, 250)));
        statsPanel.add(createStatCard("Departments", "5", new Color(100, 200, 100)));
        statsPanel.add(createStatCard("Active Status", "98%", new Color(250, 150, 40)));

        add(statsPanel, BorderLayout.CENTER);
    }

    private JPanel createStatCard(String label, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(45, 45, 55));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel l = new JLabel(label);
        l.setForeground(Color.GRAY);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel v = new JLabel(value);
        v.setForeground(Color.WHITE);
        v.setFont(new Font("Segoe UI", Font.BOLD, 36));
        v.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(l);
        card.add(Box.createVerticalStrut(10));
        card.add(v);

        return card;
    }
}
