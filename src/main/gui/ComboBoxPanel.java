package main.gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboBoxPanel extends JPanel {
    
    public ComboBoxPanel(String pormpt, String[] options, Dimension size, int hospitalMargin) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);
        add(Box.createHorizontalStrut(hospitalMargin));
        JLabel genderLabel = new JLabel(pormpt);
        add(genderLabel);
        add(Box.createHorizontalStrut(hospitalMargin / 4));
        JComboBox<String> genderComboBox = new JComboBox<>(options);
        add(genderComboBox);
        add(Box.createHorizontalStrut(hospitalMargin));
    }
}
    