package main;

import javax.swing.*;

public class AddPatientOption extends Option {
    
    public AddPatientOption() {
        super(
            "AddPatientOption", 
            new JButton("Add Patient")
        );
    }

    public void execute(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Add Patient Option Selected");
    }
}
