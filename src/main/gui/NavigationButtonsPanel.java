package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationButtonsPanel extends JPanel {

    private static final Dimension BUTTON_SIZE = new Dimension(100, 20);

    public NavigationButtonsPanel(Dimension size, ActionListener back, ActionListener confirm) {
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.YELLOW);

        backButton.setMaximumSize(BUTTON_SIZE);
        backButton.setPreferredSize(BUTTON_SIZE);
        backButton.setMinimumSize(BUTTON_SIZE);
        backButton.addActionListener(back);
    
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(Color.GREEN);

        confirmButton.setMaximumSize(BUTTON_SIZE);
        confirmButton.setPreferredSize(BUTTON_SIZE);
        confirmButton.setMinimumSize(BUTTON_SIZE);
        confirmButton.addActionListener(confirm);
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(size);
        add(backButton);
        add(confirmButton);
    }

    public NavigationButtonsPanel(Dimension size, ActionListener back, ActionListener add, ActionListener delete) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);

        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        addButton.addActionListener(add);
        deleteButton.addActionListener(delete);
        backButton.addActionListener(back);

        rightButtonPanel.add(addButton);
        rightButtonPanel.add(deleteButton);
        leftButtonPanel.add(backButton);
        
        add(leftButtonPanel);
        add(new FillerPannel());
        add(rightButtonPanel);
    }
}
