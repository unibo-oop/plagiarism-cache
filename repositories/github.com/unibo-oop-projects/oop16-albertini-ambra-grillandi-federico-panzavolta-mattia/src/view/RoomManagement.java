package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import utilities.Genre;

/**
 * This is the GUI for change a movie into a room.
 */
public class RoomManagement extends JFrame {

    private static final long serialVersionUID = -3605077778726372444L;

    private static final double HEIGHT_PERC = 0.4;
    private static final double WIDTH_PERC = 0.5;
    private static final int COLS = 3;
    private static final int ROWS = 5;
    private static final String CENTRAL_TEXT = "Insert here the parameters to create a new film"
            + " to associate with the selected room";
    private static final String ERROR_MESSAGE = "Invalid Input";
    private static final String NORTHERN_TEXT = "Choose the room where you want to change the movie";
    private static final String SOUTHERN_TEXT = "Choose one of the following titles ONLY"
            + " if you want to project a movie you already have";
    private static final String TITLE = "Manage Cinema";

    private final JButton back = new JButton("Back");
    private final JButton submit = new JButton("Submit");
    private final JPanel panel = new JPanel();

    private final JLabel name = new JLabel("Film's name:");
    private final JTextField nameText = new JTextField(30);
    private final JLabel nameError = new JLabel(ERROR_MESSAGE);

    private final JLabel length = new JLabel("Film's lenght (in minutes):");
    private final JTextField lengthText = new JTextField(3);
    private final JLabel lengthError = new JLabel(ERROR_MESSAGE);

    private final JLabel genre = new JLabel("Film's genre:");
    private final JLabel genreError = new JLabel(ERROR_MESSAGE);

    private final JLabel over14 = new JLabel("Film is over14?:");
    private final JLabel threeDim = new JLabel("Film is 3D?:");

    private boolean is3D;
    private boolean isOver14;
    private Genre selectedGenre;
    private int selectedRoom;
    private JComboBox<Genre> cbox;

    /**
     * Build a new GUI for change the movie into a selected room.
     */
    public RoomManagement() {
        super();

        //Set dimension of the window
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));

        //Set layout and background color for the panel
        this.panel.setLayout(new BorderLayout());
        this.panel.setBackground(Color.WHITE);

        //Insert the component to the panel
        this.panel.add(this.northPanel(), BorderLayout.NORTH);
        this.panel.add(this.centralPanel(), BorderLayout.CENTER);
        this.panel.add(this.southPanel(), BorderLayout.SOUTH);

        //Fix last setting
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(this.panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * @return the panel to set as central panel.
     */
    private JPanel centralPanel() {
        final JPanel panel = new JPanel();

        //Set border of my panel
        final Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
                CENTRAL_TEXT, TitledBorder.CENTER, TitledBorder.TOP);
        panel.setBorder(border);

        //Set layout and background color for the panel
        panel.setLayout(new GridLayout(ROWS, COLS));
        panel.setBackground(Color.WHITE);

        //Insert components for setting name
        panel.add(this.name);
        panel.add(this.nameText);
        panel.add(this.nameError);

        //Fix nameError's setting
        this.nameError.setForeground(Color.RED);
        this.nameError.setVisible(false);

        //Insert components for setting length
        panel.add(this.length);
        panel.add(this.lengthText);
        panel.add(this.lengthError);

        //Fix lengthError's setting
        this.lengthError.setForeground(Color.RED);
        this.lengthError.setVisible(false);

        //Insert component over14
        panel.add(this.over14);

        //Create ButtonGroup for Over14
        final ButtonGroup bgOver14 = new ButtonGroup();

        //Insert JRadioButton Yes of Over14
        final JRadioButton b1 = new JRadioButton("Yes", false);
        b1.addActionListener(e -> this.isOver14 = true);
        b1.setBackground(Color.WHITE);
        bgOver14.add(b1);
        panel.add(b1);

        //Insert JRadioButton No of Over14
        final JRadioButton b2 = new JRadioButton("No", true);
        b2.addActionListener(e -> this.isOver14 = false);
        b2.setBackground(Color.WHITE);
        bgOver14.add(b2);
        panel.add(b2);

        //Insert component threeDim
        panel.add(this.threeDim);

        //Create ButtonGroup for threeDim
        final ButtonGroup bg3D = new ButtonGroup();

        //Insert JRadioButton Yes of threeDim
        final JRadioButton b3 = new JRadioButton("Yes", false);
        b3.addActionListener(e -> this.is3D = true);
        b3.setBackground(Color.WHITE);
        bg3D.add(b3);
        panel.add(b3);

        //Insert JRadioButton No of threeDim
        final JRadioButton b4 = new JRadioButton("No", true);
        b4.addActionListener(e -> this.is3D = false);
        b4.setBackground(Color.WHITE);
        bg3D.add(b4);
        panel.add(b4);

        //Insert components for setting genre
        this.insertInJComboBox();
        panel.add(this.genre);
        panel.add(this.cbox);
        panel.add(this.genreError);

        //Fix genreError's setting
        this.genreError.setForeground(Color.RED);
        this.genreError.setVisible(false);

        return panel;
    }

    /**
     * This method insert the value of enumeration Genre in JComboBox.
     */
    private void insertInJComboBox() {
        //Create Array with every type of genre
        final Genre[] genres = Genre.values();

        //Insert in JComboBox genres
        this.cbox = new JComboBox<>(genres);
        //Add an actionListener to set the selectedGenre
        this.cbox.addActionListener(e -> this.selectedGenre = (Genre) this.cbox.getSelectedItem());

        //Fix last setting
        this.cbox.setBackground(Color.WHITE);
        this.cbox.setSelectedItem(null);
    }

    /**
     * @return the ActionListen for the submit button.
     */
    private ActionListener listenerForSubmit() {
        return new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    //Create a new film  whit the parameters extract from the buttons
                    final String name = nameText.getText();
                    final int length = Integer.parseInt(lengthText.getText());
                    final Genre genre = Genre.valueOf(selectedGenre.name());
                    ViewImpl.getController().buyNewFilm(selectedRoom, name, length, genre, isOver14, is3D);

                    //Set the GUI not visible and return to the ownerMenu
                    setVisible(false);
                    ViewImpl.getView().ownerMenu();
                } catch (Exception e) {
                    nameError.setVisible(true);
                    lengthError.setVisible(true);
                    genreError.setVisible(true);
                }
            }
        };
    }

    /**
     * @return the panel to set as northern panel.
     */
    private JPanel northPanel() {
        //Create the northern panel
        final JPanel panel = new JPanel();

        //Set border of my panel
        final Border b2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                NORTHERN_TEXT, TitledBorder.CENTER, TitledBorder.TOP);
        panel.setBorder(b2);

        //Set background color for the panel
        panel.setBackground(Color.WHITE);

        final ButtonGroup bgNorth = new ButtonGroup();

        //Insert one button for each room in panel
        final int rooms = ViewImpl.getController().addRoomList().size();

        for (int room = 0; room < rooms; room++) {
            final JRadioButton button = new JRadioButton("Sala n° " + (room + 1), false);
            button.setName(String.valueOf(room));
            button.addActionListener(e -> {
                //Set the selectedRoom and enabled submit button
                this.selectedRoom = Integer.parseInt(button.getName());
                this.submit.setEnabled(true);
            });
            bgNorth.add(button);
            panel.add(button);
        }
        return panel;
    }

    /**
     * @return the panel to set as southern panel.
     */
    private JPanel southPanel() {
        //Create the souther panel
        final JPanel panel = new JPanel();

        //Set border of my panel
        final Border b2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
                SOUTHERN_TEXT, TitledBorder.CENTER, TitledBorder.TOP);
        panel.setBorder(b2);

        //Set background color for the panel
        panel.setBackground(Color.WHITE);

        //Add submit and back to panel
        panel.add(this.submit);
        panel.add(this.back);

        //Add actionListener to back button
        this.back.addActionListener(e -> {
            this.setVisible(false);
            ViewImpl.getView().ownerMenu();
        });

        //Add actionListener to submit and set it disable
        this.submit.addActionListener(this.listenerForSubmit());
        this.submit.setEnabled(false);

        return panel;
    }
}
