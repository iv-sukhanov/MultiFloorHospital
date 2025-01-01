package main;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class MultiFloorHospital {
    
    private static final Map<String, String> staticMap = new HashMap<>();
    private static final Option[] options = {
        new AddPatientOption(),
        new AddEquipmentOption()
    };

    static final int Y_SPACE_BETWEEN_BUTTONS = 10;
    static final int X_SPACE_BETWEEN_BUTTONS = 0;
    static final int X_BUTTONS_MARGINS = 40;
    static final int BUTTONS_HIGHT = 40;
    static final double X_WINDOW_MULTIPLIER = 0.5;
    static final double Y_WINDOW_MULTIPLIER = 0.5;
    static final double Y_LABEL_MULTIPLIER = 0.3;

    public MultiFloorHospital() {}

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
            currentButton.setMaximumSize(new Dimension(frameWidth - X_BUTTONS_MARGINS, BUTTONS_HIGHT));   // Maximum size
            currentButton.setMinimumSize(new Dimension(frameWidth - X_BUTTONS_MARGINS, BUTTONS_HIGHT));
            
            buttonPanel.add(currentButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(X_SPACE_BETWEEN_BUTTONS, Y_SPACE_BETWEEN_BUTTONS)));
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
        

    public static void main(String[] args) {

        MultiFloorHospital multiFloorHospital = new MultiFloorHospital();
        multiFloorHospital.displayOptions();
    }
}