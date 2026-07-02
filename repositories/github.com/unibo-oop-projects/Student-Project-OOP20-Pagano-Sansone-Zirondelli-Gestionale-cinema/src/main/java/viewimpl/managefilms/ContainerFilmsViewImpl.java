package viewimpl.managefilms;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.managefilms.FilmsController;
import utilities.factory.Film;
import utilitiesimpl.ViewSettings;
import view.managefilms.ContainerFilmsView;
import view.managefilms.factory.ContainerFilmsViewFactory;
import view.managefilms.factory.PanelFilmFactory;
import viewimpl.managefilms.factory.ContainerFilmsViewFactoryImpl;
import viewimpl.managefilms.factory.PanelFilmFactoryImpl;

/** 
 * This is a Container films view. It shows a panel with every films added from users.
 */
public final class ContainerFilmsViewImpl implements ContainerFilmsView {

    private static final long serialVersionUID = 7114066347061701832L;

    private static final String FRAME_NAME = "Container Films";
    private static final double PROPORTION_MIN_SIZE = 1.50;
    private final JFrame frame = new JFrame(FRAME_NAME);
    //Components
    private final PanelFilmFactory factoryFilmPanel = new PanelFilmFactoryImpl();
    private final ContainerFilmsViewFactory factory = new ContainerFilmsViewFactoryImpl();
    private final JButton add = factory.createButtonWithText("Add");
    private final JButton home = factory.createButtonWithText("Home");

    private final Container container = frame.getContentPane();
    private FilmsController observer;
    private final Map<JButton, Film> map = new HashMap<>();

    private final JPanel centerPanel;
    private final ActionListener al;
    /** 
        Initialize all components from a specific factory. Add action listener to buttons.
     */
    public ContainerFilmsViewImpl() {

    final JPanel mainPanel = factory.createPanel(new BorderLayout());
    final JPanel northPanel = factory.createPanel(new FlowLayout(FlowLayout.RIGHT));
    final JPanel southPanel = factory.createPanel(new FlowLayout(FlowLayout.CENTER));
    centerPanel = new JPanel();
    centerPanel.add(factoryFilmPanel.getFilmPanel(map, new HashSet<>()));
    mainPanel.add(centerPanel, BorderLayout.CENTER);
    mainPanel.add(northPanel, BorderLayout.NORTH);
    mainPanel.add(southPanel, BorderLayout.SOUTH);

    northPanel.add(home);
    southPanel.add(add);

    add.addActionListener(event -> {
        observer.showNewFilmView();
        frame.setVisible(false);
    });

    home.addActionListener(event -> {
        observer.showMenu();
        frame.setVisible(false);
    }
    );

    al = (e) -> { 
        //this is what must be done when users click on specific film . So specific film gui must be viewed.
        final JButton selectedFilm = (JButton) e.getSource(); 
        final Film film = map.get(selectedFilm);
        frame.setVisible(false);
        observer.showInfoFilmView(film);
    };
    //add action listener to every buttons
    for (final var button: map.keySet()) {
        button.addActionListener(al);
    }

    container.add(mainPanel);
    frame.pack();
    frame.setSize((int) ViewSettings.DIMENSION_WIDTH_VIEW, (int) ViewSettings.DIMENSION_HEIGTH_VIEW);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.validate();
    final Dimension minDimension = new Dimension((int) (ViewSettings.DIMENSION_WIDTH_VIEW / PROPORTION_MIN_SIZE), (int) (ViewSettings.DIMENSION_HEIGTH_VIEW / PROPORTION_MIN_SIZE));
    frame.setMinimumSize(minDimension);
    }

    /** 
         Start and show this view. If there aren't added films, dialog will be shown.
     * */

    @Override
    public void start() {
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        if (observer.getFilms().isEmpty()) {
            this.showNoFilmsDialog();
        }
    }

    /**
        Set GUI films controller observer.
        @param observer observer to set.
     */

    @Override
    public void setObserver(final FilmsController observer) {
        this.observer = observer;
    }
    /**
        Update GUI. It's used to refresh view when films are added. Map every button with relative film.
     */

    @Override
    public void update() { //Reset map, take new films and rebuild central panel
        map.clear();
        centerPanel.remove(0); //remove first child, so remove old panel with films
        final Set<Film> film = new HashSet<>(observer.getFilms());
        centerPanel.add(factoryFilmPanel.getFilmPanel(map, film));
        for (final var button: map.keySet()) {
            button.addActionListener(al);
        }
        centerPanel.validate();
        frame.validate();
    }

    /**
        Show a dialog when there aren't films.
     */
    private void showNoFilmsDialog() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(frame, "There aren't films! Please click on Add button down below to insert a new Film");
            }
        });
    }
}
