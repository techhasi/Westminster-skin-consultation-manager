package com.company;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.security.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BookDoctor extends JFrame {
    private final JLabel consultCostIndicateLabel;
    private final JTextField consultTimeTextField;
    private final JTextField fNameTextField;
    private final JTextField idTextField;
    private final JTextField lNameTextField;
    private final JTextField mobileNoTextField;
    private final JTextArea notesTextArea;
    private final JTextField dobTextField;
    private final JTextField dateTextField;
    private final JTextField timeTextField;
    private final JComboBox<String> doctorList = new JComboBox<>();

    public BookDoctor() {
        JLabel patientLabel = new JLabel();
        JLabel fNameLabel = new JLabel();
        JLabel lNameLabel = new JLabel();
        fNameTextField = new JTextField(15);
        lNameTextField = new JTextField(15);
        dobTextField = new JTextField(15);
        JLabel dateTimeLabel = new JLabel();
        JLabel dobLabel = new JLabel();
        JLabel mobileNoLabel = new JLabel();
        mobileNoTextField = new JTextField(15);
        JLabel idLabel = new JLabel();
        idTextField = new JTextField(15);
        JLabel selectDoctorLabel = new JLabel();
        JLabel notesLabel = new JLabel();
        notesTextArea = new JTextArea(10, 10);
        JLabel consultTimeLabel = new JLabel();
        JButton bookButton = new JButton();
        JButton cancelButton = new JButton();
        JButton uploadImageButton = new JButton();
        JLabel consultCostLabel = new JLabel();
        consultTimeTextField = new JTextField(15);
        consultCostIndicateLabel = new JLabel();
        JLabel picLabel = new JLabel();
        JLabel backgroundImgLabel;
        JLabel dateLabel = new JLabel();
        JLabel timeLabel = new JLabel();
        dateTextField = new JTextField(10);
        timeTextField = new JTextField(10);

        JPanel panelMain = new JPanel();
        ImageIcon backgroundImage = new ImageIcon("Resources/bg.png");
        backgroundImgLabel = new JLabel(backgroundImage);
        panelMain.add(backgroundImgLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelMain.add(scrollPane);

        JPanel panel1 = new JPanel();

        patientLabel.setFont(new Font("Nexa Bold", Font.BOLD, 40));
        patientLabel.setText("Patient Form");
        patientLabel.setHorizontalAlignment(SwingConstants.CENTER);
        patientLabel.setBorder(new EmptyBorder(new Insets(20,0,0,0)));
        panel1.add(patientLabel);

        JPanel panel2 = new JPanel();
        panel2.setBorder(new EmptyBorder(new Insets(20,570,0,0)));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd          HH:mm");
        LocalDateTime now = LocalDateTime.now();
        dateTimeLabel.setText(dtf.format(now));
        panel2.add(dateTimeLabel);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout(10, 10));
        panel4.add(panel1, BorderLayout.CENTER);
        panel4.add(panel2,BorderLayout.SOUTH);

        JPanel panel3 = new JPanel();

        fNameLabel.setText("First Name");
        panel3.add(fNameLabel);

        panel3.add(fNameTextField);

        lNameLabel.setText("Last Name");
        panel3.add(lNameLabel);

        panel3.add(lNameTextField);

        dobLabel.setText("Date of Birth");
        panel3.add(dobLabel);

        dobTextField.setText("MM-dd-yyyy");
        panel3.add(dobTextField);

        mobileNoLabel.setText("Mobile No");
        panel3.add(mobileNoLabel);

        panel3.add(mobileNoTextField);

        idLabel.setText("ID number");
        panel3.add(idLabel);

        panel3.add(idTextField);

        selectDoctorLabel.setText("Select doctor");
        panel3.add(selectDoctorLabel);

        for (int i=0; i<Main.doctorLists.size(); i++) {
            doctorList.addItem("Dr. " + Main.doctorLists.get(i).getFirstName() + " " + Main.doctorLists.get(i).getLastName());
        }
        panel3.add(doctorList);

        consultTimeLabel.setText("Consulting hours");
        panel3.add(consultTimeLabel);



        consultTimeTextField.addMouseListener(new MouseListener() {             //To calculate the consultation price
            @Override
            public void mouseClicked(MouseEvent e) {
                String consultTimeText = consultTimeTextField.getText();
                int consulTime = Integer.parseInt(consultTimeText);
                int consultCost;
                if (consulTime > 1)
                    consultCost = 15 + 25 * (consulTime-1);
                else
                    consultCost = 15;

                String consultCostText = "£ " + consultCost;
                consultCostIndicateLabel.setText(consultCostText);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        consultTimeTextField.setText("1");
        panel3.add(consultTimeTextField);

        timeLabel.setText("Consult time");
        panel3.add(timeLabel);

        timeTextField.setText("HH:MM");
        panel3.add(timeTextField);

        dateLabel.setText("Consult date");
        panel3.add(dateLabel);

        dateTextField.setText("MM-dd-yyyy");
        panel3.add(dateTextField);

        uploadImageButton.setText("Upload Image");
        //To upload a photo
        uploadImageButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            try {
                ImageIcon icon=new ImageIcon(ImageIO.read(new File(file.getAbsolutePath())));
                picLabel.setIcon(icon);
                picLabel.setSize(80, 40);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        panel3.add(uploadImageButton);

        consultCostLabel.setText("Consultation Cost ");
        panel3.add(consultCostLabel);

        notesLabel.setText("Notes");
        panel3.add(notesLabel);


        panel3.add(picLabel);

        panel3.add(notesTextArea);

        bookButton.setText("Book now");
        bookButton.addActionListener(e -> {
            String fName = fNameTextField.getText();
            String lName = lNameTextField.getText();
            String dob = dobTextField.getText();

            String mobileNo = mobileNoTextField.getText();

            String id = idTextField.getText();
            String doctor = Objects.requireNonNull(doctorList.getSelectedItem()).toString();
            String notes = notesTextArea.getText();

            int consulTime = parseInt(consultTimeTextField.getText());
            String stringDate = dateTextField.getText();
            String stringTime = timeTextField.getText();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");

            Patient patientDetails = new Patient(fName, lName, dob, mobileNo, id);
            Main.patientLists.add(patientDetails);

            /*try {
                Date date = dateFormatter.parse(stringDate);
                Date time = timeFormatter.parse(stringTime);

                for (int i=0; i<Main.consultations.size();i++) {
                    if (Objects.equals(doctor, Main.consultations.get(i).getBookedDoctor())) {
                        Date date2 = new SimpleDateFormat("MM-dd-yyyy").parse(Main.consultations.get(i).getDate());

                        SimpleDateFormat timeFormatter2 = new SimpleDateFormat("HH:mm");
                        Date time2 = timeFormatter2.parse(Main.consultations.get(i).getTime());

                        if (date == date2 && time == time2) {
                            Random random = new Random();
                            int num = random.nextInt(doctorList.getItemCount());

                            Consultation consultationDetails = new Consultation(stringDate, stringTime, consultCostIndicateLabel.getText(), encrypt(notes), consulTime, doctorList.getItemAt(num));
                            Main.consultations.add(consultationDetails);
                            break;
                        } else {
                            Consultation consultationDetails = new Consultation(stringDate, stringTime, consultCostIndicateLabel.getText(), encrypt(notes), consulTime, doctor);
                            Main.consultations.add(consultationDetails);
                        }
                    }
                    else {
                        Consultation consultationDetails = new Consultation(stringDate, stringTime, consultCostIndicateLabel.getText(), encrypt(notes), consulTime, doctor);
                        Main.consultations.add(consultationDetails);
                    }
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }*/
            Consultation consultationDetails = new Consultation(stringDate, stringTime, consultCostIndicateLabel.getText(), encrypt(notes), consulTime, doctor);
            Main.consultations.add(consultationDetails);

            saveConsultationFile();
            savePatientFile();

            JOptionPane.showMessageDialog(rootPane, "Patient Name: " + fName + " " + lName +
                        "\nDate of birth: " + dob +
                        "\nMobile Number: " + mobileNo +
                        "\nID number: " + id +
                        "\nChanneled doctor: " + doctor +
                        "\nConsultation time:" + consultTimeTextField.getText() + " hour(s)" +
                        "\nConsultation Cost: " + consultCostIndicateLabel.getText() +
                        "\nNotes: " + notes + "\n\n Booking is completed");
            clearForm();
        });
        panel3.add(bookButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
            new WelcomeForm();
        });
        panel3.add(cancelButton);

        GroupLayout layout = new GroupLayout(panel3);
        panel3.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundImgLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fNameLabel)
                        .addComponent(dobLabel)
                        .addComponent(idLabel)
                        .addComponent(consultTimeLabel)
                        .addComponent(consultCostLabel)
                        .addComponent(timeLabel)
                ).addGap(30,30,30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fNameTextField)
                        .addComponent(dobTextField)
                        .addComponent(idTextField)
                        .addComponent(consultTimeTextField)
                        .addComponent(consultCostIndicateLabel)
                        .addComponent(timeTextField)
                ).addGap(30,30,30)

                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lNameLabel)
                        .addComponent(mobileNoLabel)
                        .addComponent(selectDoctorLabel)
                        .addComponent(dateLabel)
                ).addGap(30,30,30)

                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lNameTextField)
                        .addComponent(mobileNoTextField)
                        .addComponent(doctorList)
                        .addComponent(dateTextField)
                ).addGap(30,30,30)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(notesLabel)
                        ).addGap(30,30,30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(notesTextArea)
                                .addComponent(uploadImageButton)
                        ).addGap(30,30,30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(picLabel)
                        ).addGap(30,30,30)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(bookButton)
                        ).addGap(30,30,30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(cancelButton)
                        )
                )
        );

        layout.setVerticalGroup (layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backgroundImgLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fNameLabel)
                                .addComponent(fNameTextField)
                                .addComponent(lNameLabel)
                                .addComponent(lNameTextField)
                        ).addGap(30,30,30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(dobLabel)
                                .addComponent(dobTextField)
                                .addComponent(mobileNoLabel)
                                .addComponent(mobileNoTextField)
                        ).addGap(30,30,30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(idLabel)
                                .addComponent(idTextField)
                                .addComponent(selectDoctorLabel)
                                .addComponent(doctorList)
                        ).addGap(30,30,30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(consultTimeLabel)
                                .addComponent(consultTimeTextField)
                        ).addGap(30,30,30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(consultCostLabel)
                                .addComponent(consultCostIndicateLabel)
                        ).addGap(30,30,30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(timeLabel)
                                .addComponent(timeTextField)
                                .addComponent(dateLabel)
                                .addComponent(dateTextField)
                        ).addGap(30,30,30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(notesLabel)
                                .addComponent(notesTextArea)
                                .addComponent(picLabel)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(uploadImageButton)
                        ).addGap(30,30,30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(bookButton)
                                .addComponent(cancelButton)
                        )
                )
        );

        panelMain.setLayout(new BorderLayout(50, 50));
        panelMain.add(panel4, BorderLayout.NORTH);
        panelMain.add(panel3, BorderLayout.CENTER);

        add(panelMain);
        pack();
    }

    public void clearForm() {
        fNameTextField.setText("");
        lNameTextField.setText("");
        dobTextField.setText("YYYY/MM/DD");
        mobileNoTextField.setText("");
        idTextField.setText("");
        consultTimeTextField.setText("1");
        consultCostIndicateLabel.setText("");
        notesTextArea.setText("");
    }

    public byte[] encrypt (String notes) {
        String key = "1234567890abcdef";

        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            Objects.requireNonNull(cipher).init(Cipher.ENCRYPT_MODE, aesKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] encrypted = new byte[0];
        try {
            encrypted = Objects.requireNonNull(cipher).doFinal(notes.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return encrypted;
    }

    public void saveConsultationFile()   {
        try {
            FileOutputStream fileOut = new FileOutputStream("Consultation Information.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Main.consultations);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void openConsultationFile() {
        try {
            FileInputStream fileIn = new FileInputStream("Consultation Information.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Main.consultations = (ArrayList<Consultation>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void savePatientFile()   {
        try {
            FileOutputStream fileOut = new FileOutputStream("Patient Information.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Main.patientLists);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void openPatientFile() {
        try {
            FileInputStream fileIn = new FileInputStream("Patient Information.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Main.patientLists = (ArrayList<Patient>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        BookDoctor bookDoctor = new BookDoctor();
        bookDoctor.setTitle("Westminister Skin Consultation Center");
        bookDoctor.setSize(800, 800);
        bookDoctor.setVisible(true);
        bookDoctor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

}
