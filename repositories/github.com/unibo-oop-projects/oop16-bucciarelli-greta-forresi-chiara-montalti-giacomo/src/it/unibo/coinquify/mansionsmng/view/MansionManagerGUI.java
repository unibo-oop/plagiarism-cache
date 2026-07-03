package it.unibo.coinquify.mansionsmng.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.naming.OperationNotSupportedException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.coinquify.mansionsmng.controller.MansionManagerControllerImpl;
import it.unibo.coinquify.mansionsmng.controller.MansionMangerController;
import it.unibo.coinquify.mansionsmng.model.Mansion;
import it.unibo.coinquify.mansionsmng.model.MansionImpl;
import it.unibo.coinquify.mansionsmng.model.MansionType;
import it.unibo.coinquify.roommates.model.RoomMate;
import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.PaneGUI;
import it.unibo.coinquify.utils.TimeImpl;
import it.unibo.coinquify.utils.UtilsGUI;
import lu.tudor.santec.jtimechooser.JTimeChooser;

/**
 * GUI of mansion manager.
 */
public class MansionManagerGUI implements PaneGUI {
    private static final String EQUAL = Messages.getMessages().getString("EQUAL_SEPARATOR");
    private static final int FIELDS = 6;
    private final JPanel centralPanel;
    private final JPanel mainPanel;
    private final Set<RoomMate> roomMates;
    private final MansionMangerController controller;
    private final Map<DayOfWeek, JPanel> panelDay;
    private final JFrame mainFrame;

    /**
     * Construct a new mansion manager GUI.
     * 
     * @param roomMates
     *            set
     * @param mainFrame
     *            of home
     */
    public MansionManagerGUI(final Set<RoomMate> roomMates, final JFrame mainFrame) {
        this.panelDay = new HashMap<>();
        this.mainFrame = mainFrame;
        this.roomMates = roomMates;
        this.controller = new MansionManagerControllerImpl(roomMates);
        this.mainPanel = new JPanel(new BorderLayout());
        this.centralPanel = new JPanel();
        this.build();
    }

    /**
     * 
     */
    private void build() {
        centralPanel.setLayout(new GridLayout(0, DayOfWeek.values().length, 0, 0));
        mainPanel.add(this.centralPanel, BorderLayout.CENTER);
        final JButton addMansion = new JButton(Messages.getMessages().getString("ADD_MANSION"));
        addMansion.setSize(addMansion.getPreferredSize());
        addMansion.addActionListener(e -> this.addMansion());
        this.mainPanel.add(addMansion, BorderLayout.SOUTH);
        this.displayTable();
    }

    private void addMansion() {
        final JFrame addMansionFrame = new JFrame();
        final JPanel addMansionPanel = new JPanel();
        addMansionFrame.add(addMansionPanel);
        addMansionPanel.setLayout(new GridLayout(0, 2, 0, 0));

        addMansionPanel.add(new JLabel(Messages.getMessages().getString("DAY")));
        final JComboBox<Object> dayCb = new JComboBox<>();
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            // locale translation for each value
            dayCb.addItem(DayOfWeek.values()[i]
                    .getDisplayName(TextStyle.FULL, Messages.getMessages().getCurrentLocale()).toUpperCase());
        }
        dayCb.setLocale(Messages.getMessages().getCurrentLocale());
        addMansionPanel.add(dayCb);

        addMansionPanel.add(new JLabel(Messages.getMessages().getString("START_TIME")));
        final JTimeChooser startTimeC = new JTimeChooser();
        startTimeC.setShowSeconds(false);
        addMansionPanel.add(startTimeC);

        addMansionPanel.add(new JLabel(Messages.getMessages().getString("END_TIME")));
        final JTimeChooser endTimeC = new JTimeChooser();
        endTimeC.setShowSeconds(false);
        addMansionPanel.add(endTimeC);

        addMansionPanel.add(new JLabel(Messages.getMessages().getString("MANSION")));
        final JComboBox<Object> activityCb = new JComboBox<>(MansionType.values());
        addMansionPanel.add(activityCb);

        addMansionPanel.add(new JLabel(Messages.getMessages().getString("ROOM_MATE")));
        final JComboBox<Object> roomMateCb = new JComboBox<>(roomMates.toArray());
        addMansionPanel.add(roomMateCb);

        final JButton saveBtn = new JButton(Messages.getMessages().getString("SAVE"));
        saveBtn.addActionListener(e -> {
            try {
                this.addMansionBtn((RoomMate) roomMateCb.getSelectedItem(),
                        this.controller.addMansion(
                                new MansionImpl((MansionType) activityCb.getSelectedItem(),
                                        new TimeImpl(startTimeC.getHours(), startTimeC.getMinutes()),
                                        new TimeImpl(endTimeC.getHours(), endTimeC.getMinutes()),
                                        DayOfWeek.values()[dayCb.getSelectedIndex()]),
                                (RoomMate) roomMateCb.getSelectedItem()));
            } catch (final IllegalArgumentException e1) {
                JOptionPane.showMessageDialog(this.mainPanel,
                        Messages.getMessages().getString("ERROR_IN_MANSION_ARGS"));
            } catch (OperationNotSupportedException e1) {
                JOptionPane.showMessageDialog(this.mainPanel, Messages.getMessages().getString("MANSION_OVERLAP"));
            }
            addMansionFrame.dispose();
        });
        addMansionFrame.add(saveBtn, BorderLayout.SOUTH);

        UtilsGUI.finishFrame(addMansionFrame);
    }

    private void displayTable() {
        for (final DayOfWeek d : DayOfWeek.values()) {
            // translate day of week in italian
            final JLabel label = new JLabel(
                    d.getDisplayName(TextStyle.FULL, Messages.getMessages().getCurrentLocale()).toUpperCase(),
                    SwingConstants.CENTER);
            this.centralPanel.add(label);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        // map day panel, every day has a panel in central panel
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            final JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.PAGE_AXIS));
            panelDay.put(DayOfWeek.values()[i], dayPanel);
            this.centralPanel.add(dayPanel, i + DayOfWeek.values().length);
        }

        for (final RoomMate rm : this.roomMates) {
            for (final Mansion mansion : rm.getMansions()) {
                addMansionBtn(rm, mansion);
            }
        }
    }

    /**
     * @param rm
     * @param mansion
     */
    private void addMansionBtn(final RoomMate rm, final Mansion mansion) {
        final JButton mansionBtn = new JButton(mansion.getButtonText(rm));

        mansionBtn.addActionListener(e -> this.displayMansion(mansion, rm, mansionBtn));
        mansionBtn.setSize(mansionBtn.getPreferredSize());
        this.panelDay.get(mansion.getDayOfWeek()).add(mansionBtn);
        this.mainFrame.pack();
    }

    private void displayMansion(final Mansion mansion, final RoomMate rm, final JButton sourceBtn) {
        final JFrame displayMansionFrame = new JFrame();
        final JPanel displayMansionPanel = new JPanel();
        displayMansionFrame.add(displayMansionPanel);
        displayMansionPanel.setLayout(new GridLayout(FIELDS, 2, 0, 0));

        displayMansionPanel.add(new JLabel(Messages.getMessages().getString("DAY") + EQUAL + mansion.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Messages.getMessages().getCurrentLocale()).toUpperCase()));

        displayMansionPanel
                .add(new JLabel(Messages.getMessages().getString("START_TIME") + EQUAL + mansion.getStartTime()));

        displayMansionPanel
                .add(new JLabel(Messages.getMessages().getString("END_TIME") + EQUAL + mansion.getEndTime()));

        displayMansionPanel.add(new JLabel(Messages.getMessages().getString("MANSION") + EQUAL + mansion.getName()));

        displayMansionPanel.add(new JLabel(Messages.getMessages().getString("ROOM_MATE") + EQUAL + rm.toString()));

        final JButton deleteBtn = new JButton(Messages.getMessages().getString("DELETE"));
        deleteBtn.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, Messages.getMessages().getString("SURE_TO_DELETE_MANSION"),
                    Messages.getMessages().getString("WARNING"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                controller.deleteMansion(mansion, rm);
                this.panelDay.get(mansion.getDayOfWeek()).remove(sourceBtn);
                this.mainFrame.pack();
                displayMansionFrame.dispose();
            }
        });

        displayMansionPanel.add(deleteBtn);
        UtilsGUI.finishFrame(displayMansionFrame);
    }

    /**
     * 
     * @param rm
     *            to delete mansions
     */
    public void deleteMansionOfRoomMates(final RoomMate rm) {
        for (final Mansion mansion : rm.getMansions()) {
            for (final Component component : this.panelDay.get(mansion.getDayOfWeek()).getComponents()) {
                if (((JButton) component).getText().equals(mansion.getButtonText(rm))) {
                    this.panelDay.get(mansion.getDayOfWeek()).remove(component);
                }
            }
        }
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    /**
     * Refresh roomMate old informations, with new informatons.
     * 
     * @param roomMate
     *            old informations
     * @param newRoomMate
     *            new informations
     */
    public void refreshMansionsBtn(final RoomMate roomMate, final RoomMate newRoomMate) {
        for (final Mansion mansion : roomMate.getMansions()) {
            for (final Component component : this.panelDay.get(mansion.getDayOfWeek()).getComponents()) {
                if (((JButton) component).getText().equals(mansion.getButtonText(roomMate))) {
                    ((JButton) component).setText(mansion.getButtonText(newRoomMate));
                }
            }
        }
    }

    @Override
    public String getName() {
        return Messages.getMessages().getString("MANSION_MANAGER");
    }

}
