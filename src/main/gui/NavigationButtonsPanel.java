package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

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
}
