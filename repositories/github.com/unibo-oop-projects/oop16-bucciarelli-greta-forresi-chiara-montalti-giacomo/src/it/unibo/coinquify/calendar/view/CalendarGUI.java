package it.unibo.coinquify.calendar.view;

import java.util.Calendar;
import java.util.Optional;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;

import it.unibo.coinquify.calendar.controller.CalendarController;
import it.unibo.coinquify.calendar.controller.CalendarControllerImpl;
import it.unibo.coinquify.calendar.model.Event;
import it.unibo.coinquify.calendar.model.EventImpl;
import it.unibo.coinquify.utils.ButtonColumn;
import it.unibo.coinquify.utils.Constants;
import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.PaneGUI;
import it.unibo.coinquify.utils.TimeImpl;
import it.unibo.coinquify.utils.UtilsGUI;
import lu.tudor.santec.jtimechooser.JTimeChooser;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;

/**
 * GUI of Calendar.
 */
public class CalendarGUI implements PaneGUI {
    private static final int FIELD_COLUMNS = 5;
    private static final int DELETE_POSITION = 8;
    private static final int MODIFY_POSITION = 7;
    private final JPanel panel;
    private final CalendarController controller;

    /**
     * Construct a new calendar GUI.
     */
    public CalendarGUI() {
        this.panel = new JPanel();
        this.controller = new CalendarControllerImpl();
        this.mainDisplay();
    }

    /**
     * main display.
     */
    private void mainDisplay() {
        this.panel.setLayout(new GridLayout(3, 2, 0, 0));

        final JButton addEvent = new JButton(Messages.getMessages().getString("ADD_EVENT"));
        addEvent.addActionListener(e -> {
            try {
                this.displayAddEventFrame(Optional.empty());
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });
        this.panel.add(addEvent);

        final JButton viewAllEvent = new JButton(Messages.getMessages().getString("VIEW_ALL_EVENTS"));
        viewAllEvent.addActionListener(e -> this.displayEvents(this.controller.getEvents(Optional.empty())));
        this.panel.add(viewAllEvent);

        final JXDatePicker picker = new JXDatePicker();
        picker.setLocale(Messages.getMessages().getCurrentLocale());
        picker.getEditor().setText(Messages.getMessages().getString("NEW_LINE"));
        picker.setDate(Calendar.getInstance().getTime());
        this.panel.add(picker);

        final JButton viewTodayEvent = new JButton(Messages.getMessages().getString("VIEW_DAY_EVENTS"));
        viewTodayEvent
                .addActionListener(e -> this.displayEvents(this.controller.getEvents(Optional.of(picker.getDate()))));
        this.panel.add(viewTodayEvent);

        final JButton deleteOldEvents = new JButton(Messages.getMessages().getString("DELETE_OLD_EVENTS"));
        deleteOldEvents.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, Messages.getMessages().getString("SURE_TO_DELETE_OLD_EVENTS"),
                    Messages.getMessages().getString("WARNING"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.controller.deleteOldEvents();
            }
        });
        this.panel.add(deleteOldEvents);

        final JButton viewCurrentEvents = new JButton(Messages.getMessages().getString("VIEW_CURRENT_EVENTS"));
        viewCurrentEvents.addActionListener(e -> this.displayEvents(this.controller.getCurrentEvents()));
        this.panel.add(viewCurrentEvents);

    }

    private void displayAddEventFrame(final Optional<Event> event)
            throws FileNotFoundException, ClassNotFoundException, IOException {
        final JFrame addEventFrame = new JFrame();
        final JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new GridLayout(0, Constants.COMPONENTS_FOR_ROW, 0, 0));
        addEventFrame.add(eventPanel);

        final JXDatePicker pickerStartDate = new JXDatePicker();
        pickerStartDate.setLocale(Messages.getMessages().getCurrentLocale());
        pickerStartDate.getEditor().setText(Messages.getMessages().getString("NEW_LINE"));
        pickerStartDate.setDate(Calendar.getInstance().getTime());
        if (event.isPresent()) {
            pickerStartDate.setDate(event.get().getStartDate());
        }
        eventPanel.add(new JLabel(Messages.getMessages().getString("START_DATE")));
        eventPanel.add(pickerStartDate);

        final JXDatePicker pickerEndDate = new JXDatePicker();
        pickerEndDate.setLocale(Messages.getMessages().getCurrentLocale());
        pickerEndDate.getEditor().setText(Messages.getMessages().getString("NEW_LINE"));
        pickerEndDate.setDate(Calendar.getInstance().getTime());
        if (event.isPresent()) {
            pickerEndDate.setDate(event.get().getEndDate());
        }
        eventPanel.add(new JLabel(Messages.getMessages().getString("END_DATE")));
        eventPanel.add(pickerEndDate);

        final JTextField titleF = new JTextField(FIELD_COLUMNS);
        if (event.isPresent()) {
            titleF.setText(event.get().getTitle());
        }
        eventPanel.add(new JLabel(Messages.getMessages().getString("TITLE")));
        eventPanel.add(titleF);

        final JTextField descritpionF = new JTextField(FIELD_COLUMNS);
        if (event.isPresent()) {
            descritpionF.setText(event.get().getDescription());
        }
        eventPanel.add(new JLabel(Messages.getMessages().getString("DESCRIPTION")));
        eventPanel.add(descritpionF);

        final JTimeChooser startTimeC = new JTimeChooser();
        startTimeC.setShowSeconds(false);
        if (event.isPresent()) {
            final Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.AM_PM, 0);
            calendar.set(Calendar.HOUR, event.get().getStartTime().getHour());
            calendar.set(Calendar.MINUTE, event.get().getStartTime().getMinutes());
            calendar.set(Calendar.SECOND, 0);
            startTimeC.setTime(calendar.getTime());
        }
        eventPanel.add(new JLabel(Messages.getMessages().getString("START_TIME")));
        eventPanel.add(startTimeC);

        final JTimeChooser endTimeC = new JTimeChooser();
        endTimeC.setShowSeconds(false);
        if (event.isPresent()) {
            final Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.AM_PM, 0);
            calendar.set(Calendar.HOUR, event.get().getEndTime().getHour());
            calendar.set(Calendar.MINUTE, event.get().getEndTime().getMinutes());
            calendar.set(Calendar.SECOND, 0);
            endTimeC.setTime(calendar.getTime());
        }
        eventPanel.add(new JLabel(Messages.getMessages().getString("END_TIME")));
        eventPanel.add(endTimeC);

        final JTextField locationF = new JTextField(FIELD_COLUMNS);
        if (event.isPresent()) {
            locationF.setText(event.get().getLocation());
        }
        eventPanel.add(new JLabel(Messages.getMessages().getString("LOCATION")));
        eventPanel.add(locationF);
        final JButton addBtn = new JButton(Messages.getMessages().getString("SAVE"));
        addEventFrame.add(addBtn, BorderLayout.SOUTH);
        addBtn.addActionListener(e -> {
            final Event newEvent = new EventImpl(pickerStartDate.getDate(), pickerEndDate.getDate(),
                    new TimeImpl(startTimeC.getHours(), startTimeC.getMinutes()),
                    new TimeImpl(endTimeC.getHours(), endTimeC.getMinutes()), titleF.getText(), descritpionF.getText(),
                    locationF.getText());
            try {
                if (event.isPresent()) {
                    this.controller.update(event.get(), newEvent);
                } else {
                    this.controller.addEvent(newEvent);
                }
            } catch (final IllegalArgumentException e1) {
                JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("ERROR_IN_EVENT_ARGS"));
            }
            addEventFrame.dispose();
        });
        UtilsGUI.finishFrame(addEventFrame);
    }

    private void displayEvents(final List<Event> events) {
        if (events.isEmpty()) {
            JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("ERROR_NO_EVENTS"));
            return;
        }
        final JFrame eventFrame = new JFrame();
        final JPanel eventPanel = new JPanel(new BorderLayout());
        eventFrame.add(eventPanel);
        eventFrame.setTitle(Messages.getMessages().getString("EVENT"));
        final String[] col = { Messages.getMessages().getString("START_DATE"),
                Messages.getMessages().getString("START_TIME"), Messages.getMessages().getString("END_DATE"),
                Messages.getMessages().getString("END_DATE"), Messages.getMessages().getString("TITLE"),
                Messages.getMessages().getString("LOCATION"), Messages.getMessages().getString("DESCRIPTION"),
                Messages.getMessages().getString("UPDATE"), Messages.getMessages().getString("DELETE") };

        // The 0 argument is number rows.
        final DefaultTableModel tableModel = new DefaultTableModel(col, 0) {
            /**
             * 
             */
            private static final long serialVersionUID = 6746809753206485189L;

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return column == MODIFY_POSITION || column == DELETE_POSITION;
            }
        };

        // final JTable table = new JTable(tableModel);
        final JXTable table = new JXTable(tableModel);

        for (final Event event : events) {
            final Object[] objs = { DateFormat.getDateInstance(DateFormat.SHORT).format(event.getStartDate()),
                    event.getStartTime().toString(),
                    DateFormat.getDateInstance(DateFormat.SHORT).format(event.getEndDate()),
                    event.getEndTime().toString(), event.getTitle(), event.getLocation(), event.getDescription(),
                    Messages.getMessages().getString("UPDATE"), Messages.getMessages().getString("DELETE") };

            final Action modify = new AbstractAction() {
                /**
                 * 
                 */
                private static final long serialVersionUID = 1L;

                public void actionPerformed(final ActionEvent e) {
                    try {
                        int modelRow = Integer.valueOf(e.getActionCommand());
                        displayAddEventFrame(Optional.of(events.get(modelRow)));
                    } catch (ClassNotFoundException | IOException e1) {
                        e1.printStackTrace();
                    }
                    // or i can REFRESH TABLE AFTER UPDATE
                    eventFrame.dispose();
                }
            };

            final ButtonColumn buttonColumnModify = new ButtonColumn(table, modify, MODIFY_POSITION);
            buttonColumnModify.setMnemonic(KeyEvent.VK_D);

            final Action delete = new AbstractAction() {

                /**
                 * 
                 */
                private static final long serialVersionUID = 1L;

                public void actionPerformed(final ActionEvent e) {
                    if (JOptionPane.showConfirmDialog(null, Messages.getMessages().getString("SURE_TO_DELETE_EVENT"),
                            Messages.getMessages().getString("WARNING"),
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        JTable table = (JTable) e.getSource();
                        int modelRow = Integer.valueOf(e.getActionCommand());
                        ((DefaultTableModel) table.getModel()).removeRow(modelRow);
                        controller.remove(events.get(modelRow));
                    }
                }
            };

            final ButtonColumn buttonColumnDelete = new ButtonColumn(table, delete, DELETE_POSITION);
            buttonColumnDelete.setMnemonic(KeyEvent.VK_D);
            tableModel.addRow(objs);
        }
        table.packAll();
        eventPanel.add(table, BorderLayout.CENTER);
        // for columns name view
        eventPanel.add(table.getTableHeader(), BorderLayout.NORTH);
        UtilsGUI.finishFrame(eventFrame);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    /**
     * 
     * @throws IOException
     *             if there're problems with files
     */
    public void saveAll() throws IOException {
        this.controller.saveAll();
    }

    @Override
    public String getName() {
        return Messages.getMessages().getString("CALENDAR");
    }
}