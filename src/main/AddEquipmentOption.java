package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddEquipmentOption extends Option {

    private static final int TEXT_FIELDS_HIGHT = 20;
    private static final int HORIZONTAL_MARGIN = 30;
    private static final int VERTICAL_MARGIN = 20;
    
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

        CenteredLabel promptPanel = new CenteredLabel(
            "Please enter the following information:",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );
        promptPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        HintTextField nameField = new HintTextField(
            "Enter the name of the equipment",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );
        CenteredElementPanel namePanel = new CenteredElementPanel(
            nameField, 
            HORIZONTAL_MARGIN, 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );
        namePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        HintTextField manufacturerField = new HintTextField(
            "Enter the manufacturer of the equipment",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );
        CenteredElementPanel manufacturerPanel = new CenteredElementPanel(
            manufacturerField, 
            HORIZONTAL_MARGIN, 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );
        manufacturerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JRadioButton availableRadioButton = new JRadioButton("The equipment is available", true);;
        RadioButtonPanel radioPanel = new RadioButtonPanel(
            availableRadioButton,
            HORIZONTAL_MARGIN / 2,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );
        radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        DateSpinnerPanel datePanel = new DateSpinnerPanel(
            "Enter the last maintenance date:",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            HORIZONTAL_MARGIN    
        );
        datePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

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

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT), 
            e -> {equipmentFrame.dispose();}, 
            e -> {equipmentFrame.dispose();}
        );
        navigationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        equipmentFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN));
        equipmentFrame.add(promptPanel);
        equipmentFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN));
        equipmentFrame.add(namePanel);
        equipmentFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN));        
        equipmentFrame.add(manufacturerPanel);
        equipmentFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN));
        equipmentFrame.add(radioPanel);
        equipmentFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN));
        equipmentFrame.add(datePanel);        
        equipmentFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN * 2));
        equipmentFrame.add(navigationPanel);        

        equipmentFrame.setLocationRelativeTo(mainFrame);
        equipmentFrame.setVisible(true);
    }
}