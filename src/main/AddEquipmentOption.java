package main;

import java.awt.Component;
import java.awt.Dimension;
import java.time.ZoneId;

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

        CenteredElementPanel namePanel = new CenteredElementPanel(
            new HintTextField(
                "Enter the name of the equipment",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ), 
            HORIZONTAL_MARGIN, 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );
        namePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        CenteredElementPanel manufacturerPanel = new CenteredElementPanel(
            new HintTextField(
                "Enter the manufacturer of the equipment",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ), 
            HORIZONTAL_MARGIN, 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );
        manufacturerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        RadioButtonPanel radioPanel = new RadioButtonPanel(
            "The equipment is available", 
            true,
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

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT), 
            e -> {equipmentFrame.dispose();}, 
            e -> {
                new HospitalEquipment(
                    namePanel.getText(),
                    manufacturerPanel.getText(), 
                    radioPanel.isSelected(), 
                    datePanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                );
                equipmentFrame.dispose();
            }
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
        equipmentFrame.add(new FillerPannel());
        equipmentFrame.add(navigationPanel);        

        equipmentFrame.setLocationRelativeTo(mainFrame);
        equipmentFrame.setVisible(true);
    }
}