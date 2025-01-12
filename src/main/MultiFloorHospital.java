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
import main.gui.MainMenuFrame;
import main.options.*;

/**
 * Represents the main class for the MultiFloorHospital application.
 */
public class MultiFloorHospital extends Hospital implements HospitalProperties {

    private static final long serialVersionUID = 1L;

    private static final String FILENAME = "hospital.bak";
    private static final String FILE_EXTENSION = ".bak";

    private static final double X_WINDOW_MULTIPLIER = 0.5;
    private static final double Y_WINDOW_MULTIPLIER = 0.5;
    private final Dimension FRAME_SIZE = getFameSize();

    private static final double INITIAL_BALANCE = 0;

    private final HospitalEquipmentList equipmentList = new HospitalEquipmentList(this);
    private final HospitalStaffList staffList = new HospitalStaffList(this);
    private final HospitalFloorList hospitalFloorList = new HospitalFloorList(this, NUMBER_OF_FLOORS);
    private final PatientList patientList = new PatientList();
    private final HospitalPharmasyList hospitalPharmasyList = new HospitalPharmasyList(this);
    private final HospitalFinancial_Accounts financialAccounts = new HospitalFinancial_Accounts(this, INITIAL_BALANCE);
    
    private transient Option[] options;

    /**
     * Constructs a MultiFloorHospital object.
     */
    public MultiFloorHospital() {
        super(HOSPITAL_NAME, HOSPITAL_ADDRESS, HOSPITAL_PHONE, HOSPITAL_EMAIL);
    }

    /**
     * The main method to run the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MultiFloorHospital.getInstance().run();
    }

    /**
     * Runs the application by displaying the login frame.
     */
    public void run() {
        LoginFrame loginFrame = new LoginFrame(
            FRAME_SIZE, 
            () -> displayOptions() 
        );
        
        loginFrame.setVisible(true);
    }

    /**
     * Displays the main menu options.
     */
    private void displayOptions() {
        initOptions();
        MainMenuFrame mainFrame = new MainMenuFrame(options, FRAME_SIZE, () -> saveData());
        mainFrame.setVisible(true);
    }

    /**
     * Prompts the user to save data with a default or custom filename.
     */
    private void saveData() {
        Object[] options = {"Default", "Custom", "Clear"};
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
            saveData(userInput + FILE_EXTENSION);
        } else if (choice == 2) {
            return;
        }
    }

    /**
     * Saves data to the specified filename.
     *
     * @param filename the filename to save data to
     */
    private void saveData(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            JOptionPane.showMessageDialog(null, "Data successfully saved to " + filename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error occurred saving the data to " + filename);
        }
    }

    /**
     * Returns an instance of MultiFloorHospital, optionally loading data from a file.
     *
     * @return an instance of MultiFloorHospital
     */
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
        
        Object[] filenameOptions = {"Default", "Custom", "Clear"};
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
            return loadData(userInput + FILE_EXTENSION);
        }
        return new MultiFloorHospital();
    }

    /**
     * Loads data from the specified filename.
     *
     * @param filename the filename to load data from
     * @return an instance of MultiFloorHospital
     */
    private static MultiFloorHospital loadData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (MultiFloorHospital) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return new MultiFloorHospital();
    }

    /**
     * Initializes the options for the main menu.
     */
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

    /**
     * Returns the frame size based on the screen size.
     *
     * @return the frame size
     */
    private Dimension getFameSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * X_WINDOW_MULTIPLIER);
        int frameHeight = (int) (screenSize.height * Y_WINDOW_MULTIPLIER);
        return new Dimension(frameWidth, frameHeight);
    }
}