package main.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.options.Option;

/**
 * Represents the main menu frame of the MultiFloorHospital application.
 */
public class MainMenuFrame extends JFrame {

    private static final double Y_LABEL_MULTIPLIER = 0.3;

    private static final int Y_SPACE_BETWEEN_BUTTONS = 10;
    private static final int X_SPACE_BETWEEN_BUTTONS = 0;

    private static final int X_BUTTONS_MARGINS = 40;
    private static final int BUTTONS_HEIGHT = 40;
    
    /**
     * Constructs a MainMenuFrame object.
     *
     * @param options the options to display in the main menu
     * @param size the size of the frame
     * @param saveData the runnable to save data
     */
    public MainMenuFrame(Option[] options, Dimension size, Runnable saveData) {
        super("MultiFloorHospital");
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome to the Application! Choose an option:", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setPreferredSize(new Dimension((int) size.getWidth(), (int) (size.getHeight() * Y_LABEL_MULTIPLIER)));
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        for (Option option : options) {
            if (option == null) {
                throw new IllegalArgumentException("Option cannot be null");
            }

            if (option.getButton() == null) {
                throw new IllegalArgumentException("Button cannot be null");
            }

            JButton currentButton = option.getButton();
            currentButton.addActionListener(e -> option.execute(this));
            currentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            currentButton.setPreferredSize(new Dimension((int) size.getWidth() - X_BUTTONS_MARGINS, BUTTONS_HEIGHT));
            currentButton.setMaximumSize(new Dimension((int) size.getWidth() - X_BUTTONS_MARGINS, BUTTONS_HEIGHT));
            currentButton.setMinimumSize(new Dimension((int) size.getWidth() - X_BUTTONS_MARGINS, BUTTONS_HEIGHT));
            
            buttonPanel.add(currentButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(X_SPACE_BETWEEN_BUTTONS, Y_SPACE_BETWEEN_BUTTONS)));
        }
        
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton exitButton = new JButton("Exit");
        JButton saveButton = new JButton("Save");
        
        saveButton.setPreferredSize(new Dimension(70, 20));
        saveButton.setMaximumSize(new Dimension(70, 20));
        saveButton.setMinimumSize(new Dimension(70, 20));
        saveButton.addActionListener(e -> saveData.run());
        exitPanel.add(saveButton);

        exitButton.setPreferredSize(new Dimension(70, 20));
        exitButton.setMaximumSize(new Dimension(70, 20));
        exitButton.setMinimumSize(new Dimension(70, 20));
        exitButton.addActionListener(e -> this.dispose());
        exitPanel.add(exitButton);

        add(exitPanel, BorderLayout.SOUTH);        
        
        setSize(size);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
