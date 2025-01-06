package main.options;

import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class Option {
    private String name;
    protected JButton button;

    protected static final int TEXT_FIELDS_HIGHT = 20;
    protected static final int HORIZONTAL_MARGIN = 30;
    protected static final int VERTICAL_MARGIN = 20;
    
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
