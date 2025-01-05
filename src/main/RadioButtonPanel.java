package main;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButtonPanel extends JPanel {

    private JRadioButton radioButton;
    
    public RadioButtonPanel(String text, boolean isSelected, int horizontalMargin, Dimension size) {
        this.radioButton = new JRadioButton(text, isSelected);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(size);
        add(Box.createHorizontalStrut(horizontalMargin));
        add(this.radioButton);
    }

    public boolean isSelected() {
        return radioButton.isSelected();
    }
}
