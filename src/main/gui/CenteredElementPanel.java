package main.gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CenteredElementPanel extends JPanel {
    
    private JTextField textField;
    
    public CenteredElementPanel(JTextField textField, int margins) {
        this.textField = textField;
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(margins));
        add(textField);
        add(Box.createHorizontalStrut(margins));
    }

    public CenteredElementPanel(JTextField textField, int margins, Dimension pannelSize) {
        this(textField, margins);
        setMaximumSize(pannelSize);
    }

    public String getText() {
        return textField.getText();
    }
}
