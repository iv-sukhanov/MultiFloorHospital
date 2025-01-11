package main.options;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Represents an abstract option in the hospital management system.
 */
public abstract class Option {
    private String name;
    protected JButton button;

    protected static final int TEXT_FIELDS_HEIGHT = 20;
    protected static final int HORIZONTAL_MARGIN = 30;
    protected static final int VERTICAL_MARGIN = 20;
    protected static final int STAFF_WIDTH_DIVISOR = 4;
    protected static final int PATIENT_WIDTH_DIVISOR = 3;
    protected static final Dimension MAIN_FIELDS_SIZE = new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HEIGHT);
    
    /**
     * Constructs an Option object.
     *
     * @param name the name of the option
     * @param button the button associated with the option
     */
    public Option(String name, JButton button) {
        this.name = name;
        this.button = button;
    }

    /**
     * Returns the button associated with the option.
     *
     * @return the button
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Returns the name of the option.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Executes the option.
     *
     * @param frame the main frame
     */
    public abstract void execute(JFrame frame);
}
