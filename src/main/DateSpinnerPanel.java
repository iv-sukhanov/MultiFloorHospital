package main;

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

    static final int SPINNER_WIDTH = 150;
    static final int SPINNER_HIGHT = 40;

    DateSpinnerPanel(String label, Dimension size, int horizontalMargin) {
        
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);
        SpinnerDateModel dateModel = new SpinnerDateModel(
            new Date(), null, Calendar.getInstance().getTime(), Calendar.MONTH
        );
        JSpinner dateSpinner = new JSpinner(dateModel);
        DateEditor dateEditor = new DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setMaximumSize(new Dimension(SPINNER_WIDTH, SPINNER_HIGHT));
        
        add(Box.createHorizontalStrut(horizontalMargin));
        add(new JLabel(label));
        add(Box.createHorizontalStrut(horizontalMargin));
        add(dateSpinner);
    }
}
