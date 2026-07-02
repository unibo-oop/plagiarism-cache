package viewimpl.manageprogrammingfilms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.managefilms.FilmsController;
import controller.manageprogrammingfilms.ProgrammingFilmsController;
import exceptions.ProgrammationNotAvailableException;
import utilities.factory.Film;
import utilities.factory.ProgrammedFilm;
import utilities.factory.ProgrammedFilmFactory;
import utilitiesimpl.Hall;
import utilitiesimpl.ViewSettings;
import utilitiesimpl.factoryimpl.ProgrammedFilmFactoryImpl;
import view.manageprogrammingfilms.ScheduleFilmsView;
import view.manageprogrammingfilms.factory.ScheduleFilmsFactory;
import viewimpl.manageprogrammingfilms.factory.DatePanel;
import viewimpl.manageprogrammingfilms.factory.InfoProgrammationPanel;
import viewimpl.manageprogrammingfilms.factory.ScheduleFilmsFactoryImpl;
import viewimpl.manageprogrammingfilms.factory.TimePanel;
/** 
 * View for schedule films.
 * */
public final class ScheduleFilmViewImpl implements ScheduleFilmsView {

    private static final long serialVersionUID = 1L;
    private static final int TOP_EMPTY_BORDER = 20;
    private static final int LEFT_EMPTY_BORDER = 20;
    private static final int BOTTOM_EMPTY_BORDER = 20;
    private static final int RIGHT_EMPTY_BORDER = 20;

    private final DatePanel dateSelector;
    private final TimePanel timeSelector;
    private final InfoProgrammationPanel infoProgrammation;
    private final JFrame frame = new JFrame();
    private final Container container = frame.getContentPane();
    private final ScheduleFilmsFactory factory = new ScheduleFilmsFactoryImpl();
    private ProgrammingFilmsController observer;
    private FilmsController filmsController;

    // real dimension of the screen
    private static final  double PROPORTION_HEIGHT = 2;
    private static final  double PROPORTION_WIDTH = 4.15;
    private final int screenWidth = (int) ViewSettings.DIMENSION_WIDTH_SCREEN;
    private final int screenHeight = (int) ViewSettings.DIMENSION_HEIGTH_SCREEN;
    private final int frameWidth = (int) (screenWidth / PROPORTION_WIDTH);
    private final int frameHeight = (int) (screenHeight / PROPORTION_HEIGHT);

    public ScheduleFilmViewImpl(final FilmsController filmsController) {
        final JPanel bottomPanel;
        this.filmsController = filmsController;
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel dateTimePanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel = factory.getBottomPanel(new ScheduleButtonListener());
        infoProgrammation =  factory.getInfoProgrammationPanel(filmsController);
        mainPanel.add(infoProgrammation, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        dateTimePanel.setBorder(BorderFactory.createCompoundBorder(
                                                            BorderFactory.createTitledBorder("Date and start time"),
                                                                    BorderFactory.createEmptyBorder(TOP_EMPTY_BORDER, LEFT_EMPTY_BORDER, BOTTOM_EMPTY_BORDER, RIGHT_EMPTY_BORDER)));
        dateSelector = factory.getDatePanel(); // year, month , day
        timeSelector = factory.getTimePanel();
        dateTimePanel.add(dateSelector, BorderLayout.WEST);
        dateTimePanel.add(timeSelector, BorderLayout.EAST);
        mainPanel.add(dateTimePanel, BorderLayout.NORTH);
        container.add(mainPanel);
        frame.setTitle("Schedule a film");
        frame.pack();
        //frame.setSize(new Dimension(frameWidth, frameHeight));
        //frame.setPreferredSize(new Dimension(frameWidth, frameHeight));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(frameWidth, frameHeight));
        }

    /**
     * {@inheritDoc}*/
    @Override
    public void start() {
        //frame.setLocationByPlatform(true);
        this.update();
        this.reset();
        frame.setVisible(true);
    }
    /**
     * {@inheritDoc}*/
    @Override
    public void setObserver(final ProgrammingFilmsController observer) {
        this.observer = observer;
    }
    /**
     * {@inheritDoc}*/
    @Override
    public void update() {
        infoProgrammation.update();
    }

    private void checkDateTime(final LocalTime time, final  LocalDate date) {
        if (date.isEqual(LocalDate.now()) && time.getHour() < LocalTime.now().getHour()) {
            throw new IllegalArgumentException("Cannot schedule in the past, please change time");
        } 
        if (date.isEqual(LocalDate.now()) && time.getHour() == LocalTime.now().getHour()  && time.getMinute() < LocalTime.now().getMinute()) {
            throw new IllegalArgumentException("Cannot schedule in the past, please change time");
        } 
    }
    /**
     * {@inheritDoc}*/
    @Override
    public void setFilmsController(final FilmsController filmsController) {
        this.filmsController = filmsController;
    }
    /**
     * {@inheritDoc}*/
    @Override
    public void reset() {
        infoProgrammation.reset();
        dateSelector.reset();
        timeSelector.reset();
    }
    /**
     * Describes action listener when user click on schedule button.
     *  */
    private class ScheduleButtonListener implements ActionListener {

        private final ProgrammedFilmFactory programmedFilmFactory = new ProgrammedFilmFactoryImpl();

        public void actionPerformed(final ActionEvent ae) {
            Film selectedFilm;
            LocalDate selectedDate;
            LocalTime selectedTime;
            Hall selectedHall;
            double selectedPrice;
            try {
                    selectedDate = dateSelector.getDate();
                    selectedTime = timeSelector.getTime(selectedDate);
                    checkDateTime(selectedTime, selectedDate);
                    selectedHall =  infoProgrammation.getHall();
                    selectedPrice = Double.parseDouble(infoProgrammation.getPrice());
                    selectedFilm = infoProgrammation.getSelectedFilm();
                    try {
                        final ProgrammedFilm film = programmedFilmFactory.createProgrammedFilm(selectedFilm.getID(), selectedHall, selectedPrice, selectedDate, selectedTime, selectedTime.plusMinutes(selectedFilm.getDuration()));
                        observer.addProgrammedFilm(film);
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JOptionPane.showOptionDialog(frame, "Film has been scheduled", "Info", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                            }
                            });
                        frame.setVisible(false);
                        observer.update();
                    } catch (final ProgrammationNotAvailableException e) {

                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(frame, e.getMessage(), "Film not scheduled", JOptionPane.ERROR_MESSAGE);
                            }
                            });
                    }
             } catch (final Exception e) {
                    JOptionPane.showMessageDialog(frame, e.getMessage(), "Invalid Data", JOptionPane.ERROR_MESSAGE);
             }

    }

    }

}


