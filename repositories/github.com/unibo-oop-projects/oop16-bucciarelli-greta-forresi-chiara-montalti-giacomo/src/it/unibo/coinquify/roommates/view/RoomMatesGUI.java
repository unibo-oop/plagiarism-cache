package it.unibo.coinquify.roommates.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.naming.OperationNotSupportedException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;

import it.unibo.coinquify.home.Home;
import it.unibo.coinquify.home.Room;
import it.unibo.coinquify.roommates.controller.RoomMatesController;
import it.unibo.coinquify.roommates.controller.RoomMatesControllerImpl;
import it.unibo.coinquify.roommates.model.RoomMate;
import it.unibo.coinquify.roommates.model.RoomMateImpl;
import it.unibo.coinquify.telephonebook.model.Contact;
import it.unibo.coinquify.utils.ButtonColumn;
import it.unibo.coinquify.utils.Constants;
import it.unibo.coinquify.utils.JTextFieldLimit;
import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.PaneGUI;
import it.unibo.coinquify.utils.PhoneNumberPresentException;
import it.unibo.coinquify.utils.PhoneUtils;
import it.unibo.coinquify.utils.UtilsGUI;

/**
 * Room mates gui implementation.
 */
public class RoomMatesGUI implements PaneGUI {

    private static final int FIELDS = 6;
    private static final int MODIFY_POSITION = FIELDS;
    private static final int DELETE_POSITION = FIELDS + 1;

    private final JPanel panel;
    private final List<RoomMate> roomMates;
    private final RoomMatesController controller;
    private final JXTable table;
    private final DefaultTableModel tableModel;
    private final JFrame mainFrame;

    /**
     * 
     * @param home
     *            instance
     * @param contacts
     *            to control, if there're contact duplicate
     * @param mainFrame
     *            of home gui
     */
    public RoomMatesGUI(final Home home, final List<Contact> contacts, final JFrame mainFrame) {
        this.mainFrame = mainFrame;

        final String[] col = { Messages.getMessages().getString("NAME"), Messages.getMessages().getString("SURNAME"),
                Messages.getMessages().getString("FISCAL_CODE"), Messages.getMessages().getString("ROOM"),
                Messages.getMessages().getString("BIRTHDAY"), Messages.getMessages().getString("PHONE_NUMBER"),
                Messages.getMessages().getString("UPDATE"), Messages.getMessages().getString("DELETE") };
        // The 0 argument is number rows.
        this.tableModel = new DefaultTableModel(col, 0) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return column == MODIFY_POSITION || column == DELETE_POSITION;
            }
        };

        this.table = new JXTable(tableModel);
        this.panel = new JPanel(new BorderLayout());
        this.roomMates = new ArrayList<>(home.getRoomMates());
        this.controller = new RoomMatesControllerImpl(home, contacts);
        this.build();
    }

    /**
     * 
     */
    private void build() {
        this.displayTable();
        final JButton addBtn = new JButton(Messages.getMessages().getString("ADD_ROOM_MATE"));
        this.panel.add(addBtn, BorderLayout.SOUTH);
        addBtn.addActionListener(e -> {
            try {
                this.addRoomMateFrame(Optional.empty());
            } catch (OperationNotSupportedException e1) {
                JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("WARNING_MAX_ROOM_MATES"));
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("ERROR"));
            }
        });
    }

    private void addRoomMateFrame(final Optional<RoomMate> roomMate)
            throws OperationNotSupportedException, ParseException {

        if (this.roomMates.size() > Constants.MAX_ROOM_MATES) {
            throw new OperationNotSupportedException();
        }
        final JFrame addRoomMateFrame = new JFrame();
        final JPanel addRoomMatePanel = new JPanel();
        addRoomMatePanel.setLayout(new GridLayout(0, Constants.COMPONENTS_FOR_ROW, 0, 0));
        addRoomMateFrame.add(addRoomMatePanel);

        final JTextField nameF = new JTextField(5);
        if (roomMate.isPresent()) {
            nameF.setText(roomMate.get().getName());
        }
        addRoomMatePanel.add(new JLabel(Messages.getMessages().getString("NAME")));
        addRoomMatePanel.add(nameF);

        final JTextField surnameF = new JTextField(5);
        if (roomMate.isPresent()) {
            surnameF.setText(roomMate.get().getSurname());
        }
        addRoomMatePanel.add(new JLabel(Messages.getMessages().getString("SURNAME")));
        addRoomMatePanel.add(surnameF);

        final JTextField fiscalCodeF = new JTextField(Constants.FISCALCODE_LENGTH);
        fiscalCodeF.setDocument(new JTextFieldLimit(Constants.FISCALCODE_LENGTH));

        if (roomMate.isPresent()) {
            fiscalCodeF.setText(roomMate.get().getFiscalCode());
        }
        addRoomMatePanel.add(new JLabel(Messages.getMessages().getString("FISCAL_CODE")));
        addRoomMatePanel.add(fiscalCodeF);

        final JComboBox<Room> roomCb = new JComboBox<>(Room.values());
        if (roomMate.isPresent()) {
            roomCb.setSelectedItem(roomMate.get().getRoom());
        }
        addRoomMatePanel.add(new JLabel(Messages.getMessages().getString("ROOM")));
        addRoomMatePanel.add(roomCb);

        final JTextField phoneNumberF = PhoneUtils.getPhoneNumberJTextField();

        if (roomMate.isPresent()) {
            phoneNumberF.setText(roomMate.get().getPhoneNumber());
        }
        addRoomMatePanel.add(new JLabel(Messages.getMessages().getString("PHONE_NUMBER")));
        addRoomMatePanel.add(phoneNumberF);

        final JXDatePicker picker = new JXDatePicker();
        picker.setLocale(Messages.getMessages().getCurrentLocale());
        picker.getEditor().setText(Messages.getMessages().getString("NEW_LINE"));
        if (!roomMate.isPresent()) {
            picker.setDate(Calendar.getInstance().getTime());
        } else {
            picker.setDate(roomMate.get().getBirthDay());
        }
        addRoomMatePanel.add(new JLabel(Messages.getMessages().getString("BIRTHDAY")));
        addRoomMatePanel.add(picker);
        final JButton addBtn = new JButton(Messages.getMessages().getString("SAVE"));
        addRoomMateFrame.add(addBtn, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            final RoomMate rm = new RoomMateImpl(nameF.getText(), surnameF.getText(),
                    fiscalCodeF.getText().toUpperCase(), (Room) roomCb.getSelectedItem(), phoneNumberF.getText(),
                    picker.getDate());
            try {
                if (roomMate.isPresent()) {
                    this.controller.update(roomMate.get(), rm);
                    this.refresh();
                } else {
                    this.controller.add(rm);
                    this.roomMates.add(this.roomMates.size(), rm);
                    this.addRoomMateRow(rm);
                    this.mainFrame.pack();
                }
            } catch (IllegalArgumentException e1) {
                JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("ERROR_IN_ROOM_MATE_ARGS"));
                return;
            } catch (PhoneNumberPresentException e1) {
                JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("NUMBER_ALREADY_PRESENT"));
            }
            addRoomMateFrame.dispose();
        });
        UtilsGUI.finishFrame(addRoomMateFrame);
    }

    private void refresh() {
        // Remove all table rows
        for (int i = this.tableModel.getRowCount() - 1; i >= 0; i--) {
            this.tableModel.removeRow(i);
        }
        addAllRoomMatesToTable();
    }

    private void displayTable() {
        addAllRoomMatesToTable();
        this.panel.add(table, BorderLayout.CENTER);
        // for columns name view
        this.panel.add(table.getTableHeader(), BorderLayout.NORTH);
    }

    /**
     * 
     */
    private void addAllRoomMatesToTable() {
        for (int i = 0; i < this.roomMates.size(); i++) {
            addRoomMateRow(this.roomMates.get(i));
        }
        table.packAll();
    }

    /**
     * @param rm
     *            room mate to add
     */
    private void addRoomMateRow(final RoomMate rm) {
        final Object[] objs = { rm.getName(), rm.getSurname(), rm.getFiscalCode(), rm.getRoom(),
                DateFormat.getDateInstance(DateFormat.SHORT).format(rm.getBirthDay()), rm.getPhoneNumber(),
                Messages.getMessages().getString("UPDATE"), Messages.getMessages().getString("DELETE") };

        final Action modify = new AbstractAction() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            public void actionPerformed(final ActionEvent e) {
                try {
                    addRoomMateFrame(Optional.of(roomMates.get(Integer.valueOf(e.getActionCommand()))));
                } catch (OperationNotSupportedException e1) {
                    JOptionPane.showMessageDialog(panel, Messages.getMessages().getString("WARNING_MAX_ROOM_MATES"));
                    return;
                } catch (ParseException | NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel, Messages.getMessages().getString("ERROR"));
                }
            }
        };

        final ButtonColumn buttonColumnModify = new ButtonColumn(this.table, modify, MODIFY_POSITION);
        buttonColumnModify.setMnemonic(KeyEvent.VK_D);

        final Action delete = new AbstractAction() {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            public void actionPerformed(final ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, Messages.getMessages().getString("SURE_TO_DELETE_ROOM_MATE"),
                        Messages.getMessages().getString("WARNING"),
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    try {
                        controller.remove(roomMates.get(modelRow));
                        JXTable table = (JXTable) e.getSource();
                        roomMates.remove(modelRow);
                        ((DefaultTableModel) table.getModel()).removeRow(modelRow);
                    } catch (OperationNotSupportedException e1) {
                        JOptionPane.showMessageDialog(panel,
                                Messages.getMessages().getString("NOT_SUPPORTED_OPERATION"));
                    } catch (IllegalArgumentException e2) {
                        JOptionPane.showMessageDialog(panel, Messages.getMessages().getString("CANT_DELETE_ALL"));
                        return;
                    }
                    mainFrame.pack();
                }
            }
        };

        final ButtonColumn buttonColumnDelete = new ButtonColumn(this.table, delete, DELETE_POSITION);
        buttonColumnDelete.setMnemonic(KeyEvent.VK_D);
        this.tableModel.addRow(objs);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public String getName() {
        return Messages.getMessages().getString("ROOM_MATES");
    }

}
