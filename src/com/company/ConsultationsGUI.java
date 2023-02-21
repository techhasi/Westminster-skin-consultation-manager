package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConsultationsGUI extends JFrame {
    public ConsultationsGUI() {
        JPanel panelConsult = new JPanel();
        panelConsult.setBackground(Color.orange);
        

        panelConsult.setLayout(new BorderLayout());

        JLabel consultationLabel = new JLabel();
        consultationLabel.setFont(new Font("Nexa Bold", Font.BOLD, 40));
        consultationLabel.setText("Consultation Details");
        consultationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        consultationLabel.setBorder(new EmptyBorder(new Insets(50,0,50,0)));
        panelConsult.add(consultationLabel, BorderLayout.NORTH );

        ImageIcon backgroundImage = new ImageIcon("Resources/bg.png");
        JLabel backgroundImgLabel = new JLabel(backgroundImage);
        backgroundImgLabel.setSize(400,300);
        backgroundImgLabel.setLocation(20,200);
        panelConsult.add(backgroundImgLabel);

        JButton homeButton = new JButton("Back");
        homeButton.setSize(100,30);
        homeButton.setFocusPainted(false);
        homeButton.setLocation(20,700);
        homeButton.addActionListener(e -> {
            dispose();
            new WelcomeForm();
        });
        panelConsult.add(homeButton);

        ConsultationTableModel consultTable = new ConsultationTableModel();
        JTable table2 = new JTable();
        table2.setModel(consultTable);

        JScrollPane scrollPaneTable2 = new JScrollPane(table2);
        table2.setGridColor(Color.BLACK);
        table2.setBounds(10,10,700,600);
        table2.setModel(consultTable);
        table2.setAutoCreateRowSorter(true);

        panelConsult.add(scrollPaneTable2, BorderLayout.CENTER);

        add(panelConsult);
    }

    public static void main()  {
        ConsultationsGUI consultation = new ConsultationsGUI();
        consultation.setTitle("Consultations");
        consultation.setSize(800, 800);
        consultation.setVisible(true);
        consultation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
