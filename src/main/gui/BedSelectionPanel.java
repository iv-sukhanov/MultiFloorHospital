package main.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.entities.HospitalFloorList;

public class BedSelectionPanel extends JPanel {
    public BedSelectionPanel(Dimension size, String prompt, int optionsMargin, int horizontalMargin, HospitalFloorList hospitalFloorList) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);
        JPanel gridPanel = new JPanel(new GridLayout(1, 3, optionsMargin, 0));

        String[] floors = new String[hospitalFloorList.size()];
        for (int i = 0; i < hospitalFloorList.size(); i++) {
            floors[i] = "Floor " + (i + 1);
        }

        JComboBox<String> floorComboBox = new JComboBox<>(floors);
        gridPanel.add(floorComboBox);
        JComboBox<String> roomComboBox = new JComboBox<>();
        gridPanel.add(roomComboBox);
        JComboBox<String> bedComboBox = new JComboBox<>();
        gridPanel.add(bedComboBox);

        floorComboBox.addActionListener(e -> {
            roomComboBox.removeAllItems();
            bedComboBox.removeAllItems();
            int floorIndex = floorComboBox.getSelectedIndex();
            for (String room : hospitalFloorList.get(floorIndex).getAvailableRoomNames()) {
                roomComboBox.addItem(room);
            }
        });

        roomComboBox.addActionListener(e -> {
            bedComboBox.removeAllItems();
            int floorIndex = floorComboBox.getSelectedIndex();
            int roomIndex = roomComboBox.getSelectedIndex();
            for (String bed : hospitalFloorList.get(floorIndex).getRoom(roomIndex).getAvailableBeds()) {
                bedComboBox.addItem(bed);
            }
        });
    
        add(Box.createHorizontalStrut(horizontalMargin));
        add(new JLabel(prompt));
        add(Box.createHorizontalStrut(horizontalMargin / 4));
        add(gridPanel);
        add(Box.createHorizontalStrut(horizontalMargin));
    }
}
