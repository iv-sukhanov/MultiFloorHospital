package main.options;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import main.entities.HospitalEquipmentList;
import main.entities.HospitalEquipment;
import main.gui.CenteredElementPanel;
import main.gui.LabelPanel;
import main.gui.DateSpinnerPanel;
import main.gui.DependantFrame;
import main.gui.FillerPannel;
import main.gui.HintTextField;
import main.gui.NavigationButtonsPanel;
import main.gui.RadioButtonPanel;

public class EquipmentOption extends Option {

    private HospitalEquipmentList equipmentList;

    public EquipmentOption(HospitalEquipmentList equipmentList) {
        super(
            "EquipmentOption", 
            new JButton("Equipment")
        );
        this.equipmentList = equipmentList;
    }

    public void execute(JFrame frame) {
        frame.setVisible(false);
        listEquipment(frame);
        frame.setVisible(true);
    }

    private void listEquipment(JFrame mainFrame) {

        Dimension mainFrameSize = mainFrame.getSize();

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
        splitPane.setDividerLocation((int)(mainFrameSize.getWidth() / 4));
        
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
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT), 
            e -> {listEquipmentFrame.dispose();}, 
            e -> {
                    listEquipmentFrame.dispose();
                    addEquepment(mainFrame);
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

    private void addEquepment(JFrame mainFrame) {

        DependantFrame equipmentFrame = new DependantFrame(
            mainFrame, 
            "Add Equipment" 
        );

        LabelPanel promptPanel = new LabelPanel(
            "Please enter the following information:",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        CenteredElementPanel namePanel = new CenteredElementPanel(
            new HintTextField(
                "Enter the name of the equipment",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ), 
            HORIZONTAL_MARGIN, 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        CenteredElementPanel manufacturerPanel = new CenteredElementPanel(
            new HintTextField(
                "Enter the manufacturer of the equipment",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ), 
            HORIZONTAL_MARGIN, 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        RadioButtonPanel radioPanel = new RadioButtonPanel(
            "The equipment is available", 
            true,
            HORIZONTAL_MARGIN / 2,
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        DateSpinnerPanel datePanel = new DateSpinnerPanel(
            "Enter the last maintenance date:",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            HORIZONTAL_MARGIN,
            null,
            calendar.getTime()
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT), 
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

        equipmentFrame.setLocationRelativeTo(mainFrame);
        //TODO transfer focus from the text fields
        equipmentFrame.setVisible(true);
    }
    
    private void fillModel(DefaultListModel<String> listModel) {
        List<String> names = equipmentList.getNames();
        for (String name : names) {
            listModel.addElement(name);
        }
    }

}
