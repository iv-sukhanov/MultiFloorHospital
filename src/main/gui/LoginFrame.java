package main.gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {
    public LoginFrame(Dimension size, Runnable okAction) {
        super("multi-floor hospital system");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setSize(size);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        CenteredElementPanel loginPanel = new CenteredElementPanel(
            new HintTextField("Please, enter login"),
            30,
            getWidth(),
            4,
            new Dimension(Integer.MAX_VALUE, 20),
            "Login: "
        );

        CenteredElementPanel passwordPanel = new CenteredElementPanel(
            new JPasswordField(8),
            30,
            getWidth(),
            4,
            new Dimension(Integer.MAX_VALUE, 20),
            "Password: "
        );

        NavigationButtonsPanel okPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, 20), 
            e -> {
                if (
                    loginPanel.getText().equals(System.getenv("HOSPITAL_LOGIN")) &&
                    passwordPanel.getText().equals(System.getenv("HOSPITAL_PASSWORD"))
                ) {
                    okAction.run();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong login or password");
                }
            }
        );

        loginPanel.addActionListener(
            e -> {
                passwordPanel.requestFocusInWindow();
            }
        );

        passwordPanel.addActionListener(
            e -> {
                okPanel.accept();
            }   
        );

        add(Box.createVerticalStrut(30));
        add(new CenteredLabel(
            "Please, enter login and password: ", 
            new Dimension(Integer.MAX_VALUE, 20)
        ));
        add(Box.createVerticalStrut(30));
        add(loginPanel);
        add(Box.createVerticalStrut(30));
        add(passwordPanel);
        add(Box.createVerticalStrut(30));
        add(new FillerPannel());
        add(okPanel);
    }
}
