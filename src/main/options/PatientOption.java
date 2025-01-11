package main.options;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

import main.entities.HospitalEquipmentList;
import main.entities.HospitalFloorList;
import main.entities.HospitalStaffList;
import main.entities.Patient;
import main.entities.PatientList;
import main.gui.*;

/**
 * Represents the patient option in the hospital management system.
 */
public class PatientOption extends Option {

    private final HospitalStaffList hospitalStaffList; 
    private final HospitalEquipmentList equipmentList; 
    private final HospitalFloorList hospitalFloorList;
    private final PatientList patientList;
    
    /**
     * Constructs a PatientOption object.
     *
     * @param patientList the list of patients
     * @param hospitalStaffList the list of hospital staff
     * @param equipmentList the list of hospital equipment
     * @param hospitalFloorList the list of hospital floors
     */
    public PatientOption(PatientList patientList, HospitalStaffList hospitalStaffList, HospitalEquipmentList equipmentList, HospitalFloorList hospitalFloorList) {
        super(
            "PatientOption", 
            new JButton("Patients")
        );

        this.patientList = patientList;
        this.hospitalStaffList = hospitalStaffList;
        this.equipmentList = equipmentList;
        this.hospitalFloorList = hospitalFloorList;
    }

    /**
     * Executes the patient option.
     *
     * @param frame the main frame
     */
    public void execute(JFrame frame) {
        frame.setVisible(false);
        listPatients(frame);
        frame.setVisible(true);
    }

    /**
     * Displays the list of patients.
     *
     * @param mainFrame the main frame
     */
    private void listPatients(JFrame mainFrame) {
        DependantFrame listPatientsFrame = new DependantFrame(
            mainFrame, 
            "Patients",
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
            "Diagnosis",
            "Assigned Doctor",
            "Room",
            "Bed",
            "Equipment"
        };

        Object[][] patientsData = patientList.getPatientsDetails();
        JTable patientsTable = new JTable(patientsData, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(patientsTable);
        listPatientsFrame.add(tableScrollPane, BorderLayout.CENTER);

        NavigationButtonsPanel navigationButtonsPanel = new NavigationButtonsPanel(
            MAIN_FIELDS_SIZE,
            e -> {listPatientsFrame.dispose();},
            e -> {
                listPatientsFrame.dispose();
                addPatient(mainFrame);
                super.button.doClick();
            },
            e -> {
                int selectedRow = patientsTable.getSelectedRow();
                if (selectedRow != -1) {
                    int confirmed = JOptionPane.showConfirmDialog(listPatientsFrame, 
                        "Are you sure you want to delete " + (selectedRow + 1)  + "th patient?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirmed == JOptionPane.YES_OPTION) {
                        patientList.removePatient(selectedRow);
                        listPatientsFrame.dispose();
                        super.button.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(listPatientsFrame, "No patient selected to delete.");
                }
            }
        );
        listPatientsFrame.add(navigationButtonsPanel, BorderLayout.SOUTH);
        listPatientsFrame.setVisible(true);
    }

    /**
     * Displays the add patient form.
     *
     * @param mainFrame the main frame
     */
    public void addPatient(JFrame mainFrame) {
        DependantFrame addPatientFrame = new DependantFrame(mainFrame, "Add Patient", new BorderLayout());

        JPanel addPatientPanel = new JPanel();
        addPatientPanel.setLayout(new BoxLayout(addPatientPanel, BoxLayout.Y_AXIS));

        LabelPanel titlePanel = new LabelPanel("Please, enter the following information", MAIN_FIELDS_SIZE);

        CenteredTextFieldPanel namePanel = new CenteredTextFieldPanel(
            new HintTextField("Please, enter the full name of the patient", MAIN_FIELDS_SIZE),
            HORIZONTAL_MARGIN,
            addPatientFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE,
            "Full name: "
        );

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        DateSpinnerPanel dateOfBirthPanel = new DateSpinnerPanel(
            "Please, enter the date of birth of the patient",
            MAIN_FIELDS_SIZE,
            HORIZONTAL_MARGIN,
            null,
            calendar.getTime()
        );

        CenteredTextFieldPanel idPanel = new CenteredTextFieldPanel(
            new HintTextField("Please, enter the id number of the patient", MAIN_FIELDS_SIZE),
            HORIZONTAL_MARGIN,
            addPatientFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE,
            "ID number: "
        );

        CenteredTextFieldPanel telephonePanel = new CenteredTextFieldPanel(
            new HintTextField("Please, enter the phone number of the patient", MAIN_FIELDS_SIZE),
            HORIZONTAL_MARGIN,
            addPatientFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE,
            "Phone number: "
        );

        CenteredTextFieldPanel emailPanel = new CenteredTextFieldPanel(
            new HintTextField("Please, enter the email of the patient", MAIN_FIELDS_SIZE),
            HORIZONTAL_MARGIN,
            addPatientFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE,
            "Email: "
        );

        CenteredTextFieldPanel diagnosisPanel = new CenteredTextFieldPanel(
            new HintTextField("Please, enter the diagnosis of the patient", MAIN_FIELDS_SIZE),
            HORIZONTAL_MARGIN,
            addPatientFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE,
            "Diagnosis: "
        );

        String options[] = {"Male", "Female"};
        ComboBoxPanel genderPanel = new ComboBoxPanel(
            "Please, select the gender of the patient",
            options,
            MAIN_FIELDS_SIZE,
            HORIZONTAL_MARGIN
        );

        RadioButtonPanel ownsCarPanel = new RadioButtonPanel(
            "Owns a car",
            "Please, enter the car number of the patient in XXX-000 format",
            false,
            HORIZONTAL_MARGIN,
            addPatientFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE
        );

        String doctors[] = hospitalStaffList.getStaffNames();
        RadioButtonPanel doctorAssigned = new RadioButtonPanel(
            "A doctor(s) is(are) assigned to this patient",
            doctors,
            false,
            HORIZONTAL_MARGIN,
            addPatientFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE
        );
        
        String[] equipment = getEquipmentNames();
        RadioButtonPanel equipmentNeeded = new RadioButtonPanel(
            "Additional equipment is needed for this patient",
            equipment,
            false,
            HORIZONTAL_MARGIN,
            addPatientFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE
        );

        BedSelectionPanel bedSelectionPanel = new BedSelectionPanel(
            MAIN_FIELDS_SIZE,
            "Please, select the bed for the patient: ",
            HORIZONTAL_MARGIN / 2,
            HORIZONTAL_MARGIN,
            hospitalFloorList
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            MAIN_FIELDS_SIZE,
            e -> {addPatientFrame.dispose();},
            e -> {
                try {
                    patientList.addPatient(new Patient(
                        namePanel.getText().equals("Please, enter the full name of the patient") ?
                            null :
                            namePanel.getText(),
                        dateOfBirthPanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        idPanel.getText(),
                        telephonePanel.getText(),
                        emailPanel.getText().equals("Please, enter the email of the patient") ?
                            null :
                            emailPanel.getText(),
                        genderPanel.getSelectedIndex() == 0,
                        ownsCarPanel.isSelected(),
                        ownsCarPanel.getText(),
                        diagnosisPanel.getText().equals("Please, enter the diagnosis of the patient") ?
                            null :
                            diagnosisPanel.getText(),
                        hospitalStaffList.get(doctorAssigned.getSelectedIndex()),
                        bedSelectionPanel.getSelectedRoom(hospitalFloorList),
                        bedSelectionPanel.getSelectedBed(hospitalFloorList),
                        equipmentList.getFirstUnused(equipmentNeeded.getSelectedItem())
                    ));
                } catch (IllegalArgumentException exception) {
                    JOptionPane.showMessageDialog(addPatientFrame, exception.getMessage());
                    return;
                }
                addPatientFrame.dispose();
            }
        );

        namePanel.setNextComponent(idPanel);
        idPanel.setNextComponent(telephonePanel);
        telephonePanel.setNextComponent(emailPanel);
        emailPanel.setNextComponent(diagnosisPanel);

        JPanel elementsToDisplay[] = {
            titlePanel,
            namePanel,
            idPanel,
            telephonePanel,
            emailPanel,
            diagnosisPanel,
            ownsCarPanel,
            doctorAssigned,
            equipmentNeeded,
            genderPanel,
            dateOfBirthPanel,
            bedSelectionPanel
        };

        for (JPanel panel : elementsToDisplay) {
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            addPatientPanel.add(Box.createVerticalStrut(VERTICAL_MARGIN));
            addPatientPanel.add(panel);
        }

        JScrollPane scrollPane = new JScrollPane(addPatientPanel);
        addPatientFrame.add(scrollPane);
        addPatientFrame.add(navigationPanel, BorderLayout.SOUTH);
        addPatientFrame.setVisible(true);
    }

    /**
     * Returns the names of all equipment items.
     *
     * @return an array of equipment item names
     */
    private String[] getEquipmentNames() {
        List<String> equipmentNames = equipmentList.getNames();
        return equipmentNames.toArray(new String[equipmentNames.size()]);
    }
}
