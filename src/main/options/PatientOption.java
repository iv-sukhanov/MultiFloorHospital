package main.options;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.util.List;

import main.entities.HospitalEquipmentList;
import main.entities.HospitalFloorList;
import main.entities.HospitalStaffList;
import main.entities.Patient;
import main.entities.PatientList;
import main.gui.*;

public class PatientOption extends Option {

    private final HospitalStaffList hospitalStaffList; 
    private final HospitalEquipmentList equipmentList; 
    private final HospitalFloorList hospitalFloorList;
    private final PatientList patientList;
    
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

    public void execute(JFrame frame) {
        frame.setVisible(false);
        listPatients(frame);
        frame.setVisible(true);
    }

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
        JTable staffTable = new JTable(patientsData, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(staffTable);
        listPatientsFrame.add(tableScrollPane, BorderLayout.CENTER);

        NavigationButtonsPanel navigationButtonsPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            e -> {listPatientsFrame.dispose();},
            e -> {
                listPatientsFrame.dispose();
                addPatient(mainFrame);
                super.button.doClick();
            },
            e -> {
                //dont forget to close the patient
                listPatientsFrame.dispose();
            }
        );
        listPatientsFrame.add(navigationButtonsPanel, BorderLayout.SOUTH);
        listPatientsFrame.setVisible(true);
    }

    public void addPatient(JFrame mainFrame) {
        DependantFrame addPatientFrame = new DependantFrame(
            mainFrame, 
            "Add Patient",
            new BorderLayout()
        );

        JPanel addPatientPanel = new JPanel();
        addPatientPanel.setLayout(new BoxLayout(addPatientPanel, BoxLayout.Y_AXIS));

        CenteredLabel titlePanel = new CenteredLabel(
            "Please, enter the following information", 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        CenteredElementPanel namePanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the full name of the patient",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Full name: "
        );

        DateSpinnerPanel dateOfBirthPanel = new DateSpinnerPanel(
            "Please, enter the date of birth of the patient",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            HORIZONTAL_MARGIN
        );

        CenteredElementPanel idPanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the id number of the patient",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "ID nubmer: "
        );

        CenteredElementPanel telephonePanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the phone number of the patient",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Phone nubmer: "
        );

        CenteredElementPanel emailPanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the email of the patient",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Email: "
        );

        CenteredElementPanel diagnosisPanel = new CenteredElementPanel(
            new HintTextField(
                "Please, enter the diagnosis of the patient",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ),
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Diagnosis: "
        );

        String options[] = {"Male", "Female"};
        ComboBoxPanel genderPanel = new ComboBoxPanel(
            "Please, select the gender of the patient",
            options,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            HORIZONTAL_MARGIN
        );

        RadioButtonPanel ownsCarPanel = new RadioButtonPanel(
            "Owns a car",
            "Please, enter the car number of the patient in XXX-000 format",
            false,
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        String doctors[] = hospitalStaffList.getStaffNames();
        RadioButtonPanel doctorAssigned = new RadioButtonPanel(
            "A doctor(s) is(are) assigned to this patient",
            doctors,
            false,
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );
        
        String[] equipment = getEquipmentNames();
        RadioButtonPanel equipmentNeeded = new RadioButtonPanel(
            "Additional equipment is needed for this patient",
            equipment,
            false,
            HORIZONTAL_MARGIN,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        BedSelectionPanel bedSelectionPanel = new BedSelectionPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            "Please, select the bed for the patient: ",
            HORIZONTAL_MARGIN / 2,
            HORIZONTAL_MARGIN,
            hospitalFloorList
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            e -> {addPatientFrame.dispose();},
            e -> {
                patientList.addPatient(new Patient(
                    namePanel.getText(),
                    dateOfBirthPanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    idPanel.getText(),
                    telephonePanel.getText(),
                    emailPanel.getText(),
                    genderPanel.getSelectedIndex() == 0,
                    ownsCarPanel.isSelected(),
                    ownsCarPanel.getText(),
                    diagnosisPanel.getText(),
                    hospitalStaffList.get(doctorAssigned.getSelectedIndex()),
                    bedSelectionPanel.getSelectedRoom(hospitalFloorList),
                    bedSelectionPanel.getSelectedBed(hospitalFloorList),
                    equipmentList.getFirstUnused(equipmentNeeded.getSelectedItem())
                ));
                addPatientFrame.dispose();
            }
        );

        JPanel elementsToDisplay[] = {
            titlePanel,
            namePanel,
            dateOfBirthPanel,
            idPanel,
            telephonePanel,
            emailPanel,
            ownsCarPanel,
            genderPanel,
            doctorAssigned,
            diagnosisPanel,
            equipmentNeeded,
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

    private String[] getEquipmentNames() {
        List<String> equipmentNames = equipmentList.getNames();
        return equipmentNames.toArray(new String[equipmentNames.size()]);
    }
}
