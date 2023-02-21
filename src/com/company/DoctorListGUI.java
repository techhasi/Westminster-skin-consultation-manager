package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DoctorListGUI extends JFrame {
    public DoctorListGUI() {

    JPanel panelDoctorList = new JPanel(new BorderLayout());
    panelDoctorList.setBackground(Color.GREEN);

    JLabel doctorLabel = new JLabel();
    doctorLabel.setFont(new Font("Nexa Bold", Font.BOLD, 40));
    doctorLabel.setText("Doctor Details");
    doctorLabel.setHorizontalAlignment(SwingConstants.CENTER);
    doctorLabel.setBorder(new EmptyBorder(new Insets(50,0,50,0)));
    panelDoctorList.add(doctorLabel, BorderLayout.NORTH );

        JButton homeButton = new JButton("Back");
        homeButton.setSize(100,30);
        homeButton.setFocusPainted(false);
        homeButton.setLocation(20,700);
        homeButton.addActionListener(e -> {
            dispose();
            new WelcomeForm();
        });
        panelDoctorList.add(homeButton);

    DoctorTableModel docTable = new DoctorTableModel();
    JTable table = new JTable(docTable);

    JScrollPane scrollPaneTable = new JScrollPane(table);
        table.setGridColor(Color.BLACK);
        table.setBounds(10,10,700,600);
        table.setModel(docTable);
        table.setAutoCreateRowSorter(true);

        panelDoctorList.add(scrollPaneTable, BorderLayout.CENTER);

        JButton sortButton = new JButton("Sort");
        sortButton.setSize(10,30);
        sortButton.setFocusPainted(false);
        sortButton.setLocation(150,700);
        sortButton.addActionListener(e -> table.getRowSorter().toggleSortOrder(2));
        panelDoctorList.add(sortButton, BorderLayout.SOUTH);

        add(panelDoctorList);
        }

        public static void main()  {
            DoctorListGUI doctorListGUI = new DoctorListGUI();
            doctorListGUI.setTitle("Consultations");
            doctorListGUI.setSize(800, 800);
            doctorListGUI.setVisible(true);
            doctorListGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

}
