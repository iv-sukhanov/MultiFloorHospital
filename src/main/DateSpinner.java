package main;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;

public class DateSpinner extends JPanel {

    static final int SPINNER_WIDTH = 150;
    static final int SPINNER_HIGHT = 40;

    DateSpinner(String label) {
        setAlignmentX(RIGHT_ALIGNMENT);
        // setMinimumSize(new Dimension(500, 40));

        JLabel dateLabel = new JLabel(label);

        SpinnerDateModel dateModel = new SpinnerDateModel(
            new Date(), null, null, Calendar.MONTH
        );
        JSpinner dateSpinner = new JSpinner(dateModel);
        DateEditor dateEditor = new DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setPreferredSize(new Dimension(SPINNER_WIDTH, SPINNER_HIGHT));
        dateSpinner.setMaximumSize(new Dimension(SPINNER_WIDTH, SPINNER_HIGHT));
        dateSpinner.setMinimumSize(new Dimension(SPINNER_WIDTH, SPINNER_HIGHT));
        
        add(Box.createRigidArea(new Dimension(30, 0)));
        add(dateLabel);
        add(Box.createRigidArea(new Dimension(10, 0)));
        add(dateSpinner);
    }
}
