package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

public class AddEquipmentOption extends Option {

    private static final int HEIGHT_MARGIN = 30;
    
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

        Dimension mainFrameSize = mainFrame.getSize();

        JDialog equipmentFrame = new JDialog(mainFrame,"Add Equepment", true);
        equipmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        equipmentFrame.setSize(mainFrameSize);
        equipmentFrame.setLayout(new BoxLayout(equipmentFrame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel promptLabel = new JLabel("Please fill the fields:");
        promptLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        HintTextField nameField = new HintTextField("Enter the name of the equipment");
        nameField.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        nameField.setPreferredSize(new Dimension((int)mainFrameSize.getWidth() - 60, 20));
        nameField.setMinimumSize(new Dimension((int)mainFrameSize.getWidth() - 60, 20));
        nameField.setMaximumSize(new Dimension((int)mainFrameSize.getWidth() - 60, 20));

        HintTextField manufacturerField = new HintTextField("Enter the manufacturer of the equipment");
        manufacturerField.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        manufacturerField.setPreferredSize(new Dimension((int)mainFrameSize.getWidth() - 60, 20));
        manufacturerField.setMinimumSize(new Dimension((int)mainFrameSize.getWidth() - 60, 20));
        manufacturerField.setMaximumSize(new Dimension((int)mainFrameSize.getWidth() - 60, 20));

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JRadioButton availableRadioButton = new JRadioButton("The equipment is available", true);
        availableRadioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        // availableRadioButton.setPreferredSize(new Dimension((int)mainFrameSize.getWidth() - 60, 20));
        // availableRadioButton.setMinimumSize(new Dimension((int)mainFrameSize.getWidth() - 60, 20));
        // availableRadioButton.setMaximumSize(new Dimension((int)mainFrameSize.getWidth() - 60, 20));

        DateSpinner datePanel = new DateSpinner("Enter the last maintenance date:");

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(255, 0, 0));
        backButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(100, 20));
        backButton.setMaximumSize(new Dimension(100, 20));
        backButton.setMinimumSize(new Dimension(100, 20));
        backButton.addActionListener(e -> equipmentFrame.dispose());

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(new Color(0, 255, 0));
        confirmButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        confirmButton.setPreferredSize(new Dimension(100, 20));
        confirmButton.setMaximumSize(new Dimension(100, 20));
        confirmButton.setMinimumSize(new Dimension(100, 20));
        confirmButton.addActionListener(e -> {
            new HospitalEquipment(
                nameField.getText(),
                manufacturerField.getText(), 
                availableRadioButton.isSelected(), 
                null
            );
            equipmentFrame.dispose();
        });

        JPanel navigationPanel = new JPanel();
        navigationPanel.add(backButton);
        navigationPanel.add(confirmButton);
        
        equipmentFrame.add(Box.createVerticalStrut(30));
        equipmentFrame.add(promptLabel);
        equipmentFrame.add(Box.createVerticalStrut(30));
        equipmentFrame.add(nameField);
        // equipmentFrame.add(Box.createRigidArea(new Dimension(0, HEIGHT_MARGIN)));
        equipmentFrame.add(Box.createVerticalStrut(30));        
        equipmentFrame.add(manufacturerField);
        // equipmentFrame.add(Box.createRigidArea(new Dimension(0, HEIGHT_MARGIN)));
        equipmentFrame.add(Box.createVerticalStrut(30));
        equipmentFrame.add(availableRadioButton);
        // equipmentFrame.add(Box.createRigidArea(new Dimension(0, HEIGHT_MARGIN)));
        // equipmentFrame.add(datePanel);
        // equipmentFrame.add(Box.createRigidArea(new Dimension(0, HEIGHT_MARGIN)));
        // equipmentFrame.add(navigationPanel);
        equipmentFrame.setLocationRelativeTo(mainFrame);
        equipmentFrame.setVisible(true);
    }
}