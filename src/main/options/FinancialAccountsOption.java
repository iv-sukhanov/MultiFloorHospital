package main.options;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.*;

import main.entities.FinancialRecord;
import main.entities.HospitalFinancial_Accounts;
import main.gui.CenteredTextFieldPanel;
import main.gui.LabelPanel;
import main.gui.DateSpinnerPanel;
import main.gui.DependantFrame;
import main.gui.FillerPannel;
import main.gui.HintTextField;
import main.gui.NavigationButtonsPanel;

/**
 * Represents the financial accounts option in the hospital management system.
 */
public class FinancialAccountsOption extends Option {

    private final HospitalFinancial_Accounts accounts;

    /**
     * Constructs a FinancialAccountsOption object.
     *
     * @param accounts the financial accounts of the hospital
     */
    public FinancialAccountsOption(HospitalFinancial_Accounts accounts) {
        super(
            "FinancialAccount", 
            new JButton("Financial Account")
        );

        this.accounts = accounts;
    }

    /**
     * Executes the financial accounts option.
     *
     * @param frame the main frame
     */
    public void execute(JFrame frame) {
        displayAccount(frame);
    }

    /**
     * Displays the financial account details.
     *
     * @param mainFrame the main frame
     */
    private void displayAccount(JFrame mainFrame) {
        DependantFrame accountFrame = new DependantFrame(
            mainFrame,
            "Financial Account",
            new BorderLayout()
        );


        accountFrame.add(new LabelPanel(
            "Current balance is " + accounts.getBalance() + "$" + 
            (accounts.getBalance() < 0 ? ". Ooooouuuups... You are in debt ;(" : ""), 
            MAIN_FIELDS_SIZE,
            FlowLayout.LEFT
        ), BorderLayout.NORTH);

        String[] columns = {
            "Date",
            "From",
            "Amount"
        };

        JTable detailsTable = new JTable(accounts.getDetails(), columns);
        JScrollPane scrollPane = new JScrollPane(detailsTable);
        accountFrame.add(scrollPane, BorderLayout.CENTER);

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            MAIN_FIELDS_SIZE, 
            e -> {accountFrame.dispose();},
            e -> {
                addRecord(mainFrame);
                accountFrame.dispose();
                super.button.doClick();
            },
            e -> {accountFrame.dispose();}
        );
        accountFrame.add(navigationPanel, BorderLayout.SOUTH);

        accountFrame.setVisible(true);
    }

    /**
     * Displays the add financial record form.
     *
     * @param mainFrame the main frame
     */
    private void addRecord(JFrame mainFrame) {

        DependantFrame addRecordFrame = new DependantFrame(
            mainFrame, 
            "Add Financial Record"
        );

        LabelPanel promptPanel = new LabelPanel(
            "Please enter the following information:",
            MAIN_FIELDS_SIZE
        );

        CenteredTextFieldPanel fromPanel = new CenteredTextFieldPanel(
            new HintTextField(
                "Enter the sender/receiver",
                MAIN_FIELDS_SIZE
            ), 
            HORIZONTAL_MARGIN, 
            mainFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE,
            "Sender/Receiver: "
        );

        CenteredTextFieldPanel amountPanel = new CenteredTextFieldPanel(
            new HintTextField(
                "Enter the amount",
                MAIN_FIELDS_SIZE
            ), 
            HORIZONTAL_MARGIN, 
            mainFrame.getWidth(),
            PATIENT_WIDTH_DIVISOR,
            MAIN_FIELDS_SIZE,
            "Amount: "
        );

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        DateSpinnerPanel datePanel = new DateSpinnerPanel(
            "Enter date of the operation",
            MAIN_FIELDS_SIZE,
            HORIZONTAL_MARGIN,
            null,
            calendar.getTime()
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            MAIN_FIELDS_SIZE, 
            e -> {addRecordFrame.dispose();},
            e -> {
                try {
                    accounts.addRecord(
                        new FinancialRecord(
                            fromPanel.getText().equals("Enter the source of the foundation") ? 
                                "Unknown" : 
                                fromPanel.getText(), 
                            Double.parseDouble(amountPanel.getText()), 
                            datePanel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        ));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(addRecordFrame, "Please enter a valid number.");
                    return;
                }
                addRecordFrame.dispose();
            }
        );
        
        fromPanel.setNextComponent(amountPanel);
        JPanel elementsToDisplay[] = {
            promptPanel,
            fromPanel,
            amountPanel,
            datePanel,
            new FillerPannel(),
            navigationPanel
        };

        for (JPanel panel : elementsToDisplay) {
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            addRecordFrame.add(Box.createVerticalStrut(VERTICAL_MARGIN));
            addRecordFrame.add(panel);
        }

        addRecordFrame.setVisible(true);
    }
}
