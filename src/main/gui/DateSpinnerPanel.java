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

public class DateSpinnerPanel extends JPanel {

    private static final int SPINNER_WIDTH = 150;
    private static final int SPINNER_HIGHT = 40;
    private JSpinner dateSpinner;


    public DateSpinnerPanel(String label, Dimension size, int horizontalMargin, Comparable<Date> start, Comparable<Date> end) {
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);
        SpinnerDateModel dateModel = new SpinnerDateModel(
            new Date(), start, end, Calendar.MONTH
        );
        dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new DateEditor(dateSpinner, "dd/MM/yyyy"));
        dateSpinner.setMaximumSize(new Dimension(SPINNER_WIDTH, SPINNER_HIGHT));

        add(Box.createHorizontalStrut(horizontalMargin));
        add(new JLabel(label));
        add(Box.createHorizontalStrut(horizontalMargin / 4));
        add(dateSpinner);
    }

    public Date getDate() {
        return ((SpinnerDateModel)(dateSpinner.getModel())).getDate();
    }
}
