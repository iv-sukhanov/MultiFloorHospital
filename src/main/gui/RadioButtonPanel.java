package main.gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RadioButtonPanel extends JPanel {

    private JRadioButton radioButton;
    private JTextField textField;
    
    public RadioButtonPanel(String text, boolean isSelected, int horizontalMargin, Dimension size) {
        this.radioButton = new JRadioButton(text, isSelected);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(size);
        add(Box.createHorizontalStrut(horizontalMargin));
        add(this.radioButton);
    }

    public RadioButtonPanel(String text, String[] options, boolean isSelected, int horizontalMargin, Dimension size) {
        this.radioButton = new JRadioButton(text, isSelected);
        JComboBox<String> comboBox = new JComboBox<>(options);
        
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
        add(Box.createHorizontalStrut(horizontalMargin));
        add(comboBox);
        add(Box.createHorizontalStrut(horizontalMargin));
    }

    public RadioButtonPanel(String text, String hint, boolean isSelected, int horizontalMargin, Dimension size) {
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
        add(Box.createHorizontalStrut(horizontalMargin));
        add(textField);
        add(Box.createHorizontalStrut(horizontalMargin));
    }

    public boolean isSelected() {
        return radioButton.isSelected();
    }

    public String getText() {
        if (textField == null) {
            return null;
        }

        return textField.getText();
    }
}
