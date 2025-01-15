package main.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.entities.HospitalBed;
import main.entities.HospitalFloorList;
import main.entities.HospitalRoom;

/**
 * Represents a panel for selecting floor, room and bed in a hospital.
 */
public class BedSelectionPanel extends JPanel {

    private final JComboBox<String> floorComboBox;
    private final JComboBox<String> roomComboBox;
    private final JComboBox<String> bedComboBox;

    /**
     * Constructs a BedSelectionPanel object.
     *
     * @param size the max size of the panel
     * @param prompt the prompt to display
     * @param optionsMargin the margin between options
     * @param horizontalMargin the horizontal margin
     * @param hospitalFloorList the list of hospital floors
     */
    public BedSelectionPanel(Dimension size, String prompt, int optionsMargin, int horizontalMargin, HospitalFloorList hospitalFloorList) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(size);
        JPanel gridPanel = new JPanel(new GridLayout(1, 3, optionsMargin, 0));

        String[] floors = new String[hospitalFloorList.size()];
        for (int i = 0; i < hospitalFloorList.size(); i++) {
            floors[i] = "Floor " + (i + 1);
        }

        this.floorComboBox = new JComboBox<>(floors);
        gridPanel.add(floorComboBox);
        this.roomComboBox = new JComboBox<>();
        gridPanel.add(roomComboBox);
        this.bedComboBox = new JComboBox<>();
        gridPanel.add(bedComboBox);

        floorComboBox.addActionListener(e -> {
            roomComboBox.removeAllItems();
            bedComboBox.removeAllItems();
            int floorIndex = floorComboBox.getSelectedIndex();

            if (floorIndex == -1) {
                return;
            }

            for (String room : hospitalFloorList.get(floorIndex).getAvailableRoomNames()) {
                roomComboBox.addItem(room);
            }
        });

        roomComboBox.addActionListener(e -> {
            bedComboBox.removeAllItems();
            int floorIndex = floorComboBox.getSelectedIndex();
            int roomIndex = roomComboBox.getSelectedIndex();

            if (floorIndex == -1 || roomIndex == -1) {
                return;
            }

            for (String bed : hospitalFloorList.get(floorIndex).getAvailableRoom(roomIndex).getAvailableBeds()) {
                bedComboBox.addItem(bed);
            }
        });
    
        add(Box.createHorizontalStrut(horizontalMargin));
        add(new JLabel(prompt));
        add(Box.createHorizontalStrut(horizontalMargin / 4));
        add(gridPanel);
        add(Box.createHorizontalStrut(horizontalMargin));
    }

    /**
     * Returns the selected bed.
     *
     * @param hospitalFloorList the list of hospital floors
     * @return the selected bed, or null if no bed is selected
     */
    public HospitalBed getSelectedBed(HospitalFloorList hospitalFloorList) {

        int floorIndex = floorComboBox.getSelectedIndex();
        int roomIndex = roomComboBox.getSelectedIndex();
        int bedIndex = bedComboBox.getSelectedIndex();
        
        if (floorIndex == -1 || roomIndex == -1 || bedIndex == -1) {
            return null;
        }

        return hospitalFloorList.get(floorIndex).getAvailableRoom(roomIndex).getAvailableBed(bedIndex);
    }

    /**
     * Returns the selected room.
     *
     * @param hospitalFloorList the list of hospital floors
     * @return the selected room, or null if no room is selected
     */
    public HospitalRoom getSelectedRoom(HospitalFloorList hospitalFloorList) {
        
        int floorIndex = floorComboBox.getSelectedIndex();
        int roomIndex = roomComboBox.getSelectedIndex();
        int bedIndex = bedComboBox.getSelectedIndex();
        
        if (floorIndex == -1 || roomIndex == -1 || bedIndex == -1) {
            return null;
        }
        
        return hospitalFloorList.get(floorIndex).getAvailableRoom(roomIndex);
    }
}
