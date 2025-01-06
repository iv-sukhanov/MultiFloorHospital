package main.options;

import javax.swing.*;

public class PatientOption extends Option {
    //TODO
    
    public PatientOption() {
        super(
            "PatientOption", 
            new JButton("Patients")
        );
    }

    public void execute(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Patient Option Selected");
    }
}
