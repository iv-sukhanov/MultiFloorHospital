package main.options;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.time.ZoneId;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.entities.HospitalStaff;
import main.entities.HospitalStaffList;
import main.entities.PatientList;
import main.gui.CenteredElementPanel;
import main.gui.CenteredLabel;
import main.gui.ComboBoxPanel;
import main.gui.DateSpinnerPanel;
import main.gui.DependantFrame;
import main.gui.HintTextField;
import main.gui.NavigationButtonsPanel;
import main.gui.RadioButtonPanel;

public class HospitalStaffOption extends Option {

    private final HospitalStaffList staffList;
    private final PatientList patientList;

    public HospitalStaffOption(HospitalStaffList staffList, PatientList patientList) {
        super(
            "HospitalStaffOption", 
            new JButton("Hospital Staff")
        );
        this.staffList = staffList;
        this.patientList = patientList;
    }

    public void execute(JFrame frame) {
        frame.setVisible(false);
        listStaff(frame);
        frame.setVisible(true);
    }

    private void listStaff(JFrame mainFrame) {

        DependantFrame listStaffFrame = new DependantFrame(
            mainFrame, 
            "Hospital Staff", 
            new BorderLayout()
        );
        
        String[] columnNames = {
            "Name",
            "Age",
            "Date of Birth", 
            "ID Number", 
            "Phone Number", 
            "Email",
            "Gender",
            "Owns a car",
            "Car number",
            "Position",
            "Is available",
            "Patient"
        };

        Object[][] staffData = staffList.getDetails();
        JTable staffTable = new JTable(staffData, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(staffTable);
        listStaffFrame.add(tableScrollPane, BorderLayout.CENTER);

        NavigationButtonsPanel navigationButtonsPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            e -> {listStaffFrame.dispose();},
            e -> {
                listStaffFrame.dispose();
                addStaff(mainFrame);
                super.button.doClick();
            },
            e -> {
                int selectedRow = staffTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int confirmed = JOptionPane.showConfirmDialog(listStaffFrame, 
                            "Are you sure you want to delete " + (selectedRow + 1) + "th staff member?",
                            "Delete Confirmation",
                            JOptionPane.YES_NO_OPTION);
        
                        if (confirmed == JOptionPane.YES_OPTION) {
                            staffList.remove(selectedRow);
                            listStaffFrame.dispose();
                            super.button.doClick();
                        }
                    } else {
                        JOptionPane.showMessageDialog(listStaffFrame, "No staff member selected to delete.");
                    }
            }
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
                "Please, enter the full name of the staff member",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Full name: "
        );

        DateSpinnerPanel dateOfBirthPanel = new DateSpinnerPanel(
            "Please, enter the date of birth of the staff member",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            HORIZONTAL_MARGIN
        );

        CenteredElementPanel idPanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the id number of the staff member",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "ID nubmer: "
        );

        CenteredElementPanel telephonePanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the phone number of the staff member",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Phone nubmer: "
        );

        CenteredElementPanel emailPanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the email of the staff member",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Email: "
        );

        String options[] = {"Male", "Female"};
        ComboBoxPanel genderPanel = new ComboBoxPanel(
            "Please, select the gender of the staff member",
            options,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            HORIZONTAL_MARGIN
        );

        CenteredElementPanel positionPanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the position of the staff member",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Position: "
        );

        RadioButtonPanel ownsCarPanel = new RadioButtonPanel(
            "Owns a car",
            "Please, enter the car number of the staff member in XXX-000 format",
            false,
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        String patients[] = patientList.getPatientNames();
            RadioButtonPanel isAvailablePanel = new RadioButtonPanel(
            "Is busy right now",
            patients,
            false,
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            e -> {addStaffFrame.dispose();},
            e -> {
                try {
                    staffList.add(new HospitalStaff(
                        namePanel.getText().equals("Please, enter the full name of the staff member") ?
                            null :
                            namePanel.getText(),
                        dateOfBirthPanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
                        idPanel.getText(), 
                        telephonePanel.getText(), 
                        emailPanel.getText().equals("Please, enter the email of the staff member") ? 
                            null : 
                            emailPanel.getText(), 
                        genderPanel.getSelectedIndex() == 0, 
                        ownsCarPanel.isSelected(), 
                        ownsCarPanel.getText(),
                        positionPanel.getText().equals("Please, enter the position of the staff member") ?
                            null :
                            positionPanel.getText(), 
                        !isAvailablePanel.isSelected(), 
                        isAvailablePanel.getSelectedIndex() == -1 ? 
                            null : 
                            patientList.getPatient(isAvailablePanel.getSelectedIndex())
                    ));
                }
                catch (IllegalArgumentException exception) {
                    JOptionPane.showMessageDialog(addStaffFrame, exception.getMessage());
                    return;
                }
                addStaffFrame.dispose();
            }
        );

        JPanel elementsToDisplay[] = {
            titlePanel,
            namePanel,
            idPanel,
            telephonePanel,
            emailPanel,
            positionPanel,
            ownsCarPanel,
            isAvailablePanel,
            genderPanel,
            dateOfBirthPanel,
            navigationPanel
        };

        for (JPanel panel : elementsToDisplay) {
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            addStaffFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN));
            addStaffFrame.add(panel);
        }
        
        addStaffFrame.setVisible(true);
    }
}
