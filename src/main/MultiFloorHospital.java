package main;
import java.awt.*;

import javax.swing.*;

public class MultiFloorHospital {

    private Option[] options;

    public MultiFloorHospital(Option[] options) {
        this.options = options;
    }

    public void displayOptions() {
        JFrame frame = new JFrame("MultiFloorHospital");
        
        frame.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.5);
        int frameHeight = (int) (screenSize.height * 0.5);

        JLabel label = new JLabel("Welcome to the Application! Choose an option:", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(frameWidth, frameHeight / 3));
        frame.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(options.length, 1, 10, 20));

        for (Option option : options) {

            if (option == null) {
                throw new IllegalArgumentException("Option cannot be null");
            }

            if (option.getButton() == null) {
                throw new IllegalArgumentException("Button cannot be null");
            }

            JButton currentButton = option.getButton();
            currentButton.addActionListener(e -> option.execute(frame));
            buttonPanel.add(currentButton);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setSize(frameWidth, frameHeight);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
        

    public static void main(String[] args) {

        MultiFloorHospital multiFloorHospital = new MultiFloorHospital(new Option[] {
            new AddPatientOption(),
            new AddEquepmentOption()
        });
        
        multiFloorHospital.displayOptions();
    }
}