package viewimpl.booking;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controller.booking.BookingViewObserver;
import utilities.Seat;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.GeneralSettings;
import utilitiesimpl.Hall;
import utilitiesimpl.Row;
import utilitiesimpl.SeatImpl;
import utilitiesimpl.SeatState;
import utilitiesimpl.ViewSettings;
import view.booking.BookingView;




import view.booking.GUIFactoryBooking;

public class BookingViewImpl implements BookingView {
    private final JFrame frame;
    private final BookingViewObserver observer;
    private static final String TITLE = "BookginView";
    private static final String INFO_STRING = "Choose seats and book";
    private static final String STRING_BTN_BOOK = "Book"; 
    private static final String STRING_SCREEN_LABEL = "SCREEN"; 
    private final ProgrammedFilm film; 
    private final Map<JButton, SeatImpl> grid = new HashMap<>();
    private static final double WIDTH_PERC_FRAME = 0.5;
    private static final double HEIGTH_PERC_FRAME = 0.5;

    private static final double WIDTH_IMAGE_SEAT = WIDTH_PERC_FRAME / 15;
    private static final double HEIGHT_IMAGE_SEAT = HEIGTH_PERC_FRAME / 15;
    private static final double HEIGHT_IMAGE_LEGEND = HEIGTH_PERC_FRAME / 5;
    private static final double WIDTH_IMAGE_LEGEND = WIDTH_PERC_FRAME / 5;

    private static final double WIDTH_MINIMUM_FRAME = WIDTH_PERC_FRAME / 0.7;
    private static final double HEIGTH_MINMUM_FRAME = HEIGTH_PERC_FRAME / 0.7;


    public BookingViewImpl(final BookingViewObserver observer, final ProgrammedFilm film) {

        final GUIFactoryBooking factory = new GUIFactoryBookingImpl();
        this.film = film;
        this.frame = factory.getBaseFrame(TITLE);
        this.observer = observer;


        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setMinimumSize(new Dimension((int) (screenSize.getWidth() * WIDTH_MINIMUM_FRAME), (int) (screenSize.getHeight() * HEIGTH_MINMUM_FRAME)));

        observer.newBooking();
        final JPanel mainPanel = new JPanel(new BorderLayout()); 
        final JPanel north = factory.getInfoPanel(INFO_STRING, e -> {
            observer.showBackFromBooking(film);
            frame.dispose();
        });
        final Set<Seat> setSeats = observer.getSeatsFromProgrammedFilm(film);
        final Row row = Hall.NUM_ROWS;
        final int col = Hall.NUM_COLUMNS;
        final JPanel center = new JPanel(new BorderLayout());
        final JPanel gridPanel = new JPanel(new GridLayout(row.ordinal() + 1, col));
        for (int i = 0; i < row.ordinal() + 1; i++) {
            for (int j = 0; j < col; j++) {
                SeatState state;
                if (setSeats.contains(new SeatImpl(Row.values()[i], j))) {
                    state  = SeatState.TAKEN;
                } else {
                    state = SeatState.FREE;
                }
                final JButton button = factory.getButtonSeat(state, i, j);
                this.grid.put(button, new SeatImpl(Row.values()[i], j));
                gridPanel.add(button);
            }
        }
        grid.keySet().forEach(btn -> {
            btn.addActionListener(e -> {
                final JButton button = (JButton) e.getSource();
                observer.buttonSelected(grid.get(button), film);
            });
        });
        final JButton bookBt = new JButton(STRING_BTN_BOOK);
        bookBt.addActionListener(e -> {
            if (observer.getSeatsSelected().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No selected seaet");
            } else {
                observer.bookSeat(film);
                observer.newBooking();
            }
        });
        final JLabel label = new JLabel(STRING_SCREEN_LABEL);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        center.add(label, BorderLayout.NORTH);
        center.add(new JScrollPane(gridPanel), BorderLayout.CENTER);
        mainPanel.add(north, BorderLayout.NORTH);
        mainPanel.add(center, BorderLayout.CENTER);
        mainPanel.add(bookBt, BorderLayout.SOUTH);

        final ImageIcon imageLegend = new ImageIcon(ClassLoader.getSystemResource(GeneralSettings.IMAGE_LEGEND));
        final int width = (int) (screenSize.getWidth() * WIDTH_IMAGE_LEGEND);
        final int height = (int) (screenSize.getHeight() * HEIGHT_IMAGE_LEGEND);
        final JLabel labelLegend = factory.getLabelImage(imageLegend, width, height);
        mainPanel.add(labelLegend, BorderLayout.EAST);
        frame.getContentPane().add(mainPanel);
        frame.setSize((int) ViewSettings.DIMENSION_WIDTH_VIEW, (int) ViewSettings.DIMENSION_HEIGTH_VIEW);

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        frame.setVisible(true);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Set<Seat> setSeatsTaken = observer.getSeatsFromProgrammedFilm(film);
        final Set<Seat> setSeatsSelected = observer.getSeatsSelected();
        for (final var bt : grid.keySet()) {
            ImageIcon imageIcon;
            if (setSeatsTaken.contains(grid.get(bt))) {
                imageIcon = new ImageIcon(ClassLoader.getSystemResource(GeneralSettings.IMAGE_SEAT_TAKEN));
            } else if (setSeatsSelected.contains(grid.get(bt))) {
                imageIcon =  new ImageIcon(ClassLoader.getSystemResource(GeneralSettings.IMAGE_SEAT_SELECTED));
            } else {
                imageIcon = new ImageIcon(ClassLoader.getSystemResource(GeneralSettings.IMAGE_SEAT_FREE));
            }
            final Image image = imageIcon.getImage(); // transform it 
            final Image newimg = image.getScaledInstance((int) (screenSize.getWidth() * WIDTH_IMAGE_SEAT), (int) (screenSize.getHeight() * HEIGHT_IMAGE_SEAT), java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);  // transform it back
            bt.setIcon(imageIcon);
        }
    }
}
