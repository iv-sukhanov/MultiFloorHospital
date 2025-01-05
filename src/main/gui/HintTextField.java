package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class HintTextField extends JTextField {
    
    private boolean showingHint;
    private final Color hintColor = Color.GRAY;
    private final Color textColor = Color.BLACK;
    
    public HintTextField(String hint) {
        super("");
        setForeground(hintColor);
        setText(hint);
        this.showingHint = true;

        this.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                if (showingHint) {
                    showingHint = false;
                    setText(null);
                    setForeground(textColor);
                }
            }

            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(hint);
                    showingHint = true;
                    setForeground(hintColor);
                }
            }
        });
    }

    public HintTextField(String hint, Dimension size) {
        this(hint);
        setMaximumSize(size);
    }
}
