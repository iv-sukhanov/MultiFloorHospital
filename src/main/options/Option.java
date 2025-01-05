package main.options;

import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class Option {
    private String name;
    protected JButton button;
    
    public Option(String name, JButton button) {
        this.name = name;
        this.button = button;
    }

    public JButton getButton() {
        return button;
    }


    public String getName() {
        return name;
    }

    public abstract void execute(JFrame frame);
}
