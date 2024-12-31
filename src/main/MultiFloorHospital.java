package main;
import java.awt.*;

import javax.swing.*;

public class MultiFloorHospital {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MultiFloorHospital");

        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome to the Application! Choose an option:", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        button1.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Button 1 clicked!");
        });

        button2.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Button 2 clicked!");
        });

        button3.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Button 3 clicked!");
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        frame.add(buttonPanel, BorderLayout.CENTER);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int frameWidth = (int) (screenSize.width * 0.5);
        int frameHeight = (int) (screenSize.height * 0.5);

        frame.setSize(frameWidth, frameHeight);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}