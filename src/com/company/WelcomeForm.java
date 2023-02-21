package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class WelcomeForm extends JFrame {

    public WelcomeForm() {
        JPanel panel = new JPanel();
        JButton bookAppointmentButton = new JButton();
        JButton doctorListButton = new JButton();
        JButton consultationButton = new JButton();
        JLabel picLabel = new JLabel();
        JLabel welcomeLabel = new JLabel();

        panel.setBorder(new EmptyBorder(new Insets(30,0,50,0)));

        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
        welcomeLabel.setText("Welcome to Westminister Skin Consultation Center");
        panel.add(welcomeLabel);

        picLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        picLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("Resources/doctor3.jpg"))));
        picLabel.setSize(800, 400);
        panel.add(picLabel);

        bookAppointmentButton.setHorizontalTextPosition(SwingConstants.CENTER);
        bookAppointmentButton.setText("Book an appointment");
        bookAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookDoctor.main();
            }
        });
        panel.add(bookAppointmentButton);

        doctorListButton.setHorizontalTextPosition(SwingConstants.CENTER);
        doctorListButton.setText("Doctor List");
        doctorListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoctorListGUI.main();
            }
        });
        panel.add(doctorListButton);

        consultationButton.setHorizontalTextPosition(SwingConstants.CENTER);
        consultationButton.setText("Consultations");
        consultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultationsGUI.main();
            }
        });
        panel.add(consultationButton);

        picLabel.getAccessibleContext().setAccessibleName("picLabel");

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(welcomeLabel)
                        .addComponent(picLabel)
                        .addComponent(bookAppointmentButton, 200, 200, 200)
                        .addComponent(doctorListButton, 200, 200, 200)
                        .addComponent(consultationButton, 200, 200, 200)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(welcomeLabel)
                ).addGap(50,50,50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(picLabel)
                ).addGap(170,170,170)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bookAppointmentButton)
                ).addGap(20,20,20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(doctorListButton)
                ).addGap(20,20,20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(consultationButton)
                )
        );

        add(panel);

        pack();

    }


    public static void main(String[] args)  {
        WelcomeForm welcome = new WelcomeForm();
        welcome.setTitle("Welcome");
        welcome.setSize(800, 800);
        welcome.setVisible(true);
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
