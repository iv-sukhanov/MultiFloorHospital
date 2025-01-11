package main.gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Represents a panel with a centered text field.
 */
public class CenteredTextFieldPanel extends JPanel {
    
    private JTextField textField;

    /**
     * Constructs a CenteredTextFieldPanel object with specified parameters.
     *
     * @param textField the text field to be centered
     * @param margins the margins around the text field
     * @param maxWidth the maximum width of the panel
     * @param divisor the divisor that determines the width of the space between label and text field
     * @param panelSize the maximum size of the panel
     * @param prompt the prompt to display
     */
    public CenteredTextFieldPanel(JTextField textField, int margins, int maxWidth, int divisor, Dimension panelSize, String prompt) {
        this.textField = textField;
        
        JLabel promptLabel = new JLabel(prompt);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(margins));
        add(promptLabel);
        add(Box.createHorizontalStrut(maxWidth / divisor - margins - promptLabel.getPreferredSize().width));
        add(textField);
        add(Box.createHorizontalStrut(margins));
        setMaximumSize(panelSize);
    }
    
    /**
     * Constructs a CenteredTextFieldPanel object with specified margins.
     *
     * @param textField the text field to be centered
     * @param margins the margins around the text field
     */
    public CenteredTextFieldPanel(JTextField textField, int margins) {
        this.textField = textField;
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(margins));
        add(textField);
        add(Box.createHorizontalStrut(margins));
    }

    /**
     * Constructs a CenteredTextFieldPanel object with specified margins and max size.
     *
     * @param textField the text field to be centered
     * @param margins the margins around the text field
     * @param panelSize the maximum size of the panel
     */
    public CenteredTextFieldPanel(JTextField textField, int margins, Dimension panelSize) {
        this(textField, margins);
        setMaximumSize(panelSize);
    }

    /**
     * Returns the text from the text field.
     *
     * @return the text from the text field
     */
    public String getText() {
        return textField.getText();
    }

    /**
     * Adds an action listener to the text field.
     *
     * @param actionListener the action listener to add
     */
    public void addActionListener(ActionListener actionListener) {
        this.textField.addActionListener(actionListener);
    }

    /**
     * Requests focus in the window for the text field.
     *
     * @return true if the request is successful, false otherwise
     */
    public boolean requestFocusInWindow() {
        return textField.requestFocusInWindow();
    }

    /**
     * Sets the next component to focus on when an action is performed.
     *
     * @param nextComponent the next component to focus on
     */
    public void setNextComponent(CenteredTextFieldPanel nextComponent) {
        addActionListener(e -> nextComponent.requestFocusInWindow());
    }
}
