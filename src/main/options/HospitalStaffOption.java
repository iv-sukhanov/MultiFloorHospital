package main.options;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.gui.CenteredElementPanel;
import main.gui.CenteredLabel;
import main.gui.DependantFrame;
import main.gui.HintTextField;
import main.gui.NavigationButtonsPanel;

public class HospitalStaffOption extends Option {

    public HospitalStaffOption() {
        super(
            "HospitalStaffOption", 
            new JButton("Hospital Staff")
        );
    }

    public void execute(JFrame frame) {
        frame.setVisible(false);
        listStaff(frame);
        frame.setVisible(true);
    }

    private void listStaff(JFrame mainFrame) {

        Dimension mainFrameSize = mainFrame.getSize();

        JDialog listStaffFrame = new JDialog(mainFrame, "Hospital Staff", true);
        listStaffFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        listStaffFrame.setSize(mainFrameSize);
        listStaffFrame.setLayout(new BorderLayout());
        listStaffFrame.setLocationRelativeTo(mainFrame);
        
        NavigationButtonsPanel navigationButtonsPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            e -> {listStaffFrame.dispose();},
            e -> {
                listStaffFrame.dispose();
                addStaff(mainFrame);
                super.button.doClick();
            },
            e -> {listStaffFrame.dispose();}
        );
        listStaffFrame.add(navigationButtonsPanel, BorderLayout.SOUTH);
        listStaffFrame.setVisible(true);
    }
    
    private void addStaff(JFrame mainFrame) {
        
        DependantFrame addStaffFrame = new DependantFrame(
            mainFrame, 
            "Add Staff"
        );

        CenteredLabel titlePanel = new CenteredLabel(
            "Please, enter the following information", 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        CenteredElementPanel namePanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the name of the staff member",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            e -> {addStaffFrame.dispose();},
            e -> {addStaffFrame.dispose();}
        );

        JPanel elementsToDisplay[] = {
            titlePanel,
            namePanel,
            navigationPanel
        };

        for (JPanel panel : elementsToDisplay) {
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            addStaffFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN));
            addStaffFrame.add(panel);
        }
        
        addStaffFrame.setVisible(true);
        navigationPanel.requestFocusInWindow();
    }
}
