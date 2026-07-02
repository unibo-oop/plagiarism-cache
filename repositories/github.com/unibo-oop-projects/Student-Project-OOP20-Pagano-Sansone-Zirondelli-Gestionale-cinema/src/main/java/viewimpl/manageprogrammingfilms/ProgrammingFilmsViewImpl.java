package viewimpl.manageprogrammingfilms;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mindfusion.common.ChangeListener;
import com.mindfusion.scheduling.Calendar;

import controller.managefilms.FilmsController;
import controller.manageprogrammingfilms.ProgrammingFilmsController;
import model.manageprogrammingfilms.HandlerList;
import modelimpl.manageprogrammedfilms.FilterByDateImpl;
import modelimpl.manageprogrammedfilms.SorterByTimeImpl;
import utilities.factory.ProgrammedFilm;
import utilitiesimpl.ViewSettings;
import view.manageprogrammingfilms.ProgrammingFilmsView;
import view.manageprogrammingfilms.factory.ProgrammingFilmsGUIfactory;
import viewimpl.manageprogrammingfilms.factory.ProgrammingFilmsViewFactoryImpl;
/** 
 * Show view  with all programmed films.
 * */
public final class ProgrammingFilmsViewImpl implements ProgrammingFilmsView {

    private static final long serialVersionUID = 7114066347061701832L;

    private static final String FIRST_COLUMN_NAME = "Film";
    private static final String SECOND_COLUMN_NAME = "Hall";
    private static final String THIRD_COLUMN_NAME = "Start time";
    private static final String FOURTH_COLUMN_NAME = "End time";
    private static final int COLUMNS_NUMBER = 4;
    private static final String[] COLUMNS_NAMES = { FIRST_COLUMN_NAME, SECOND_COLUMN_NAME, THIRD_COLUMN_NAME, FOURTH_COLUMN_NAME };
    private static final String FRAME_NAME = "Programming  film";
    private static final int CALENDAR_WIDTH = 250;
    private static final int CALENDAR_HEIGHT = 300;
    private static final int BUTADD_WIDTH = 250;
    private static final int BUTADD_HEIGHT = 100;

    private final JFrame frame = new JFrame(FRAME_NAME);
    private final Container container = frame.getContentPane();
    private final ProgrammingFilmsGUIfactory factory = new ProgrammingFilmsViewFactoryImpl();
    // real dimension of the screen
    private final int screenWidth = (int) ViewSettings.DIMENSION_WIDTH_SCREEN;
    private final int screenHeight = (int) ViewSettings.DIMENSION_HEIGTH_SCREEN;
    // real dimension of my frame
    private final int frameWidth = (int) ViewSettings.DIMENSION_WIDTH_VIEW;
    private final int frameHeight = (int) ViewSettings.DIMENSION_HEIGTH_VIEW;
    private final JButton home = factory.createButton("Home");

    private final JButton addProgrammation = factory.createButton("Add programmation");
    private final Calendar calendar;
    private final JTable table;
    private ProgrammingFilmsController observer;
    private FilmsController filmsController;
    private final Map<Integer, ProgrammedFilm> map; // map row number to ProgrammedFilm

    public ProgrammingFilmsViewImpl() {

        final JPanel mainPanel = factory.createPanel(new BorderLayout());
        final JPanel centerPanel = factory.createPanel(new BorderLayout());
        final JPanel northPanel = factory.createPanel(new BorderLayout());
        final JPanel westPanel = factory.createPanel(new BorderLayout());
        final JPanel optionPanel = factory.createPanel(null);
        map = new HashMap<>();
        final Object[][] data = new Object[1][4]; // row columns
        table = factory.createTable(COLUMNS_NAMES, data);
        final JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        calendar = factory.createCalendar();
        calendar.getSelection().addChangeListener(new ChangeListener() {
            @Override
            public void changed(final EventObject e) {
                onSelectionChanged();
            }
        });
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        northPanel.add(home, BorderLayout.EAST);
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        westPanel.add(optionPanel, BorderLayout.SOUTH); // add panel in south of west panel
        westPanel.add(calendar, BorderLayout.NORTH);
        calendar.setPreferredSize(new Dimension(CALENDAR_WIDTH, CALENDAR_HEIGHT));
        optionPanel.add(addProgrammation);
        addProgrammation.setMaximumSize(new Dimension(BUTADD_WIDTH, BUTADD_HEIGHT)); // WIDTH,HEIGHT
        addProgrammation.setPreferredSize(new Dimension(BUTADD_WIDTH, BUTADD_HEIGHT));
        container.add(mainPanel);
        addProgrammation.addActionListener(event -> {
            observer.showScheduleFilmView();
        });

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                final JTable table = (JTable) mouseEvent.getSource();
                final int clickCount = mouseEvent.getClickCount();
                final int selectedRow = table.getSelectedRow();
                if (clickCount == 2 && selectedRow != -1) {
                    final int option = JOptionPane.showConfirmDialog(frame,
                            "Do you want delete this selected programmation?", "Deleting", JOptionPane.YES_NO_OPTION);
                    if (option == 0) { // yes 0 option , no 1 option
                        observer.deleteProgrammedFilm(map.get(selectedRow));
                        onSelectionChanged();
                    }
                }
            }
        });

        home.addActionListener(e -> {
            observer.showMenu();
            frame.setVisible(false);
        });

        frame.pack();
        frame.setMinimumSize(new Dimension(screenWidth / 2, screenHeight / 2));
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(final WindowEvent winEvent) {
                // ((JFrame)winEvent.getSource()).dispose();
                update();
            }
        });
    }

    private void onSelectionChanged() { // when user clicks on specific date, table must be updated
        final LocalDate selectedDate = this.getCalendarSelectionDate();
        final List<ProgrammedFilm> list = observer.getAllProgrammedFilms();
        final HandlerList<ProgrammedFilm> handler = observer.getManagerProgrammingFilms().getHandlerList();
        final List<ProgrammedFilm> filtByDate = handler.filterBy(list, new FilterByDateImpl(selectedDate)); // filter
                                                                                                            // for
                                                                                                            // selected
                                                                                                            // date
        final List<ProgrammedFilm> sortByTime = handler.sortBy(filtByDate, new SorterByTimeImpl());
        this.fillDataTable(sortByTime);
    }

    private LocalDate getCalendarSelectionDate() {
        if (calendar.getSelection().getIsEmpty()) {
            return LocalDate.now();
        }
        final int day = calendar.getSelection().getRanges().get(0).getDay();
        final int month = calendar.getSelection().getRanges().get(0).getMonth();
        final int year = calendar.getSelection().getRanges().get(0).getYear();
        return LocalDate.of(year, month, day);
    }

    private void fillDataTable(final List<ProgrammedFilm> manipulatedList) {
        map.clear(); // empty map
        final Object[][] data = new Object[manipulatedList.size()][COLUMNS_NUMBER];
        for (int i = 0; i < manipulatedList.size(); i++) {
            final int id = manipulatedList.get(i).getIdProgrammation();
            data[i][0] = filmsController.getFilms().stream().filter(film -> film.getID() == id)
                    .collect(Collectors.toList()).get(0).getName();
            data[i][1] = manipulatedList.get(i).getHall();
            data[i][2] = manipulatedList.get(i).getStartTime();
            data[i][3] = manipulatedList.get(i).getEndTime();
            map.put(i, manipulatedList.get(i));
        }
        final DefaultTableModel modelNew = new DefaultTableModel(data, COLUMNS_NAMES);
        table.setModel(modelNew);
        final DefaultTableModel dfm = (DefaultTableModel) table.getModel();
        dfm.fireTableDataChanged();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void start() {
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void setObserver(final ProgrammingFilmsController observer) {
        this.observer = observer;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void update() {
        this.onSelectionChanged(); // update table
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public void setFilmsController(final FilmsController filmsController) {
        this.filmsController = filmsController;
    }
}
