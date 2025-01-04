package main;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class CenteredElementPanel extends JPanel {
    public CenteredElementPanel(HintTextField textField, int margins) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalStrut(margins));
        add(textField);
        add(Box.createHorizontalStrut(margins));
    }

    public CenteredElementPanel(HintTextField textField, int margins, Dimension pannelSize) {
        this(textField, margins);
        setMaximumSize(pannelSize);
    }
}
