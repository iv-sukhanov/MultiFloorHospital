package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class AddEquipmentOption extends Option {
    
    public AddEquipmentOption() {
        super(
            "AddEquepmentOption", 
            new JButton("Add Equepment")
        );
    }

    public void execute(JFrame frame) {
        frame.setVisible(false);
        addEquepment(frame);
        frame.setVisible(true);
    }

    private void addEquepment(JFrame mainFrame) {
        JDialog equipmentFrame = new JDialog(mainFrame,"Add Equepment", true);
        equipmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        equipmentFrame.setSize(mainFrame.getSize());
        equipmentFrame.setLayout(new BoxLayout(equipmentFrame.getContentPane(), BoxLayout.Y_AXIS));

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 0, 0));
        backButton.setAlignmentX(JButton.LEFT_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(70, 20));
        backButton.setMaximumSize(new Dimension(70, 20));
        backButton.setMinimumSize(new Dimension(70, 20));
        backButton.addActionListener(e -> equipmentFrame.dispose());

        equipmentFrame.add(backButton);
        equipmentFrame.setLocationRelativeTo(mainFrame);
        equipmentFrame.setVisible(true);
    }
}