package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * The view for the selection of movie to watch.
 */
public class SelectFilm extends JFrame {

    private static final long serialVersionUID = 1273760231184206216L;

    private static final double HEIGHT_PERC = 0.15;
    private static final double WIDTH_PERC = 0.3;
    private static final String TEXT = "Select the movie you want to see";
    private static final String TEXT_DIALOG_ONE = "There aren't enough free seats for the selected movie"
            + ", please choose another one";
    private static final String TEXT_DIALOG_TWO = "You have selected an Under14 forbidden movie"
            + " with an Under14 booked tickets!";
    private static final String TITLE = "Select Movie";

    private final JButton mainMenu = new JButton("Main Menu'");
    private final JButton submit = new JButton("submit");
    private final JPanel panel = new JPanel();

    private JComboBox<String> cbox;
    private String selectedFilm;

    /**
     * Build a new GUI for choose the movie to watch.
     * @param nTickets the parameter to know the number of booked tickets.
     * @param nUnder14 the parameter to know the number of Under14 booked tickets.
     */
    public SelectFilm(final int nTickets, final int nUnder14) {
        super();

        //Set dimension of the window
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));

        //Set background color for the panel
        this.panel.setBackground(Color.WHITE);

        //Set border of my panel
        final Border b2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                TEXT, TitledBorder.CENTER, TitledBorder.TOP);
        this.panel.setBorder(b2);

        //Insert the component to the panel
        this.insertFilmList();
        this.panel.add(this.cbox);
        this.panel.add(this.submit);
        this.panel.add(this.mainMenu);

        //Add actionListener to submit button
        this.submit.addActionListener(e -> {
            //Set the selectedRoom, call the view's method selectSeatsInRoom and set this GUI not visible
            final int selectedRoom = ViewImpl.getController().searchRoom(this.selectedFilm, nTickets, nUnder14);
            if (selectedRoom >= 0) {
                this.setVisible(false);
                ViewImpl.getView().selectSeatsInRoom(selectedRoom, nTickets);
            } else if (selectedRoom == -1) {
                //If selectedRoom is less then 0, show a message dialog
                JOptionPane.showMessageDialog(this, TEXT_DIALOG_ONE, TITLE, JOptionPane.DEFAULT_OPTION);
            } else {
                //If selectedRoom is less then 0, show a message dialog
                JOptionPane.showMessageDialog(this, TEXT_DIALOG_TWO, TITLE, JOptionPane.DEFAULT_OPTION);
            }
        });

        //Add actionListener to mainMenu button
        this.mainMenu.addActionListener(e -> {
            this.setVisible(false);
            ViewImpl.getView().startingView();
        });

        //Fix last setting
        this.submit.setEnabled(false);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(this.panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method insert the list of films' name in JComboBox.
     */
    private void insertFilmList() {
        //Insert the films' title in JComboBox
        final List<String> film = ViewImpl.getController().getScreenedTitlesList();
        final String[] jb = new String[film.size()];

        for (int i = 0; i < film.size(); i++) {
            jb[i] = film.get(i);
        }
        this.cbox = new JComboBox<>(jb);

        //Add ActionListener to JComboBox
        this.cbox.addActionListener(e -> {
            //Set the selectedFilm and enabled submit button
            this.selectedFilm = (String) cbox.getSelectedItem();
            this.submit.setEnabled(true);
        });

        //Set Background color and selectedItem for cbox
        this.cbox.setBackground(Color.WHITE);
        this.cbox.setSelectedItem(null);
    }
}
