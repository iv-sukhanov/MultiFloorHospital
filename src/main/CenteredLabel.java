package main;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CenteredLabel extends JPanel {
    
    public CenteredLabel(String text) {
        super(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel(text));
    }

    public CenteredLabel(String text, Dimension size) {
        this(text);
        setMaximumSize(size);
    }
    
}
