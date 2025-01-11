package main.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelPanel extends JPanel {
    
    public LabelPanel(String text) {
        super(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel(text));
    }

    public LabelPanel(String text, Dimension size) {
        this(text);
        setMaximumSize(size);
    }

    public LabelPanel(String text, Dimension size, int alignment) {
        super(new FlowLayout(alignment));
        add(new JLabel(text));
        setMaximumSize(size);
    }
}
