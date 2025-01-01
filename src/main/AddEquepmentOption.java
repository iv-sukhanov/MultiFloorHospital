package main;

import javax.swing.*;

public class AddEquepmentOption extends Option {
    
    public AddEquepmentOption() {
        super(
            "AddEquepmentOption", 
            new JButton("Add Equepment")
        );
    }

    public void execute(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Add Equepment Option Selected");
    }
}