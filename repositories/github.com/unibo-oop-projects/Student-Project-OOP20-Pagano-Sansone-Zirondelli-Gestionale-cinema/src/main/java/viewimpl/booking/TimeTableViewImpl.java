package viewimpl.booking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;

import controller.booking.TimeTableViewObserver;
import modelimpl.manageprogrammedfilms.FilterByDateImpl;
import utilities.factory.Film;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.Hall;
import utilitiesimpl.ViewSettings;
import view.booking.GUIFactoryBooking;
import view.booking.TimeTableView;
import viewimpl.manageprogrammingfilms.factory.ProgrammingFilmsViewFactoryImpl;

public class TimeTableViewImpl implements TimeTableView {
    private static final double WIDTH_PERC_FRAME = 0.5;
    private static final double HEIGTH_PERC_FRAME = 0.5;
    private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 1;
    private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.9;

    private static final String TITLE = "Time Tabel Film";
    private static final String TEXT_BUTTON_SELECT = "Select";
    private static final String TEXT_BUTTON_RESET = "Reset"; 
    private static final String TEXT_JCOMBOBOX_1 = "Date";
    private static final String TEXT_JCOMBOBOX_2 = "Time";
    private static final String INFO_STRING = "Select a schedule from:";
    private static final String BTN_FILTER_STRING = "Apply";
    private static final String CHECKBOX_TITLE = "Order by:";
    private static final String CALENDAR_STRING = "Filter by date: ";
    private static final Color COLOR_STRING = Color.BLACK;
    private TimeTableViewObserver observer;
    private final JFrame frame;
    private final Set<ProgrammedFilm> setProgrammedFilm;

    public TimeTableViewImpl(final TimeTableViewObserver observer, final Set<ProgrammedFilm> setProgrammedFilmOriginal, final Film film) {
        setProgrammedFilm = new HashSet<>(observer.handlerProgrammedFilm(setProgrammedFilmOriginal, new FilterOldDateImpl()));
        final String nameFilm = film.getName();
        final GUIFactoryBooking factory = new GUIFactoryBookingImpl(); 
        this.observer = observer;
        this.frame = factory.getBaseFrame(TITLE);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setMinimumSize(new Dimension((int) (screenSize.getWidth() * WIDTH_MINIMUM_FRAME), (int) (screenSize.getHeight() * HEIGTH_MINMUM_FRAME)));

        final JPanel north = factory.getInfoPanel(INFO_STRING + nameFilm, e -> {
            observer.showBackFromTimeTable();
            this.frame.dispose();
        });
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JButton bookBtn = new JButton(TEXT_BUTTON_SELECT);
        final JTable table = factory.getTable(setProgrammedFilm);

        bookBtn.addActionListener(e -> {
            final int row = table.getSelectedRow();
            if (row != -1) {
                final LocalDate date = (LocalDate) table.getModel().getValueAt(row, 0);
                final LocalTime time = (LocalTime) table.getModel().getValueAt(row, 1);
                final Hall hall =  (Hall) table.getModel().getValueAt(row, 2);

                final ProgrammedFilm programmedFilm = setProgrammedFilm.stream()
                        .filter(f -> f.getStartTime().equals(time))
                        .filter(f -> f.getHall().equals(hall))
                        .filter(f -> f.getDate().equals(date))
                        .findAny().get();
                observer.bookTicketForFilm(programmedFilm);
                this.frame.dispose();
            } else {
                this.notSelectedRow();
            }
        });
        final JScrollPane scroll = new JScrollPane(table);
        /*
         * filterPanel 
         */
        final JPanel filterPanel = new JPanel(new BorderLayout());
        final JPanel panelCalendar = new JPanel(new BorderLayout());
        final JPanel panelCheckBox = new JPanel(new BorderLayout());
        final JButton filterBtn = new JButton(BTN_FILTER_STRING);
        /*
         * checkBoxGroup
         */
        final ButtonGroup checkBoxGroup = new ButtonGroup();
        JCheckBox jcb1, jcb2;
        jcb1 = new JCheckBox(TEXT_JCOMBOBOX_1, false);
        jcb2 = new JCheckBox(TEXT_JCOMBOBOX_2, false);

        checkBoxGroup.add(jcb1);
        checkBoxGroup.add(jcb2);

        final JPanel panelCheckBoxInternal = new JPanel();
        final JLabel labelCheckBox = new JLabel(CHECKBOX_TITLE);
        labelCheckBox.setForeground(COLOR_STRING);
        panelCheckBox.add(labelCheckBox, BorderLayout.NORTH);
        panelCheckBoxInternal.add(jcb1);
        panelCheckBoxInternal.add(jcb2);
        panelCheckBox.add(panelCheckBoxInternal, BorderLayout.CENTER);

        final ProgrammingFilmsViewFactoryImpl fctFilm = new ProgrammingFilmsViewFactoryImpl();
        final Calendar calendar = fctFilm.createCalendar(); 
        panelCalendar.add(calendar, BorderLayout.CENTER);
        final JLabel labelCalendar = new JLabel(CALENDAR_STRING);
        labelCalendar.setForeground(COLOR_STRING);
        panelCalendar.add(labelCalendar, BorderLayout.NORTH);

        final JButton resetBtn = new JButton(TEXT_BUTTON_RESET);
        resetBtn.addActionListener(e -> {
           this.refresh(table, setProgrammedFilm);
           calendar.getSelection().reset();
        });
        filterPanel.add(panelCalendar, BorderLayout.NORTH);
        filterPanel.add(panelCheckBox, BorderLayout.CENTER);
        final JPanel panelButtonFilter = new JPanel();
        panelButtonFilter.add(filterBtn);
        panelButtonFilter.add(resetBtn);
        filterPanel.add(panelButtonFilter, BorderLayout.SOUTH);

        filterBtn.addActionListener(e -> {
            List<ProgrammedFilm> listProgrammedFilm = new ArrayList<>(setProgrammedFilm); 
            if (!calendar.getSelection().getRanges().isEmpty()) {
                final DateTime dataCalendar = calendar.getSelection().getRanges().get(0);
                final LocalDate date = LocalDate.of(dataCalendar.getYear(), dataCalendar.getMonth(), dataCalendar.getDay());
                listProgrammedFilm = observer.handlerProgrammedFilm(listProgrammedFilm, new FilterByDateImpl(date));
            }
            if (jcb1.isSelected()) {
                listProgrammedFilm = observer.handlerProgrammedFilm(listProgrammedFilm, new SorterByLocalDate());
            }
            if (jcb2.isSelected()) {
                listProgrammedFilm = observer.handlerProgrammedFilm(listProgrammedFilm, new SorterByLocalTime());

            }

            this.refresh(table, listProgrammedFilm);
        });

        mainPanel.add(filterPanel, BorderLayout.WEST);
        mainPanel.add(north, BorderLayout.NORTH);
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(bookBtn, BorderLayout.SOUTH);
        this.frame.add(mainPanel);
        this.frame.setSize((int) ViewSettings.DIMENSION_WIDTH_VIEW, (int) ViewSettings.DIMENSION_HEIGTH_VIEW);

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
       this.frame.setVisible(true);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void checkEmptyProgrammation(final Collection<ProgrammedFilm> collProgrammedFilm) {
       if (collProgrammedFilm.isEmpty()) {
            EventQueue.invokeLater(new Runnable() {
                @Override
            public void run() {
                    JOptionPane.showMessageDialog(frame,  "Empty programmation for film", "No programmation for film ",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }
    }
    /**
     * Check if not selected row in table.
     */
    private void notSelectedRow() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(frame, "Not selected row", 
                        "Incorrect Row", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    /**
     * Refresh table with list.
     * @param table refreshed
     * @param list used to fill table
     */
    private void refresh(final JTable table, final Collection<ProgrammedFilm> list) {
        final GUIFactoryBooking factory = new GUIFactoryBookingImpl(); 
        final DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setModel(factory.getModel(list));
        model.fireTableDataChanged();
        this.checkEmptyProgrammation(list);
    }
}

