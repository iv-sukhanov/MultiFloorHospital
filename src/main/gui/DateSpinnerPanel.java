package main.gui;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;

/**
 * Represents a panel with a date spinner for selecting dates.
 */
public class DateSpinnerPanel extends JPanel {

    private static final int SPINNER_WIDTH = 150;
    private static final int SPINNER_HEIGHT = 40;
    private JSpinner dateSpinner;

    /**
     * Constructs a DateSpinnerPanel object.
     *
     * @param label the label to display
     * @param size the size of the panel
     * @param horizontalMargin the horizontal margin
     * @param start the start date
     * @param end the end date
     */
    public DateSpinnerPanel(String label, Dimension size, int horizontalMargin, Comparable<Date> start, Comparable<Date> end) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), start, end, Calendar.MONTH);
        dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new DateEditor(dateSpinner, "dd/MM/yyyy"));
        dateSpinner.setMaximumSize(new Dimension(SPINNER_WIDTH, SPINNER_HEIGHT));

        add(Box.createHorizontalStrut(horizontalMargin));
        add(new JLabel(label));
        add(Box.createHorizontalStrut(horizontalMargin / 4));
        add(dateSpinner);
    }

    /**
     * Returns the selected date.
     *
     * @return the selected date
     */
    public Date getDate() {
        return ((SpinnerDateModel)(dateSpinner.getModel())).getDate();
    }
}
