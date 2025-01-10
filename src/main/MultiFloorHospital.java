package main;

import java.awt.*;
import javax.swing.*;

import main.entities.*;
import main.gui.CenteredElementPanel;
import main.gui.CenteredLabel;
import main.gui.FillerPannel;
import main.gui.HintTextField;
import main.gui.NavigationButtonsPanel;
import main.options.*;

public class MultiFloorHospital extends Hospital implements HospitalProperties {
    private final HospitalEquipmentList equipmentList = new HospitalEquipmentList(this);
    private final HospitalStaffList staffList = new HospitalStaffList(this);
    private final HospitalFloorList hospitalFloorList = new HospitalFloorList(this, NUMBER_OF_FLOORS);
    private final PatientList patientList = new PatientList();
    private final HospitalPharmasyList hospitalPharmasyList = new HospitalPharmasyList(this);
    

    private final Option[] options = {
        new PatientOption(patientList, staffList, equipmentList, hospitalFloorList),
        new HospitalStaffOption(staffList, patientList),
        new EquipmentOption(equipmentList),
        new RoomsOption(hospitalFloorList),
        new HospitalPharmasyOption(hospitalPharmasyList),
    };

    static final int Y_SPACE_BETWEEN_BUTTONS = 10;
    static final int X_SPACE_BETWEEN_BUTTONS = 0;
    static final int X_BUTTONS_MARGINS = 40;
    static final int BUTTONS_HIGHT = 40;
    static final double X_WINDOW_MULTIPLIER = 0.5;
    static final double Y_WINDOW_MULTIPLIER = 0.5;
    static final double Y_LABEL_MULTIPLIER = 0.3;

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
        
        JButton exitButton = new JButton("EXIT");
        JPanel exitPanel = new JPanel();
        exitButton.setBackground(new Color(255, 0, 0));
        exitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
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

        MultiFloorHospital multiFloorHospital = new MultiFloorHospital();
        multiFloorHospital.logIn(Toolkit.getDefaultToolkit().getScreenSize());
    }

    private void logIn(Dimension size) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * X_WINDOW_MULTIPLIER);
        int frameHeight = (int) (screenSize.height * Y_WINDOW_MULTIPLIER);
        size = new Dimension(frameWidth, frameHeight);

        JFrame loginFrame = new JFrame("multi-floor hospital system");
        loginFrame.setLayout(new BoxLayout(loginFrame.getContentPane(), BoxLayout.Y_AXIS));
        loginFrame.setSize(size);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CenteredElementPanel loginPanel = new CenteredElementPanel(
            new HintTextField("Please, enter login"),
            30,
            loginFrame.getWidth(),
            4,
            new Dimension(Integer.MAX_VALUE, 20),
            "Login: "
        );

        CenteredElementPanel passwordPanel = new CenteredElementPanel(
            new JPasswordField(8),
            30,
            loginFrame.getWidth(),
            4,
            new Dimension(Integer.MAX_VALUE, 20),
            "Password: "
        );

        NavigationButtonsPanel okPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, 20), 
            e -> {
                if (
                    loginPanel.getText().equals(System.getenv("HOSPITAL_LOGIN")) &&
                    passwordPanel.getText().equals(System.getenv("HOSPITAL_PASSWORD"))
                ) {
                    displayOptions();
                    loginFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Wrong login or password");
                }
        });

        loginPanel.addActionListener(
            e -> {
                okPanel.accept();
            }
        );

        passwordPanel.addActionListener(
            e -> {
                okPanel.accept();
            }   
        );

        loginFrame.add(Box.createVerticalStrut(30));
        loginFrame.add(new CenteredLabel(
            "Please, enter login and password: ", 
            new Dimension(Integer.MAX_VALUE, 20)
        ));
        loginFrame.add(Box.createVerticalStrut(30));
        loginFrame.add(loginPanel);
        loginFrame.add(Box.createVerticalStrut(30));
        loginFrame.add(passwordPanel);
        loginFrame.add(Box.createVerticalStrut(30));
        loginFrame.add(new FillerPannel());
        loginFrame.add(okPanel);
        loginFrame.setVisible(true);
    }
}