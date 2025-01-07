package main.options;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import main.entities.HospitalFloorList;
import main.gui.DependantFrame;

public class RoomsOption extends Option {
    private final HospitalFloorList hospitalFloorList;
    
    public RoomsOption(HospitalFloorList hospitalFloorList) {
        super(
            "RoomsOption",
            new JButton("Rooms")
        );
        this.hospitalFloorList = hospitalFloorList;
    }

    public void execute(JFrame frame) {
        frame.setVisible(false);
        listFloors(frame);
        frame.setVisible(true);
    }

    private void listFloors(JFrame frame) {
        DependantFrame listFloorsFrame = new DependantFrame(
            frame, 
            "Rooms", 
            new BorderLayout()
        );

        DefaultListModel<String> floorListModel = new DefaultListModel<>();
        fillFloorModel(floorListModel);
        JList<String> mainList = new JList<>(floorListModel);
        mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainList.setBorder(BorderFactory.createTitledBorder("Floors"));
        JScrollPane listScrollPane = new JScrollPane(mainList);

        DefaultListModel<String> roomsListModel = new DefaultListModel<>();
        JList<String> roomList = new JList<>(roomsListModel);
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.setBorder(BorderFactory.createTitledBorder("Rooms"));
        JScrollPane roomsListScrollPane = new JScrollPane(roomList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, roomsListScrollPane);
        splitPane.setDividerLocation(frame.getSize().width / 6);
        listFloorsFrame.add(splitPane, BorderLayout.CENTER);

        mainList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedFloor = mainList.getSelectedIndex();
                if (selectedFloor != -1) {
                    fillRoomsModel(roomsListModel, selectedFloor);
                }
            }
        });

        roomList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRoom = roomList.getSelectedIndex();
                int selectedFloor = mainList.getSelectedIndex();
                if (selectedRoom != -1) {
                    JOptionPane.showMessageDialog(
                        listFloorsFrame, 
                        hospitalFloorList.get(selectedFloor).getRoom(selectedRoom).getBedsInfo(), 
                        "Room Selection", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });

        listFloorsFrame.setVisible(true);
    }

    private void fillFloorModel(DefaultListModel<String> listModel) {
        for (int i = 0; i < hospitalFloorList.size(); i++) {
            listModel.addElement("Floor " + (i + 1));
        }
    }

    private void fillRoomsModel(DefaultListModel<String> listModel, int floorNumber) {
        listModel.clear();
        listModel.addAll(hospitalFloorList.get(floorNumber).getRoomNames());
    }
    
}
