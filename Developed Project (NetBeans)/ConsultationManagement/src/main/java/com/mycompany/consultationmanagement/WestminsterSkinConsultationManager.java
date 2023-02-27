/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consultationmanagement;

/**
 *
 * @author MSI
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    static Scanner sc = new Scanner(System.in);

    //using arrayLists to store doctors and consultations
    private ArrayList<Doctor> doctorsList = new ArrayList<>();
    private ArrayList<Consultation> consultationsList = new ArrayList<>();

    @Override
    public void add() {
        if (doctorsList.size() < 10) {  //a maximum of 10 doctors can be allocated
            System.out.print("Enter doctor's name: ");
            String name = sc.next();
            System.out.print("Enter doctor's surname: ");
            String surname = sc.next();
            String dateOfBirth;
            System.out.print("Enter doctor's date of birth (YYYY/MM/DD): ");
            while (true) {
                dateOfBirth = sc.next();
                try {
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(dateOfBirth);
                    String[] dateSections = dateOfBirth.split("/");
                    int month = Integer.parseInt(dateSections[1]);
                    int day = Integer.parseInt(dateSections[2]);
                    if (month <= 0 || month > 12) {
                        System.out.print("Invalid Input for Month. Try Again: ");
                        continue;
                    }
                    if (day <= 0 || day > 31) {
                        System.out.print("Invalid Input for Day. Try Again: ");
                        continue;
                    }
                    break;
                } catch (Exception p) {
                    System.out.print("Input Invalid. Try again: ");
                }
            }
            System.out.print("Enter doctor's mobile number: ");
            long mobileNum;
            //input validation;
            while (true) {
                if (sc.hasNextLong()) {
                    mobileNum = sc.nextLong();
                    break;
                }
                else {
                    System.out.print("Input Invalid. Try again: ");
                    sc.next();
                }
            }
            System.out.print("Enter doctor's license number: ");      //assumed that the license number could contain alphabetical characters. Hence, data type is string
            String licenseNum = sc.next();
            sc.nextLine();
            System.out.print("Enter doctor's specialisation: ");
            String specialisation = sc.nextLine();
            doctorsList.add(new Doctor(name, surname, dateOfBirth, mobileNum, licenseNum, specialisation));
            System.out.println("Record Added Successfully!");
        } else
            System.out.println("Can't add more records. Limit reached.");
    }

    @Override
    public void delete() {
        System.out.print("Enter the Doctor's license number: ");
        String enteredLicenseNum = sc.next();
        for (int i = 0; i<doctorsList.size(); i++) {
            if (doctorsList.get(i).getLicenseNum().equalsIgnoreCase(enteredLicenseNum)) {
                System.out.println(doctorsList.get(i).fullDetails());
                doctorsList.remove(i);
                System.out.println("Record successfully deleted!\n" +
                        "Number of doctors remaining: " + doctorsList.size());
                return;
            }
        }
        System.out.println("No matching results found!");
    }

    @Override
    public void print() {
        Collections.sort(doctorsList);
        for (Doctor doctor : doctorsList) {
            System.out.print("\n" + doctor.fullDetails());
        }
    }

    @Override
    public void save() {
        try {
            // creating the file
            File doctorSaveData = new File("DoctorSaveData.txt");
            doctorSaveData.createNewFile();

            // overwriting the file
            FileWriter writeToFile = new FileWriter("DoctorSaveData.txt", false);

            Collections.sort(doctorsList);
            for (Doctor d : doctorsList) {
                writeToFile.write(d.getName() + "," +
                        d.getSurname() + "," +
                        d.getDateOfBirth() + "," +
                        d.getMobileNum() + "," +
                        d.getLicenseNum() + "," +
                        d.getSpecialisation() + "\n");
            }
            writeToFile.close();
            System.out.println("Save Successful!");
        } catch (IOException e) {
            System.out.print("An Error Occurred!");
        }
    }

    @Override
    public void load() {
        try {
            Scanner fileInput = new Scanner(new File("DoctorSaveData.txt"));
            fileInput.useDelimiter("[,\n]");
            int recordCount = 0;
            while(fileInput.hasNext()) {
                String name = fileInput.next();
                String surname =fileInput.next();
                String dateOfBirth = fileInput.next();
                long mobileNum = Long.parseLong(fileInput.next());
                String licenseNum = fileInput.next();
                String specialisation = fileInput.next();
                doctorsList.add(new Doctor(name, surname, dateOfBirth, mobileNum, licenseNum, specialisation));
                recordCount++;
            }
            if (recordCount == 0)
                System.out.println("SaveFile found. No Records found!");
            else
                System.out.println("SaveFile found. Added " + recordCount + " record(s)!");
        } catch (FileNotFoundException e) {
            System.out.println("SaveFile Not Found!");
        }
    }

    @Override
    public void graphicalUserInterface() {
        ApplicationFrame appOne = new ApplicationFrame();
        appOne.setSize(1200,700);
        appOne.setVisible(true);
        appOne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void menu() {

        System.out.println("""
        ---------------------------------------------------
        WELCOME TO WESTMINSTER SKIN CONSULTATION MANAGEMENT""");        //  Triple double-quotes is for a text block

        while (true) {

            System.out.print("""

            Menu:
            1. Add a new doctor
            2. Delete a doctor
            3. Print details of available doctors
            4. Save program data
            5. Open GUI
            6. Load program data
            7. Exit

            Enter a suitable menu option number:\s""");         // \s is for a single white-space character

            String option = sc.next();
            if (option.equals("1"))
                add();
            else if (option.equals("2"))
                delete();
            else if (option.equals("3"))
                print();
            else if (option.equals("4"))
                save();
            else if (option.equals("5"))
                graphicalUserInterface();
            else if (option.equals("6"))
                load();
            else if (option.equals("7"))
                return;
            else
                System.out.println("Invalid Option. Try Again");
        }
    }

    //innerclass for the frame, it uses the arraylist in the main WestminsterSkinConsultationManager class.
    public class ApplicationFrame extends JFrame implements ActionListener {

        //Instance Variables
        private JPanel mainPanel;
        private JPanel consultationPanel;
        private JPanel consultationSelectionPanel;
        private JTable doctorsTable;
        private JScrollPane doctorsTablePane;
        private JPanel consultationForm;
        private JLabel patientNameL;
        private JTextField patientName;
        private JLabel patientSurnameL;
        private JTextField patientSurname;
        private JLabel patientDateOfBirthL;
        private JTextField patientDateOfBirth;
        private JLabel patientMobileNumL;
        private JTextField patientMobileNum;
        private JLabel patientIdL;
        private JTextField patientId;
        private JLabel doctorSelectL;
        private JComboBox<Doctor> doctorSelect;
        private JLabel dateL;
        private JTextField date;
        private JLabel startTimeL;
        private JTextField startTime;
        private JLabel endTimeL;
        private JTextField endTime;
        private JLabel notesL;
        private JTextPane notesA;
        private JScrollPane notes;
        private JButton submitButton;
        private JTextArea logText;
        private JScrollPane log;
        private JLabel consultationSelectL;
        private JComboBox<Consultation> consultationSelect;
        private JButton consultationButton;
        private JTextPane consultationDetails;
        private JScrollPane consultationDetailsPane;

        public void clearForm() {
            patientName.setText("");
            patientSurname.setText("");
            patientDateOfBirth.setText("");
            patientMobileNum.setText("");
            patientId.setText("");         
            date.setText("");
            startTime.setText("");
            endTime.setText("");
            notesA.setText("");
            doctorSelect.setSelectedIndex(0);
        }

        public ApplicationFrame() {

            super("Doctor Consultation Management");
            mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(2,2));
            consultationPanel = new JPanel();
            consultationPanel.setLayout(new BorderLayout());
            consultationSelectionPanel = new JPanel();
            consultationSelectionPanel.setLayout(new FlowLayout());

            //Table of Doctors
            String[] columnNames = {"Surname", "First-Name", "Date of Birth", "Mobile Number", "License Number", "Specialization"};
            String[][] tableData = new String[doctorsList.size()][6];
            int rowNumber = 0;
            for (Doctor d : doctorsList) {
                tableData[rowNumber][0] = d.getSurname();
                tableData[rowNumber][1] = d.getName();
                tableData[rowNumber][2] = d.getDateOfBirth();
                tableData[rowNumber][3] = Long.toString(d.getMobileNum());
                tableData[rowNumber][4] = d.getLicenseNum();
                tableData[rowNumber][5] = d.getSpecialisation();
                rowNumber++;
            }
            doctorsTable = new JTable(tableData, columnNames) {
                //to make all cells uneditable, read only
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            doctorsTablePane = new JScrollPane(doctorsTable);
            doctorsTable.setAutoCreateRowSorter(true);  // to sort the list alphabetically based on the column selected by the user


            //Consultation form
            consultationForm = new JPanel();
            GridBagConstraints c = new GridBagConstraints();
            consultationForm.setLayout(new GridBagLayout());

            patientNameL = new JLabel("First Name");
            patientName = new JTextField();

            patientSurnameL = new JLabel("Surname");
            patientSurname = new JTextField();

            patientDateOfBirthL = new JLabel("Date of Birth (YYYY/MM/DD)");
            patientDateOfBirth = new JTextField();

            patientMobileNumL = new JLabel("Mobile Number");
            patientMobileNum = new JTextField();

            patientIdL = new JLabel("Patient ID");
            patientId = new JTextField();

            doctorSelectL = new JLabel("Select Doctor");
            Doctor[] doctorsArray = doctorsList.toArray(new Doctor[doctorsList.size()]);
            doctorSelect = new JComboBox(doctorsArray);

            dateL = new JLabel("Date (YYYY/MM/DD)");
            date = new JTextField();

            startTimeL = new JLabel("Start Time (HHMM)");
            startTime = new JTextField();

            endTimeL = new JLabel("End Time (HHMM)");
            endTime = new JTextField();

            notesL = new JLabel("Notes");
            notesA = new JTextPane();
            notes = new JScrollPane(notesA);

            submitButton = new JButton("Add Consultation");

            c.gridx = 0;
            c.gridy = 0;
            c.ipadx = 15;
            consultationForm.add(doctorSelectL, c);

            c.gridx = 1;
            c.ipadx = 120;
            consultationForm.add(doctorSelect, c);

            c.gridx = 0;
            c.gridy = 1;
            c.ipadx = 15;
            consultationForm.add(patientNameL, c);

            c.gridx = 1;
            c.ipadx = 300;
            consultationForm.add(patientName, c);

            c.gridx = 0;
            c.gridy = 2;
            c.ipadx = 15;
            consultationForm.add(patientSurnameL, c);

            c.gridx = 1;
            c.ipadx = 300;
            consultationForm.add(patientSurname, c);

            c.gridx = 0;
            c.gridy = 3;
            c.ipadx = 15;
            consultationForm.add(patientDateOfBirthL, c);

            c.gridx = 1;
            c.ipadx = 300;
            consultationForm.add(patientDateOfBirth, c);

            c.gridx = 0;
            c.gridy = 4;
            c.ipadx = 15;
            consultationForm.add(patientMobileNumL, c);

            c.gridx = 1;
            c.ipadx = 300;
            consultationForm.add(patientMobileNum, c);

            c.gridx = 0;
            c.gridy = 5;
            c.ipadx = 15;
            consultationForm.add(patientIdL, c);

            c.gridx = 1;
            c.ipadx = 300;
            consultationForm.add(patientId, c);

            c.gridx = 0;
            c.gridy = 6;
            c.ipadx = 15;
            consultationForm.add(dateL, c);

            c.gridx = 1;
            c.ipadx = 300;
            consultationForm.add(date, c);

            c.gridx = 0;
            c.gridy = 7;
            c.ipadx = 15;
            consultationForm.add(startTimeL, c);

            c.gridx = 1;
            c.ipadx = 300;
            consultationForm.add(startTime, c);

            c.gridx = 0;
            c.gridy = 8;
            c.ipadx = 15;
            consultationForm.add(endTimeL, c);

            c.gridx = 1;
            c.ipadx = 300;
            consultationForm.add(endTime, c);

            c.gridx = 0;
            c.gridy = 9;
            c.ipadx = 15;
            consultationForm.add(notesL, c);

            c.gridx = 1;
            c.ipadx = 300;
            c.ipady = 50;
            consultationForm.add(notes, c);

            c.gridx = 0;
            c.gridy = 10;
            c.ipadx = 15;
            c.ipady = 5;
            c.weighty = 1;
            consultationForm.add(submitButton, c);
            submitButton.addActionListener(this);

            //log
            logText = new JTextArea();
            logText.setEditable(false);     //read only
            logText.setBackground(Color.white);
            logText.setForeground(Color.blue);
            log = new JScrollPane(logText);

            //comboBox for consultation list
            consultationSelectL = new JLabel("Select Consultation ");
            consultationSelect = new JComboBox();
            consultationButton = new JButton("View Consultation");
            consultationButton.addActionListener(this);

            //textArea for viewing consultations
            consultationDetails = new JTextPane();
            consultationDetails.setEditable(false);     //read only
            consultationDetailsPane = new JScrollPane(consultationDetails);

            //arranging components
            consultationSelectionPanel.add(consultationSelectL);
            consultationSelectionPanel.add(consultationSelect);
            consultationSelectionPanel.add(consultationButton);
            consultationPanel.add(consultationSelectionPanel, BorderLayout.NORTH);
            consultationPanel.add(consultationDetailsPane, BorderLayout.CENTER);
            mainPanel.add(doctorsTablePane);
            mainPanel.add(consultationForm);
            mainPanel.add(log);
            mainPanel.add(consultationPanel);
            this.setLayout(new BorderLayout());
            this.add(mainPanel, BorderLayout.CENTER);
        }

        // the key that is used for encryption and decryption
        final String secretKey = "yasindu20211263";

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == submitButton) {
                // try and catch used for input validation in the GUI
                try {
                    String pName = patientName.getText();
                    String pSurname = patientSurname.getText();
                    String pDateOfBirth = patientDateOfBirth.getText();

                    // GUI Input validation for date of birth
                    try {
                        Date date = new SimpleDateFormat("yyyy/MM/dd").parse(pDateOfBirth);
                        String[] dateSections = pDateOfBirth.split("/");
                        int month = Integer.parseInt(dateSections[1]);
                        int day = Integer.parseInt(dateSections[2]);
                        if (month <= 0 || month > 12) {
                            logText.append("\nRecord Not Added\nInvalid Input for Month in Date of Birth\nEnter year, month, day seperated by slashes in the format YYYY/MM/DD");
                            throw new Exception();
                        }
                        if (day <= 0 || day > 31) {
                            logText.append("\nRecord Not Added\nInput Invalid for day in Date of Birth\nEnter year, month, day seperated by slashes in the format YYYY/MM/DD");
                            throw new Exception();
                        }
                    } catch (Exception p) {
                        logText.append("\nRecord Not Added\nInput Invalid for date in Date of Birth\nEnter year, month, day seperated by slashes in the format YYYY/MM/DD");
                        throw new Exception();
                    }

                    long pMobileNum = Long.parseLong(patientMobileNum.getText());
                    String pPatientId = patientId.getText();
                    Doctor doctor = (Doctor) doctorSelect.getSelectedItem();
                    String cDate = date.getText();

                    // GUI Input validation for consultation date
                    try {
                        Date date = new SimpleDateFormat("yyyy/MM/dd").parse(cDate);
                        String[] dateSections = cDate.split("/");
                        int month = Integer.parseInt(dateSections[1]);
                        int day = Integer.parseInt(dateSections[2]);
                        if (month <= 0 || month > 12) {
                            logText.append("\nRecord Not Added\nInvalid Input for Month in Consultation Date\nEnter year, month, day seperated by slashes in the format YYYY/MM/DD\n");
                            throw new Exception();
                        }
                        if (day <= 0 || day > 31) {
                            logText.append("\nRecord Not Added\nInput Invalid for day in Consultation Date\nEnter year, month, day seperated by slashes in the format YYYY/MM/DD\n");
                            throw new Exception();
                        }
                    } catch (Exception p) {
                        logText.append("\nRecord Not Added\nInput Invalid for date in Consultation Date\nEnter year, month, day seperated by slashes in the format YYYY/MM/DD");
                        throw new Exception();
                    }

                    // GUI Input validation for start time
                    String startTimeString = startTime.getText();
                    int cSTime = Integer.parseInt(startTime.getText());
                    if (startTimeString.length() != 4) {
                        logText.append("\nRecord Not Added\nInput Invalid for time in Start Time\nEnter in the format HHMM (eg: 6:35pm is 1835)");
                        throw new Exception();
                    }
                    String[] results = startTimeString.split("(?<=\\G.{" + 2 + "})");
                    int startHours = Integer.parseInt(results[0]);
                    if (startHours < 0 || startHours > 23) {
                        logText.append("\nRecord Not Added\nInput Invalid for hours in Start time");
                        throw new Exception();
                    }
                    int startMins= Integer.parseInt(results[1]);
                    if (startMins < 0 || startMins > 59) {
                        logText.append("\nRecord Not Added\nInput Invalid for minutes in Start time");
                        throw new Exception();
                    }

                    // GUI Input validation for end time
                    String endTimeString = endTime.getText();
                    int cETime = Integer.parseInt(endTime.getText());
                    if (endTimeString.length() != 4) {
                        logText.append("\nRecord Not Added\nInput Invalid for time in End Time\nEnter in the format HHMM (eg: 6:35pm is 1835)");
                        throw new Exception();
                    }
                    String[] results2 = endTimeString.split("(?<=\\G.{" + 2 + "})");
                    int endHours = Integer.parseInt(results2[0]);
                    if (endHours < 0 || endHours > 23) {
                        logText.append("\nRecord Not Added\nInput Invalid for hours in End time");
                        throw new Exception();
                    }
                    int endMins= Integer.parseInt(results2[1]);
                    if (endMins < 0 || endMins > 59) {
                        logText.append("\nRecord Not Added\nInput Invalid for minutes in End time");
                        throw new Exception();
                    }

                    if (startMins == endMins)
                        endHours -= 1;      // to make the calculation accurate for start time 1200 and end  time 1300
                    double cCost = calculateCost(startHours, endHours, pPatientId);

                    logText.append("\nConsultation Cost: " + cCost + " £");
                    //Encrypting and then storing
                    String cNotes = Cryptography.encrypt(notesA.getText(), secretKey);
                    Patient patient = new Patient(pName, pSurname, pDateOfBirth, pMobileNum, pPatientId);

                    if (checkConsultationAvailability(doctor, cDate, cSTime, cETime)) {
                        consultationsList.add(new Consultation(cDate, cSTime, cETime, doctor, patient, cCost, cNotes));
                        logText.append("\nConsultation successfully added");                    
                        consultationSelect.addItem(new Consultation(cDate, cSTime, cETime, doctor, patient, cCost, cNotes));        //adding to the consultations combo box
                        //clearForm();
                    } else {
                        outerLoop:
                        while(true) {
                            logText.append("\nThe Doctor you selected is not available in that slot");
                            ArrayList<Doctor> randomDoctors = (ArrayList)doctorsList.clone();       //cloning the arraylist
                            Collections.shuffle(randomDoctors);     //shuffling the elements so that the doctor is assigned randomly and not according to the alphabetical order
                            for (Doctor d: randomDoctors) {
                                if (checkConsultationAvailability(d, cDate, cSTime, cETime)) {
                                    consultationsList.add(new Consultation(cDate, cSTime, cETime, d, patient, cCost, cNotes));
                                    logText.append("\nConsultation successfully added with an available doctor");                                   
                                    consultationSelect.addItem(new Consultation(cDate, cSTime, cETime, d, patient, cCost, cNotes));        //adding to the combo box
                                    //clearForm();
                                    break outerLoop;
                                }
                            }
                            logText.append("\nNo doctors are available in that slot. Add another consultation with a different time slot");
                            break outerLoop;
                        }
                    }
                } catch (NumberFormatException n) {
                    logText.append("\nCouldn't make appointment. Make sure that you enter numerical values for mobile no, times");
                } catch (Exception l) {
                    //logText.append("\Thrown Exception");
                }
            }

            if (e.getSource() == consultationButton) {
                Consultation consultation = (Consultation) consultationSelect.getSelectedItem();
                String fullDetails = consultation.fullDetails();
                // Appending after decrypting
                fullDetails += Cryptography.decrypt(consultation.getNotes(), secretKey);
                consultationDetails.setText(fullDetails);
            }
        }
    }

    public double calculateCost(int sHours, int eHours, String patientId) {
        double cost;
        int duration = (eHours - sHours) + 1;
        int hourlyRate = 15;    //if it's the patient's first consultation, the hourly rate is 15.
        for (Consultation c: consultationsList) {
            if (c.getPatient().getPatientId().equals(patientId)) {
                hourlyRate = 25;    //if it isn't the patient's first consultation, the hourly rate is 25.
                break;
            }
        }
        cost = duration*hourlyRate;
        return cost;
    }

    public boolean checkConsultationAvailability(Doctor doctor, String cDate, int cSTime, int cETime) {
        for (Consultation c: consultationsList) {
            if (c.getDoctor().equals(doctor)) {
                if (c.getDate().equals(cDate)) {
                    if (cSTime >= c.getStartTime() && cSTime <= c.getEndTime()) {     //if start time is between the existing time slot
                        return false;
                    } else if (cETime >= c.getStartTime() && cETime <= c.getEndTime()) {      //if end time is between the existing time slot
                        return false;
                    } else if (c.getStartTime() >= cSTime && c.getStartTime() <= cETime) {      //if the existing time slot is between start time and end time
                        return false;
                    }
                }
            }
        }
        return true;        //returning true after checking with all existing consultations.
    }
}

//Code Reference for Substrings https://www.baeldung.com/java-string-split-every-n-characters