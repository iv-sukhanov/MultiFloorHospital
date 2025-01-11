package main.options;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.*;

import main.entities.FinancialRecord;
import main.entities.HospitalFinancial_Accounts;
import main.gui.CenteredElementPanel;
import main.gui.LabelPanel;
import main.gui.DateSpinnerPanel;
import main.gui.DependantFrame;
import main.gui.FillerPannel;
import main.gui.HintTextField;
import main.gui.NavigationButtonsPanel;

public class FinancialAccountsOption extends Option {

    private final HospitalFinancial_Accounts accounts;

    public FinancialAccountsOption(HospitalFinancial_Accounts accounts) {
        super(
            "FinancialAccount", 
            new JButton("Financial Account")
        );

        this.accounts = accounts;
    }

    public void execute(JFrame frame) {
        displayAccount(frame);
    }

    private void displayAccount(JFrame mainFrame) {
        DependantFrame accountFrame = new DependantFrame(
            mainFrame,
            "Financial Account",
            new BorderLayout()
        );


        accountFrame.add(new LabelPanel(
            "Currnet balance is " + accounts.getBalance() + "$" + 
            (accounts.getBalance() < 0 ? ". Ooooouuuups... You are in debt ;(" : ""), 
            new Dimension(Integer.MAX_VALUE, 30),
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
            new Dimension(Integer.MAX_VALUE, 20), 
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

    private void addRecord(JFrame mainFrame) {

        DependantFrame addRecordFrame = new DependantFrame(
            mainFrame, 
            "Add Financial Record"
        );

        LabelPanel promptPanel = new LabelPanel(
            "Please enter the following information:",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        CenteredElementPanel fromPanel = new CenteredElementPanel(
            new HintTextField(
                "Enter the source of the foundation",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ), 
            HORIZONTAL_MARGIN, 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        CenteredElementPanel amountPanel = new CenteredElementPanel(
            new HintTextField(
                "Enter the amount",
                new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
            ), 
            HORIZONTAL_MARGIN, 
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT)
        );

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        DateSpinnerPanel datePanel = new DateSpinnerPanel(
            "Enter the expiration date:",
            new Dimension(Integer.MAX_VALUE, TEXT_FIELDS_HIGHT),
            HORIZONTAL_MARGIN,
            null,
            calendar.getTime()
        );

        NavigationButtonsPanel navigationPanel = new NavigationButtonsPanel(
            new Dimension(Integer.MAX_VALUE, 30), 
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
