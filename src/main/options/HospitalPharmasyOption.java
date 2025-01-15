package main.options;

import java.awt.BorderLayout;
import java.awt.Component;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.*;

import main.entities.HospitalPharmasy;
import main.entities.HospitalPharmasyList;
import main.gui.CenteredTextFieldPanel;
import main.gui.LabelPanel;
import main.gui.DateSpinnerPanel;
import main.gui.DependantFrame;
import main.gui.FillerPannel;
import main.gui.HintTextField;
import main.gui.NavigationButtonsPanel;

/**
 * Represents the pharmacy option in the hospital management system.
 */
public class HospitalPharmasyOption extends Option {

    private final HospitalPharmasyList pharmasyList;

    /**
     * Constructs a HospitalPharmasyOption object.
     *
     * @param pharmasyList the list of pharmacy items
     */
    public HospitalPharmasyOption(HospitalPharmasyList pharmasyList) {
        super("Pharmasy", new JButton("Pharmasy"));
        this.pharmasyList = pharmasyList;
    }

    /**
     * Executes the pharmacy option.
     *
     * @param frame the main frame
     */
    public void execute(JFrame frame) {
        listPharmasy(frame);
    }

    /**
     * Displays the list of pharmacy items.
     *
     * @param mainFrame the main frame
     */
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
            MAIN_FIELDS_SIZE, 
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

    /**
     * Displays the add pharmacy item form.
     *
     * @param mainFrame the main frame
     */
    private void addPharmasy(JFrame mainFrame) {
        DependantFrame addPharmasyFrame = new DependantFrame(mainFrame, "Add Pharmasy");

        LabelPanel promptPanel = new LabelPanel("Please enter the following information:", MAIN_FIELDS_SIZE);

        CenteredTextFieldPanel namePanel = new CenteredTextFieldPanel(
            new HintTextField("Enter the name of the item", MAIN_FIELDS_SIZE), 
            HORIZONTAL_MARGIN, 
            mainFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE,
            "Name: "
        );

        CenteredTextFieldPanel pricePanel = new CenteredTextFieldPanel(
            new HintTextField("Enter the price of the item", MAIN_FIELDS_SIZE), 
            HORIZONTAL_MARGIN,
            mainFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE,
            "Price: "
        );

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        DateSpinnerPanel datePanel = new DateSpinnerPanel(
            "Enter the expiration date:",
            MAIN_FIELDS_SIZE,
            HORIZONTAL_MARGIN,
            calendar.getTime(),
            null
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            MAIN_FIELDS_SIZE, 
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
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addPharmasyFrame, "Please enter a valid price.");
                    return;
                } catch (IllegalArgumentException ex) {
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
    
    /**
     * Fills the list model with pharmacy item names.
     *
     * @param model the list model to fill
     */
    private void fillModel(DefaultListModel<String> model) {
        for (String pharmasy : pharmasyList.getPharmasyNames()) {
            model.addElement(pharmasy);
        }
    }
}
