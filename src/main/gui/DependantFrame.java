package main.gui;

import java.awt.Frame;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

public class DependantFrame extends JDialog {
    public DependantFrame(Frame frame, String title, LayoutManager layout) {
        super(frame, title, true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(frame.getSize());
        setLayout(layout);
        setLocationRelativeTo(frame);
    }

    public DependantFrame(Frame frame, String title) {
        super(frame, title, true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(frame.getSize());
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(frame);
    }
}
