package main.gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Represents a panel with a radio button and optional text field or combo box.
 */
public class RadioButtonPanel extends JPanel {

    private JRadioButton radioButton;
    private JTextField textField;
    private JComboBox<String> comboBox;

    /**
     * Constructs a RadioButtonPanel object with a radio button.
     *
     * @param text the text for the radio button
     * @param isSelected the initial selection state of the radio button
     * @param horizontalMargin the horizontal margin
     * @param size the maximum size of the panel
     */
    public RadioButtonPanel(String text, boolean isSelected, int horizontalMargin, Dimension size) {
        this.radioButton = new JRadioButton(text, isSelected);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(size);
        add(Box.createHorizontalStrut(horizontalMargin));
        add(this.radioButton);
    }

    /**
     * Constructs a RadioButtonPanel object with a radio button and a combo box.
     *
     * @param text the text for the radio button
     * @param options the options for the combo box
     * @param isSelected the initial selection state of the radio button
     * @param horizontalMargin the horizontal margin
     * @param maxWidth the maximum width of the panel
     * @param divisor the divisor for calculating the horizontal strut
     * @param size the maximum size of the panel
     */
    public RadioButtonPanel(String text, String[] options, boolean isSelected, int horizontalMargin, int maxWidth, int divisor, Dimension size) {
        this.radioButton = new JRadioButton(text, isSelected);
        this.comboBox = new JComboBox<>();
        comboBox.addItem("N/A");
        for (String option : options) {
            comboBox.addItem(option);
        }

        comboBox.setVisible(false);
        radioButton.addActionListener(e -> {
            comboBox.setVisible(radioButton.isSelected());
            revalidate();
            repaint();
        });

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);
        add(Box.createHorizontalStrut(horizontalMargin));
        add(this.radioButton);
        add(Box.createHorizontalStrut(maxWidth / divisor - horizontalMargin - this.radioButton.getPreferredSize().width));
        add(comboBox);
        add(Box.createHorizontalStrut(horizontalMargin));
    }

    /**
     * Constructs a RadioButtonPanel object with a radio button and a text field.
     *
     * @param text the text for the radio button
     * @param hint the hint for the text field
     * @param isSelected the initial selection state of the radio button
     * @param horizontalMargin the horizontal margin
     * @param maxWidth the maximum width of the panel
     * @param divisor the divisor for calculating the horizontal strut
     * @param size the maximum size of the panel
     */
    public RadioButtonPanel(String text, String hint, boolean isSelected, int horizontalMargin, int maxWidth, int divisor, Dimension size) {
        this.radioButton = new JRadioButton(text, isSelected);
        textField = new HintTextField(hint, size);
        
        textField.setVisible(false);
        radioButton.addActionListener(e -> {
            textField.setVisible(radioButton.isSelected());
            revalidate();
            repaint();
        });
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);
        add(Box.createHorizontalStrut(horizontalMargin));
        add(this.radioButton);
        add(Box.createHorizontalStrut(maxWidth / divisor - horizontalMargin - this.radioButton.getPreferredSize().width));
        add(textField);
        add(Box.createHorizontalStrut(horizontalMargin));
    }

    /**
     * Returns whether the radio button is selected.
     *
     * @return true if the radio button is selected, false otherwise
     */
    public boolean isSelected() {
        return radioButton.isSelected();
    }

    /**
     * Returns the text from the text field if the radio button is selected.
     *
     * @return the text from the text field, or null if the text field is not present or the radio button is not selected
     */
    public String getText() {
        if ((textField == null) || !radioButton.isSelected()) {
            return null;
        }
        return textField.getText();
    }

    /**
     * Returns the selected index of the combo box if the radio button is selected.
     *
     * @return the selected index, or -1 if the combo box is not present or the radio button is not selected
     */
    public int getSelectedIndex() {
        if ((comboBox == null) || !radioButton.isSelected()) {
            return -1;
        }
        return comboBox.getSelectedIndex() - 1;
    }

    /**
     * Returns the selected item from the combo box if the radio button is selected.
     *
     * @return the selected item, or null if the combo box is not present, the radio button is not selected, or the selected index is 0
     */
    public String getSelectedItem() {
        if ((comboBox == null) || !radioButton.isSelected() || comboBox.getSelectedIndex() == 0) {
            return null;
        }
        return (String) comboBox.getSelectedItem();
    }
}
