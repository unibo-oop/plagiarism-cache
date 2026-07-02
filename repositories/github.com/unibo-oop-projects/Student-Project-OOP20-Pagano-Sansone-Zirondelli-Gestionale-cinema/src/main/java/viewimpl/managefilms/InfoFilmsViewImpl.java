package viewimpl.managefilms;
import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.managefilms.FilmsController;
import utilities.factory.Film;
import utilities.factory.FilmFactory;
import utilitiesimpl.GeneralSettings;
import utilitiesimpl.ViewSettings;
import utilitiesimpl.factoryimpl.FilmFactoryImpl;
import view.managefilms.InfoFilmsView;
import view.managefilms.factory.InfoFilmsViewfactory;
import viewimpl.managefilms.factory.InfoFilmsViewFactoryImpl;

import java.awt.Insets;

/**
     This is a Info films view. It shows a panel with every fields and film info.
 */

public final class InfoFilmsViewImpl implements InfoFilmsView {

    /** IMAGE_HEIGTH_PROPORTION. */
    public static final double IMAGE_HEIGTH_PROPORTION = 1.7;
    /** IMAGE_WIDTH_PROPORTION. */
    public static final double IMAGE_WIDTH_PROPORTION = 3.4;
    /** HGAP_FLOWLAYOUT. */
    public static final int HGAP_FLOWLAYOUT = 35;
    /** VGAP_FLOWLAYOUt. */
    public static final int VGAP_FLOWLAYOUT = 0;
    /** BUTTON_WIDTH_PROPORTION. */
    public static final int BUTTON_WIDTH_PROPORTION = 16;
    /** BUTTON_HEIGHT_PROPORTION. */
    public static final int BUTTON_HEIGHT_PROPORTION = 7;
    /** JTEXTFIELD_WIDTH_PROPORTION. */
    public static final int JTEXTFIELD_WIDTH_PROPORTION = 30;
    /** JTEXTFIELD_HEIGHT_PROPORTION. */
    public static final int JTEXTFIELD_HEIGHT_PROPORTION = 7;
    /** JTEXTAREA_HEIGHT_PROPORTION. */


    public static final int JTEXTAREA_HEIGHT_PROPORTION = 4;
    private static final long serialVersionUID = 7114066347061701832L;
    private static final String FRAME_NAME = "Info film";
    private static final double PROPORTION = 1.15;
    private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private final JFrame frame = new JFrame(FRAME_NAME);
    private final Container container = frame.getContentPane();
    private FilmsController observer;
    private Optional<Film> focusFilm;
    private Optional<String> selectedImagePath;
    private final InfoFilmsViewfactory factory = new InfoFilmsViewFactoryImpl();

    private final JTextField duration = factory.createTextField("Duration (minutes)");
    private final JTextField genre = factory.createTextField("Genre");
    private final JTextArea description = factory.createTextArea("Description");
    private final JButton save = factory.createButtonWithText("Save");
    private final JButton delete = factory.createButtonWithText("Delete");

    private final JButton back = factory.createButtonWithText("Back");
    private final JButton pic = factory.createButtonWithText("");
    private final JTextField title = factory.createTextField("Title");
    //real dimension of the screen
    private final int screenWidth = (int) ViewSettings.DIMENSION_WIDTH_SCREEN;
    private final int screenHeight = (int) ViewSettings.DIMENSION_HEIGTH_SCREEN;
    //real dimension of my frame
    private final int frameWidth = (int) ViewSettings.DIMENSION_WIDTH_VIEW;
    private final int frameHeight = (int) ViewSettings.DIMENSION_HEIGTH_VIEW;

/**
 * Initialize info film view. Creates all components.
 */
    public InfoFilmsViewImpl() {
        final JPanel mainPanel = factory.createPanel(new BorderLayout());
        final JPanel centralPanel = factory.createPanel(new BorderLayout());
        final JPanel westPanel = factory.createPanel(new BorderLayout());
        final JPanel southPanel = factory.createPanel(new BorderLayout());
        final JPanel northPanel = factory.createPanel(new BorderLayout());
        focusFilm = Optional.ofNullable(null); // no film loaded, focusFilm empty
        selectedImagePath = Optional.ofNullable(null); // no selection image path , so empty

        final URL imgURL = ClassLoader.getSystemResource(GeneralSettings.IMAGEFILMSTANDARD);
        final ImageIcon icon = new ImageIcon(imgURL);
        pic.setIcon(
                factory.getScaledIcon(icon, (int) (frameWidth / IMAGE_WIDTH_PROPORTION), (int) (frameHeight / IMAGE_HEIGTH_PROPORTION))
        );

        pic.setMargin(new Insets(0, 0, 0, 0));
        pic.setPreferredSize(new Dimension((int) (frameWidth / IMAGE_WIDTH_PROPORTION), (int) (frameHeight / IMAGE_HEIGTH_PROPORTION)));

        final JPanel firstInfoPanel =  factory.createPanel(new FlowLayout(FlowLayout.LEFT, HGAP_FLOWLAYOUT, VGAP_FLOWLAYOUT));
        final JPanel secondInfoPanel =  factory.createPanel(new FlowLayout(FlowLayout.LEFT, HGAP_FLOWLAYOUT, VGAP_FLOWLAYOUT));
        final JPanel actionPanel = factory.createPanel(new FlowLayout(FlowLayout.CENTER));

        mainPanel.add(centralPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(westPanel, BorderLayout.WEST); 
        northPanel.add(back, BorderLayout.WEST);
        back.setPreferredSize(new Dimension(frameHeight / BUTTON_HEIGHT_PROPORTION, frameWidth / BUTTON_WIDTH_PROPORTION));
        southPanel.add(actionPanel, BorderLayout.SOUTH);
        actionPanel.add(save);
        actionPanel.add(delete);

        southPanel.add(description, BorderLayout.NORTH);
        westPanel.add(pic, BorderLayout.NORTH);
        centralPanel.add(firstInfoPanel, BorderLayout.WEST);
        centralPanel.add(secondInfoPanel, BorderLayout.CENTER);
        title.setPreferredSize(new Dimension(frameWidth / JTEXTFIELD_HEIGHT_PROPORTION, frameWidth / JTEXTFIELD_WIDTH_PROPORTION));
        duration.setPreferredSize(new Dimension(frameWidth / JTEXTFIELD_HEIGHT_PROPORTION, frameWidth / JTEXTFIELD_WIDTH_PROPORTION));
        genre.setPreferredSize(new Dimension(frameWidth / JTEXTFIELD_HEIGHT_PROPORTION, frameWidth / JTEXTFIELD_WIDTH_PROPORTION));
        firstInfoPanel.add(title);
        firstInfoPanel.add(duration);
        secondInfoPanel.add(genre);
        description.setPreferredSize(new Dimension(frameWidth, frameHeight / JTEXTAREA_HEIGHT_PROPORTION));
        container.add(mainPanel);
        frame.pack();

        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(final FocusEvent e) {
                JTextField selectedField;
                try {
                     selectedField = (JTextField) e.getSource(); 
                     if (selectedField.equals(title) && "Title".equals(title.getText())) {
                         title.setText("");
                     }
                     if (selectedField.equals(duration) && "Duration (minutes)".equals(duration.getText())) {
                         duration.setText("");
                     }
                     if (selectedField.equals(genre) && "Genre".equals(genre.getText())) {
                         genre.setText("");
                     }
                } catch (ClassCastException ce) {
                    if ("Description".equals(description.getText())) {
                        description.setText("");
                    }
                }
            }
            @Override
            public void focusLost(final FocusEvent e) {

            }
        };

        description.addFocusListener(focusListener);
        genre.addFocusListener(focusListener);
        title.addFocusListener(focusListener);
        duration.addFocusListener(focusListener);

        pic.addActionListener(e -> {
            final JFileChooser chooser = new JFileChooser();
            final FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG  & PNG Images", "jpg", "png", "jpeg", "JPG");
            chooser.setFileFilter(filter);
            final int returnVal = chooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
               final File selectedFile = chooser.getSelectedFile();
               final ImageIcon img = new ImageIcon(selectedFile.getAbsolutePath());
               pic.setIcon(factory.getScaledIcon(img, (int) (frameWidth / IMAGE_WIDTH_PROPORTION), (int) (frameHeight / IMAGE_HEIGTH_PROPORTION)));
               selectedImagePath = Optional.ofNullable(selectedFile.getAbsolutePath());
            }
        }
        );
        back.addActionListener(event -> {
               frame.setVisible(false); 
               focusFilm = Optional.ofNullable(null);
               observer.showContainerFilmsView();
            }
        );

        save.addActionListener(new SaveActionListener());

        delete.addActionListener(event -> {
            final Optional<String> imagePathToDelete = focusFilm.get().getCoverPath();
            if (imagePathToDelete.isPresent()) {
                observer.getManagerWorkingDIR().deleteFileWithSpecificName(new File(imagePathToDelete.get()));
            }
            observer.getManagerIdsFilms().removeFilmId(focusFilm.get().getID());
            observer.deleteFilmAndProgrammation(focusFilm.get());
            frame.setVisible(false);
            observer.showContainerFilmsView();
        });
        frame.setMinimumSize(new Dimension(frameWidth, frameHeight));
        frame.validate();
    }
    /**
     * Load specific film in the view.
     */
    @Override
    public void loadFilm(final Film film) {
            this.reset();
            focusFilm = Optional.of(film);
            title.setText(film.getName());
            genre.setText(film.getGenre());
            duration.setText(new Integer(film.getDuration()).toString());
            description.setText(film.getDescription());

            if (film.getCoverPath().isPresent()) {
                final ImageIcon icon = new ImageIcon(film.getCoverPath().get());
                pic.setIcon(
                        factory.getScaledIcon(icon, (int) (frameWidth / IMAGE_WIDTH_PROPORTION), (int) (frameHeight / IMAGE_HEIGTH_PROPORTION))
                );
            } else {
                final URL imgURL = ClassLoader.getSystemResource(GeneralSettings.IMAGEFILMSTANDARD);
                final ImageIcon icon = new ImageIcon(imgURL);
                pic.setIcon(factory.getScaledIcon(icon, (int) (frameWidth / IMAGE_WIDTH_PROPORTION), (int) (frameHeight / IMAGE_HEIGTH_PROPORTION)));
            }
            delete.setEnabled(true);
   }
    /**
     * Start films view.
     */
    @Override
    public void start() {
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Set observer.
     */

    @Override
    public void setObserver(final FilmsController observer) {
        this.observer = observer;
    }

    /**
     * Reset all fields.
     */
    @Override
    public void reset() {
        focusFilm = Optional.ofNullable(null);
        selectedImagePath = Optional.ofNullable(null);
        duration.setText("Duration (minutes)");
        genre.setText("Genre");
        description.setText("Description");
        title.setText("Title");
        final URL imgURL = ClassLoader.getSystemResource("images/filmStandardIco.png");
        final ImageIcon icon = new ImageIcon(imgURL);
        pic.setIcon(
                factory.getScaledIcon(icon, (int) (frameWidth / IMAGE_WIDTH_PROPORTION), (int) (frameHeight / IMAGE_HEIGTH_PROPORTION))
        );
        delete.setEnabled(false);
    }
    /**
     * 
     * Describe action listener for save button.
     *
     */
    private final class SaveActionListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent event) {
            int durationTime = 0;

            try {
              durationTime = Integer.parseInt(duration.getText());
              if (durationTime <= 0) {
                  throw new IllegalArgumentException();
              }
            } catch (NumberFormatException exception) {
              JOptionPane.showMessageDialog(frame, "Please enter duration in minutes");
              return;
            } catch (IllegalArgumentException ie) {
              JOptionPane.showMessageDialog(frame, "Please insert duration in minutes as number  > 0");
              return;
            }

            String name = "";
            try {
                name = title.getText();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException();
                }
              } catch (IllegalArgumentException ie) {
                JOptionPane.showMessageDialog(frame, "Please insert a title");
                return;
              }

            String pathWhereStored = "";
            Film film;
            final FilmFactory filmFactory = new FilmFactoryImpl(observer.getManagerIdsFilms());
            if (focusFilm.isEmpty()) { // If users clicks on add new film
                if (selectedImagePath.isEmpty()) { // If users has not selected any image
                    film = filmFactory.createBasicFilm(name, genre.getText(), description.getText(), Optional.ofNullable(null), durationTime);
                    observer.addFilm(film);
                } else {
                    try {
                        pathWhereStored = observer.getManagerWorkingDIR().copyFile(new File(selectedImagePath.get()), GeneralSettings.IMAGESSELECTEDDIR);
                        film = filmFactory.createBasicFilm(name, genre.getText(), description.getText(), Optional.ofNullable(pathWhereStored), durationTime);
                        observer.addFilm(film);
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(frame, "Error during film creation");
                        return;
                    }
                } 
            } else { // If users wants edit an existing film
                final int oldIdFilm = focusFilm.get().getID();
                if (selectedImagePath.isEmpty()) {
                    final Optional<String> equalPath = focusFilm.get().getCoverPath();
                    film = filmFactory.createBasicFilmById(name, genre.getText(), description.getText(), equalPath, durationTime, oldIdFilm);
                    observer.deleteFilm(focusFilm.get());
                    observer.addFilm(film);
                } else { // If users wants edit an existing film and he has selected specific image
                    final Optional<String> imagePathToDelete = focusFilm.get().getCoverPath();
                    if (imagePathToDelete.isPresent()) {
                        observer.getManagerWorkingDIR().deleteFileWithSpecificName(new File(imagePathToDelete.get()));
                    }
                    try {
                        pathWhereStored = observer.getManagerWorkingDIR().copyFile(new File(selectedImagePath.get()), GeneralSettings.IMAGESSELECTEDDIR);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    film = filmFactory.createBasicFilmById(name, genre.getText(), description.getText(), Optional.ofNullable(pathWhereStored), durationTime, oldIdFilm);
                    observer.deleteFilm(focusFilm.get());
                    observer.addFilm(film);
                }
                if (!String.valueOf(focusFilm.get().getDuration()).equals(duration.getText())) {
                    JOptionPane.showMessageDialog(frame, "You have just changed film duration. In the next future programming of this film the duration will be changed.");
                }
            }
            frame.setVisible(false);
            observer.showContainerFilmsView();
        }
    }
}
