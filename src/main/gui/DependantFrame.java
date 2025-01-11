package main.gui;

import java.awt.Frame;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

/**
 * Represents a dependent frame that is modal and dependent on a parent frame.
 */
public class DependantFrame extends JDialog {

    /**
     * Constructs a DependantFrame object with a specified layout.
     *
     * @param frame the parent frame
     * @param title the title of the dialog
     * @param layout the layout manager for the dialog
     */
    public DependantFrame(Frame frame, String title, LayoutManager layout) {
        super(frame, title, true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(frame.getSize());
        setLayout(layout);
        setLocationRelativeTo(frame);
    }

    /**
     * Constructs a DependantFrame object with a default vertical BoxLayout.
     *
     * @param frame the parent frame
     * @param title the title of the dialog
     */
    public DependantFrame(Frame frame, String title) {
        super(frame, title, true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(frame.getSize());
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(frame);
    }
}
