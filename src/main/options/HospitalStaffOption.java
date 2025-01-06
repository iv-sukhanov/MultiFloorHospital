package main.options;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.time.ZoneId;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.entities.HospitalStaff;
import main.entities.HospitalStaffList;
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

    public HospitalStaffOption(HospitalStaffList staffList) {
        super(
            "HospitalStaffOption", 
            new JButton("Hospital Staff")
        );
        this.staffList = staffList;
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
                // listStaffFrame.dispose();
                addStaff(mainFrame);
                // super.button.doClick();
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

        // String name, +
        // int age, +
        // LocalDate dateOfBirth, +
        // String idNumber, +
        // String phoneNumber, +
        // String email, +
        // boolean isMale, +
        // boolean ownsCar, +
        // String carNumber, +
        // String position, +
        // boolean available, 
        // Patient patient

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
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Name: "
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

        String patients[] = {"One", "Two", "Three"}; //TODO add real patients
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
                staffList.add(new HospitalStaff(
                    namePanel.getText(),
                    dateOfBirthPanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
                    idPanel.getText(), 
                    telephonePanel.getText(), 
                    emailPanel.getText(), 
                    genderPanel.getSelectedIndex() == 0, 
                    ownsCarPanel.isSelected(), 
                    ownsCarPanel.getText(),
                    positionPanel.getText(), 
                    isAvailablePanel.isSelected(), 
                    null //TODO
                ));
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
