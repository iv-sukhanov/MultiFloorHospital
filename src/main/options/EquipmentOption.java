package main.options;

import java.awt.BorderLayout;
import java.awt.Component;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import main.entities.HospitalEquipmentList;
import main.entities.HospitalEquipment;
import main.gui.*;

/**
 * Represents the equipment option in the hospital management system.
 */
public class EquipmentOption extends Option {

    private HospitalEquipmentList equipmentList;

    /**
     * Constructs an EquipmentOption object.
     *
     * @param equipmentList the list of hospital equipment
     */
    public EquipmentOption(HospitalEquipmentList equipmentList) {
        super(
            "EquipmentOption", 
            new JButton("Equipment")
        );
        this.equipmentList = equipmentList;
    }

    /**
     * Executes the equipment option.
     *
     * @param frame the main frame
     */
    public void execute(JFrame frame) {
        listEquipment(frame);
    }

    /**
     * Displays the list of equipment.
     *
     * @param mainFrame the main frame
     */
    private void listEquipment(JFrame mainFrame) {

        DependantFrame listEquipmentFrame = new DependantFrame(
            mainFrame, 
            "Hospital Equipment", 
            new BorderLayout()
        ); 


        DefaultListModel<String> listModel = new DefaultListModel<>();
        fillModel(listModel);
        JList<String> mainList = new JList<>(listModel);
        mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(mainList);

        String[] columnNames = {"#", "Name", "Manufacturer", "Last Maintenance Date", "Is available"};
        Object[][] emptyData = {};
        JTable detailsTable = new JTable(emptyData, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(detailsTable);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, tableScrollPane);
        splitPane.setDividerLocation((int)(mainFrame.getWidth() / 4));
        
        listEquipmentFrame.add(splitPane, BorderLayout.CENTER);
        
        mainList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedOption = mainList.getSelectedValue();
                    if (equipmentList.containsKey(selectedOption)) {
                        String[][] data = equipmentList.getDetails(selectedOption);
                        detailsTable.setModel(new DefaultTableModel(data, columnNames));
                    }
                }
            }
        });
        
        NavigationButtonsPanel buttonPanel = new NavigationButtonsPanel(
            MAIN_FIELDS_SIZE, 
            e -> {listEquipmentFrame.dispose();}, 
            e -> {
                listEquipmentFrame.dispose();
                addEquipment(mainFrame);
                super.button.doClick();
            },
            e -> {
                String selectedOption = mainList.getSelectedValue();
                int selectedRow = detailsTable.getSelectedRow();
                if (selectedOption != null && selectedRow != -1) {
                    int confirmed = JOptionPane.showConfirmDialog(listEquipmentFrame, 
                        "Are you sure you want to delete " + selectedRow + "th element?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION);
    
                    if (confirmed == JOptionPane.YES_OPTION) {
                        equipmentList.remove(selectedOption, selectedRow);
                        listEquipmentFrame.dispose();
                        super.button.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(listEquipmentFrame, "No item selected to delete.");
                }
            }
        );
        listEquipmentFrame.add(buttonPanel, BorderLayout.SOUTH);

        listEquipmentFrame.setVisible(true);
    }

    /**
     * Displays the add equipment form.
     *
     * @param mainFrame the main frame
     */
    private void addEquipment(JFrame mainFrame) {
        DependantFrame equipmentFrame = new DependantFrame(mainFrame, "Add Equipment");

        LabelPanel promptPanel = new LabelPanel(
            "Please enter the following information:",
            MAIN_FIELDS_SIZE
        );

        CenteredTextFieldPanel namePanel = new CenteredTextFieldPanel(
            new HintTextField(
                "Enter the name of the equipment",
                MAIN_FIELDS_SIZE
            ), 
            HORIZONTAL_MARGIN, 
            MAIN_FIELDS_SIZE
        );

        CenteredTextFieldPanel manufacturerPanel = new CenteredTextFieldPanel(
            new HintTextField(
                "Enter the manufacturer of the equipment",
                MAIN_FIELDS_SIZE
            ), 
            HORIZONTAL_MARGIN, 
            MAIN_FIELDS_SIZE
        );

        RadioButtonPanel radioPanel = new RadioButtonPanel(
            "The equipment is available", 
            true,
            HORIZONTAL_MARGIN / 2,
            MAIN_FIELDS_SIZE
        );

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        DateSpinnerPanel datePanel = new DateSpinnerPanel(
            "Enter the last maintenance date:",
            MAIN_FIELDS_SIZE,
            HORIZONTAL_MARGIN,
            null,
            calendar.getTime()
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            MAIN_FIELDS_SIZE, 
            e -> {equipmentFrame.dispose();}, 
            e -> {
                if (namePanel.getText().equals("Enter the name of the equipment")) {
                    JOptionPane.showMessageDialog(equipmentFrame, "Please enter the name of the equipment.");
                    return;
                }

                equipmentList.add(new HospitalEquipment(
                    namePanel.getText(),
                    (
                        manufacturerPanel.getText().equals("Enter the manufacturer of the equipment") ? 
                        "Unknown" : 
                        manufacturerPanel.getText()
                    ), 
                    radioPanel.isSelected(), 
                    datePanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    equipmentList
                ));
                equipmentFrame.dispose();
            }
        );

        namePanel.setNextComponent(manufacturerPanel);
        JPanel elementsToDisplay[] = {
            promptPanel,
            namePanel,
            manufacturerPanel,
            radioPanel,
            datePanel,
            new FillerPannel(),
            navigationPanel
        };

        for (JPanel panel : elementsToDisplay) {
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            equipmentFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN));
            equipmentFrame.add(panel);
        }      

        equipmentFrame.setVisible(true);
    }
    
    /**
     * Fills the list model with equipment names.
     *
     * @param listModel the list model to fill
     */
    private void fillModel(DefaultListModel<String> listModel) {
        List<String> names = equipmentList.getNames();
        for (String name : names) {
            listModel.addElement(name);
        }
    }

}
