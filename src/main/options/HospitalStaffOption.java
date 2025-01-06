package main.options;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.gui.CenteredElementPanel;
import main.gui.CenteredLabel;
import main.gui.DateSpinnerPanel;
import main.gui.DependantFrame;
import main.gui.HintTextField;
import main.gui.NavigationButtonsPanel;
import main.gui.RadioButtonPanel;

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
        // boolean ownsCar,
        // String carNumber,
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

        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.X_AXIS));
        genderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT + 20));
        genderPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        genderPanel.add(Box.createHorizontalStrut(HORIZONTAL_MARGIN));
        JLabel genderLabel = new JLabel("Please, select the gender of the staff: ");
        genderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        genderPanel.add(genderLabel);
        genderPanel.add(Box.createHorizontalStrut(HORIZONTAL_MARGIN));
        String options[] = {"Male", "Female"};
        JComboBox<String> genderComboBox = new JComboBox<>(options);
        genderComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        genderPanel.add(genderComboBox);
        genderPanel.add(Box.createHorizontalStrut(HORIZONTAL_MARGIN));



        CenteredElementPanel positionPanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the position of the staff member",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Position: "
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            e -> {addStaffFrame.dispose();},
            e -> {addStaffFrame.dispose();}
        );

        JPanel elementsToDisplay[] = {
            titlePanel,
            namePanel,
            idPanel,
            telephonePanel,
            emailPanel,
            positionPanel,
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
