package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Represents a panel with navigation buttons.
 */
public class NavigationButtonsPanel extends JPanel {

    private final JButton acceptButton;
    private static final Dimension BUTTON_SIZE = new Dimension(100, 20);

    /**
     * Constructs a NavigationButtonsPanel object with back and confirm buttons.
     *
     * @param size the size of the panel
     * @param back the action listener for the back button
     * @param confirm the action listener for the confirm button
     */
    public NavigationButtonsPanel(Dimension size, ActionListener back, ActionListener confirm) {
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.YELLOW);

        backButton.setMaximumSize(BUTTON_SIZE);
        backButton.setPreferredSize(BUTTON_SIZE);
        backButton.setMinimumSize(BUTTON_SIZE);
        backButton.addActionListener(back);
    
        acceptButton = new JButton("Confirm");
        acceptButton.setBackground(Color.GREEN);

        acceptButton.setMaximumSize(BUTTON_SIZE);
        acceptButton.setPreferredSize(BUTTON_SIZE);
        acceptButton.setMinimumSize(BUTTON_SIZE);
        acceptButton.addActionListener(confirm);
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(size);
        add(backButton);
        add(acceptButton);
    }

    /**
     * Constructs a NavigationButtonsPanel object with back, add, and delete buttons.
     *
     * @param size the size of the panel
     * @param back the action listener for the back button
     * @param add the action listener for the add button
     * @param delete the action listener for the delete button
     */
    public NavigationButtonsPanel(Dimension size, ActionListener back, ActionListener add, ActionListener delete) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);

        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        acceptButton = new JButton("Back");

        addButton.addActionListener(add);
        deleteButton.addActionListener(delete);
        acceptButton.addActionListener(back);

        rightButtonPanel.add(addButton);
        rightButtonPanel.add(deleteButton);
        leftButtonPanel.add(acceptButton);
        
        add(leftButtonPanel);
        add(new FillerPannel());
        add(rightButtonPanel);
    }

    /**
     * Constructs a NavigationButtonsPanel object with back, add, delete, and details buttons.
     *
     * @param size the size of the panel
     * @param back the action listener for the back button
     * @param add the action listener for the add button
     * @param delete the action listener for the delete button
     * @param details the action listener for the details button
     */
    public NavigationButtonsPanel(Dimension size, ActionListener back, ActionListener add, ActionListener delete, ActionListener details) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);

        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        acceptButton = new JButton("Back");
        JButton detailsButton = new JButton("Details");

        addButton.addActionListener(add);
        deleteButton.addActionListener(delete);
        acceptButton.addActionListener(back);
        detailsButton.addActionListener(details);

        rightButtonPanel.add(detailsButton);
        rightButtonPanel.add(addButton);
        rightButtonPanel.add(deleteButton);
        leftButtonPanel.add(acceptButton);
        
        add(leftButtonPanel);
        add(new FillerPannel());
        add(rightButtonPanel);
    }

    /**
     * Constructs a NavigationButtonsPanel object with an OK button.
     *
     * @param size the size of the panel
     * @param ok the action listener for the OK button
     */
    public NavigationButtonsPanel(Dimension size, ActionListener ok) {
        acceptButton = new JButton("OK");

        acceptButton.setMaximumSize(BUTTON_SIZE);
        acceptButton.setPreferredSize(BUTTON_SIZE);
        acceptButton.setMinimumSize(BUTTON_SIZE);
        acceptButton.addActionListener(ok);
    
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(size);
        add(acceptButton);
    }

    /**
     * Simulates a click on the accept button.
     */
    public void accept() {
        if (acceptButton == null) {
            return;
        }
        acceptButton.doClick();
    }
}
