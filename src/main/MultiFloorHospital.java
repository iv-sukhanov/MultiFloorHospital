package main;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import main.entities.*;
import main.gui.LoginFrame;
import main.options.*;

public class MultiFloorHospital extends Hospital implements HospitalProperties {

    private static final long serialVersionUID = 1L;
    private static final String FILENAME = "hospital.dat";

    private final HospitalEquipmentList equipmentList = new HospitalEquipmentList(this);
    private final HospitalStaffList staffList = new HospitalStaffList(this);
    private final HospitalFloorList hospitalFloorList = new HospitalFloorList(this, NUMBER_OF_FLOORS);
    private final PatientList patientList = new PatientList();
    private final HospitalPharmasyList hospitalPharmasyList = new HospitalPharmasyList(this);
    private final HospitalFinancial_Accounts financialAccounts = new HospitalFinancial_Accounts(this, INITIAL_BALANCE);
    

    private transient Option[] options;

    private void initOptions() {
        options = new Option[] {
            new PatientOption(patientList, staffList, equipmentList, hospitalFloorList),
            new HospitalStaffOption(staffList, patientList),
            new EquipmentOption(equipmentList),
            new RoomsOption(hospitalFloorList),
            new HospitalPharmasyOption(hospitalPharmasyList),
            new FinancialAccountsOption(financialAccounts)
        };
    }

    private static final double X_WINDOW_MULTIPLIER = 0.5;
    private static final double Y_WINDOW_MULTIPLIER = 0.5;

    private static final double Y_LABEL_MULTIPLIER = 0.3;

    private static final int Y_SPACE_BETWEEN_BUTTONS = 10;
    private static final int X_SPACE_BETWEEN_BUTTONS = 0;

    private static final int X_BUTTONS_MARGINS = 40;
    private static final int BUTTONS_HIGHT = 40;

    private static final double INITIAL_BALANCE = 0;

    public MultiFloorHospital() {
        super(HOSPITAL_NAME, HOSPITAL_ADDRESS, HOSPITAL_PHONE, HOSPITAL_EMAIL);
    }

    public void displayOptions() {

        JFrame frame = new JFrame("MultiFloorHospital");
        frame.setLayout(new BorderLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * X_WINDOW_MULTIPLIER);
        int frameHeight = (int) (screenSize.height * Y_WINDOW_MULTIPLIER);

        JLabel label = new JLabel("Welcome to the Application! Choose an option:", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(frameWidth, (int)(frameHeight * Y_LABEL_MULTIPLIER)));
        frame.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        initOptions();
        for (Option option : options) {

            if (option == null) {
                throw new IllegalArgumentException("Option cannot be null");
            }

            if (option.getButton() == null) {
                throw new IllegalArgumentException("Button cannot be null");
            }

            JButton currentButton = option.getButton();
            currentButton.addActionListener(e -> option.execute(frame));
            currentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            currentButton.setPreferredSize(new Dimension(frameWidth - X_BUTTONS_MARGINS, BUTTONS_HIGHT));
            currentButton.setMaximumSize(new Dimension(frameWidth - X_BUTTONS_MARGINS, BUTTONS_HIGHT));
            currentButton.setMinimumSize(new Dimension(frameWidth - X_BUTTONS_MARGINS, BUTTONS_HIGHT));
            
            buttonPanel.add(currentButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(X_SPACE_BETWEEN_BUTTONS, Y_SPACE_BETWEEN_BUTTONS)));
        }
        
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton exitButton = new JButton("Exit");
        JButton saveButton = new JButton("Save");
        
        saveButton.setPreferredSize(new Dimension(70, 20));
        saveButton.setMaximumSize(new Dimension(70, 20));
        saveButton.setMinimumSize(new Dimension(70, 20));
        saveButton.addActionListener(e -> saveData());
        exitPanel.add(saveButton);

        exitButton.setPreferredSize(new Dimension(70, 20));
        exitButton.setMaximumSize(new Dimension(70, 20));
        exitButton.setMinimumSize(new Dimension(70, 20));
        exitButton.addActionListener(e -> frame.dispose());
        exitPanel.add(exitButton);

        frame.add(exitPanel, BorderLayout.SOUTH);        
        
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
        

    public static void main(String[] args) {
        MultiFloorHospital.getInstance().run();
    }

    private void run() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * X_WINDOW_MULTIPLIER);
        int frameHeight = (int) (screenSize.height * Y_WINDOW_MULTIPLIER);

        LoginFrame loginFrame = new LoginFrame(
            new Dimension(frameWidth, frameHeight), 
            () -> displayOptions() 
        );
        
        loginFrame.setVisible(true);
    }

    private void saveData() {

        Object[] options = {"Default", "Cusom", "Clear"};
        int choice = JOptionPane.showOptionDialog(null,
                "Do you want to use default file name or custom one to save the data?",
                "File name",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
            );
        
        if (choice == 0) {
            saveData(FILENAME);
        } else if (choice == 1) {

            String userInput = JOptionPane.showInputDialog("Enter a filename:");
            
            while (userInput != null && !userInput.matches("^[a-zA-Z0-9._-]+$")) {
                JOptionPane.showMessageDialog(null, "Invalid filename. Please use only letters, numbers, dots, underscores and dashes.");
                userInput = JOptionPane.showInputDialog("Enter a filename:");
            }
            
            if (userInput == null) {
                return;
            }
            
            saveData(userInput + ".dat");
        } else if (choice == 2) {
            return;
        }
    }

    private void saveData(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            JOptionPane.showMessageDialog(null, "Data successfully saved to " + filename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error occured saving the data to " + filename);
        }
    }

    public static MultiFloorHospital getInstance() {
        Object[] loadOptions = {"Yes", "No"};
        int loadChoice = JOptionPane.showOptionDialog(null,
                "Do you want to load the data?",
                "Load data",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                loadOptions,
                loadOptions[0]
            );
        
        if (loadChoice != 0) {
            return new MultiFloorHospital();
        }
        
        Object[] filenameOptions = {"Default", "Cusom", "Clear"};
        int filenameChoice = JOptionPane.showOptionDialog(null,
                "Do you want to use default file name or custom one to load the data?",
                "File name",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                filenameOptions,
                filenameOptions[0]
            );
        
        if (filenameChoice == 0) {
            return loadData(FILENAME);
        } else if (filenameChoice == 1) {

            String userInput = JOptionPane.showInputDialog("Enter a filename:");
            
            while (userInput != null && !userInput.matches("^[a-zA-Z0-9._-]+$")) {
                JOptionPane.showMessageDialog(null, "Invalid filename. Please use only letters, numbers, dots, underscores and dashes.");
                userInput = JOptionPane.showInputDialog("Enter a filename:");
            }
            
            if (userInput == null) {
                return new MultiFloorHospital();
            }
            
            return loadData(userInput + ".dat");
        
        }

        return new MultiFloorHospital();
    }

    public static MultiFloorHospital loadData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (MultiFloorHospital) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return new MultiFloorHospital();
    }
}