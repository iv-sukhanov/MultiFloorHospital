package main.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents a panel with a centered label.
 */
public class LabelPanel extends JPanel {
    
    /**
     * Constructs a LabelPanel object with specified text.
     *
     * @param text the text to display
     */
    public LabelPanel(String text) {
        super(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel(text));
    }

    /**
     * Constructs a LabelPanel object with specified text and size.
     *
     * @param text the text to display
     * @param size the maximum size of the panel
     */
    public LabelPanel(String text, Dimension size) {
        this(text);
        setMaximumSize(size);
    }

    /**
     * Constructs a LabelPanel object with specified text, size, and alignment.
     *
     * @param text the text to display
     * @param size the maximum size of the panel
     * @param alignment the alignment of the label
     */
    public LabelPanel(String text, Dimension size, int alignment) {
        super(new FlowLayout(alignment));
        add(new JLabel(text));
        setMaximumSize(size);
    }
}
