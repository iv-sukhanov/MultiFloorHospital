package main.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Represents a filler panel that occupies maximum available space.
 */
public class FillerPannel extends JPanel {

    /**
     * Constructs a FillerPannel object.
     */
    public FillerPannel() {
        setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
}
