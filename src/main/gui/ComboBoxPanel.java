package main.gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents a panel with a combo box for selecting options.
 */
public class ComboBoxPanel extends JPanel {

    private JComboBox<String> comboBox;
    private static final int WIDTH = 80;

    /**
     * Constructs a ComboBoxPanel object.
     *
     * @param prompt the prompt to display
     * @param options the options for the combo box
     * @param size the size of the panel
     * @param horizontalMargin the horizontal margin
     */
    public ComboBoxPanel(String prompt, String[] options, Dimension size, int horizontalMargin) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);
        add(Box.createHorizontalStrut(horizontalMargin));
        JLabel genderLabel = new JLabel(prompt);
        add(genderLabel);
        add(Box.createHorizontalStrut(horizontalMargin / 4));
        this.comboBox = new JComboBox<>(options);
        comboBox.setMaximumSize(new Dimension(WIDTH, (int)size.getHeight()));
        add(comboBox);
        add(Box.createHorizontalStrut(horizontalMargin));
    }

    /**
     * Returns the selected index of the combo box.
     *
     * @return the selected index
     */
    public int getSelectedIndex() {
        return comboBox.getSelectedIndex();
    }
}
