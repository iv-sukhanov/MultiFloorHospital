package main.options;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import main.entities.HospitalPharmasy;
import main.entities.HospitalPharmasyList;
import main.gui.CenteredTextFieldPanel;
import main.gui.LabelPanel;
import main.gui.DateSpinnerPanel;
import main.gui.DependantFrame;
import main.gui.FillerPannel;
import main.gui.HintTextField;
import main.gui.NavigationButtonsPanel;

public class HospitalPharmasyOption extends Option {

    private final HospitalPharmasyList pharmasyList;

    public HospitalPharmasyOption(HospitalPharmasyList pharmasyList) {
        super(
            "Pharmasy",
            new JButton("Pharmasy")
        );

        this.pharmasyList = pharmasyList;
    }

    public void execute(JFrame frame) {
        listPharmasy(frame);
    }

    private void listPharmasy(JFrame mainFrame) {
        
        DependantFrame listPharmasyFrame = new DependantFrame(
            mainFrame,
            "Hospital Pharmasy",
            new BorderLayout()
        );

        DefaultListModel<String> floorListModel = new DefaultListModel<>();
        fillModel(floorListModel);
        JList<String> mainList = new JList<>(floorListModel);
        mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainList.setBorder(BorderFactory.createTitledBorder("Items in the pharmasy:"));
        JScrollPane listScrollPane = new JScrollPane(mainList);
        listPharmasyFrame.add(listScrollPane, BorderLayout.CENTER);

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT), 
            e -> {listPharmasyFrame.dispose();}, 
            e -> {
                listPharmasyFrame.dispose();
                addPharmasy(mainFrame);
                super.button.doClick();
            },
            e -> {
                String selection = mainList.getSelectedValue();
                    if (selection != null) {
                        int confirmed = JOptionPane.showConfirmDialog(listPharmasyFrame, 
                            "Are you sure you want to delete " + selection + " ?",
                            "Delete Confirmation",
                            JOptionPane.YES_NO_OPTION);
        
                        if (confirmed == JOptionPane.YES_OPTION) {
                            pharmasyList.remove(selection);
                            listPharmasyFrame.dispose();
                            super.button.doClick();
                        }
                    } else {
                        JOptionPane.showMessageDialog(listPharmasyFrame, "No item selected to delete.");
                    }
            },
            e -> {
                String selection = mainList.getSelectedValue();
                if (selection != null) {
                    String[] column = {"#", "Name", "Price", "Expiration Date"};
                    JTable detailsTable = new JTable(pharmasyList.getPharmasyDetails(selection), column);
                    JScrollPane scrollPane = new JScrollPane(detailsTable);
                    JOptionPane.showConfirmDialog(
                        null,
                        scrollPane,    
                        "Details:", 
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(listPharmasyFrame, "No item selected for details.");
                }
            }
        );
        listPharmasyFrame.add(navigationPanel, BorderLayout.SOUTH);



        listPharmasyFrame.setVisible(true);
    }

    private void addPharmasy(JFrame mainFrame) {
        DependantFrame addPharmasyFrame = new DependantFrame(
            mainFrame,
            "Add Pharmasy"
        );

        LabelPanel promptPanel = new LabelPanel(
            "Please enter the following information:",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        CenteredTextFieldPanel namePanel = new CenteredTextFieldPanel(
            new HintTextField(
                "Enter the name of the item",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ), 
            HORIZONTAL_MARGIN, 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        CenteredTextFieldPanel pricePanel = new CenteredTextFieldPanel(
            new HintTextField(
                "Enter the price of the item",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ), 
            HORIZONTAL_MARGIN, 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        DateSpinnerPanel datePanel = new DateSpinnerPanel(
            "Enter the expiration date:",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            HORIZONTAL_MARGIN,
            calendar.getTime(),
            null
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT), 
            e -> {addPharmasyFrame.dispose();}, 
            e -> {
                try {
                    pharmasyList.addPharmasy(
                        new HospitalPharmasy(
                            pharmasyList,
                            namePanel.getText().equals("Enter the name of the item") ?
                                null :
                                namePanel.getText(),
                            Double.parseDouble(pricePanel.getText()),
                            datePanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        )
                    );
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addPharmasyFrame, "Please enter a valid price.");
                    return;
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(addPharmasyFrame, ex.getMessage());
                    return;
                }
                addPharmasyFrame.dispose();
            }
        );

        namePanel.setNextComponent(pricePanel);
        JPanel elementsToDisplay[] = {
            promptPanel,
            namePanel,
            pricePanel,
            datePanel,
            new FillerPannel(),
            navigationPanel
        };

        for (JPanel panel : elementsToDisplay) {
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            addPharmasyFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN));
            addPharmasyFrame.add(panel);
        }

        addPharmasyFrame.setVisible(true);
    }
    
    private void fillModel(DefaultListModel<String> model) {
        for (String pharmasy : pharmasyList.getPharmasyNames()) {
            model.addElement(pharmasy);
        }
    }
}
