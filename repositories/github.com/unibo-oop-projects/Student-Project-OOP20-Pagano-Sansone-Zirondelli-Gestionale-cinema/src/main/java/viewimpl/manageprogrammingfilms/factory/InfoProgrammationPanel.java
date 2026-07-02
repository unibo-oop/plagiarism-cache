package viewimpl.manageprogrammingfilms.factory;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.managefilms.FilmsController;
import utilities.factory.Film;
import utilitiesimpl.Hall;
/**
 * Describe a panel with info of programmation.
 *  */
public final class InfoProgrammationPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_STRING_PRICE = "3.4 e.g. (euro)";
    private static final int TOP_EMPTY_BORDER = 10;
    private static final int LEFT_EMPTY_BORDER = 10;
    private static final int BOTTOM_EMPTY_BORDER = 10;
    private static final int RIGHT_EMPTY_BORDER = 10;
    private static final int ROWS_GRID = 4;
    private static final int COLS_GRID = 0;
    private static final int HGAP_GRID = 5;
    private static final int VGAP_GRID = 5;

    private final JTextField price;
    private final JComboBox<String> films = new JComboBox<>();
    private final JComboBox<Hall> halls;

    private final FilmsController filmsController;
    // final private FilmsController hallsController;

    private final Map<Integer, Film> map = new HashMap<>(); // map selectedIndexItem to filmId

    InfoProgrammationPanel(final FilmsController filmsController) {
        this.filmsController = filmsController;
        halls = new JComboBox<>(Hall.values());

        this.fillMap(); // association between indexSelectionComboBox and Film
        this.fillComboBox();
        price = new JTextField(DEFAULT_STRING_PRICE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Info"), BorderFactory
                .createEmptyBorder(TOP_EMPTY_BORDER, LEFT_EMPTY_BORDER, BOTTOM_EMPTY_BORDER, RIGHT_EMPTY_BORDER)));
        add(getLabelsPanel(), BorderLayout.WEST);
        add(getTextFieldPanel(), BorderLayout.CENTER);
        price.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(final FocusEvent e) {
                if (DEFAULT_STRING_PRICE.equals(price.getText())) {
                    price.setText("");
                }

            }

            @Override
            public void focusLost(final FocusEvent e) {
            }
        });
    }
    /** 
     * Get inserted price.
     * @throws NumberFormatException number format exception
     * @return price 
     * */
    public String getPrice() {

        try {
            Double.parseDouble(price.getText());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Price must be number! Insert '.' between integer and decimal parts! ");
        }

        return price.getText();
    }
    /** 
     * Set inserted price.
     * @param priceValue price to set
     * */
    public void setPrice(final String priceValue) {
        price.setText(priceValue);
        price.setFocusable(true);
        price.selectAll();
    }
    /** 
     * Get hall .
     * @return selectedHall selected hall
     * @throws IllegalArgumentException illegal argument exception
     * */
    public Hall getHall()  {
        final Hall selectedHall = (Hall) halls.getSelectedItem();
        if (selectedHall != null) {
            return selectedHall;
        }
        throw new IllegalArgumentException("Please select hall");

    }
    /** 
     * Get film name .
     * @return selectedFilm film name
     * @throws IllegalArgumentException illegal argument exception
     * */
    public String getFilmName()  {
        String selectedFilm;
        try {
            selectedFilm = films.getSelectedItem().toString();
            return selectedFilm;
        } catch (Exception e) {
            throw new IllegalArgumentException("Please select film");
        }

    }
    /** 
     * Get index selection .
     * @return film selected index
     * */
    public int getSelectedIndex() {
        return films.getSelectedIndex();
    }
    /** 
     * Get labels panel. 
     * @return labelPanel 
     * */
    private JPanel getLabelsPanel() {
        final JPanel labelPanel = new JPanel(new GridLayout(ROWS_GRID, COLS_GRID, HGAP_GRID, VGAP_GRID)); 
        labelPanel.add(new JLabel("Price"));
        labelPanel.add(new JLabel("Hall"));
        labelPanel.add(new JLabel("Film"));
        return labelPanel;
    }
    /** 
     * Get panel with text field. 
     * @return textFieldPanel 
     * */

    private JPanel getTextFieldPanel() {
        final JPanel textFieldPanel = new JPanel(new GridLayout(ROWS_GRID, COLS_GRID, HGAP_GRID, VGAP_GRID));
        textFieldPanel.add(price);
        textFieldPanel.add(halls);
        textFieldPanel.add(films);
        return textFieldPanel;
    }
    /** 
     * Fill map with films. 
     * */
    private void fillMap() {

        final List<Film> films = new ArrayList<>();
        films.addAll(filmsController.getFilms());
        films.sort((f1, f2) -> f1.getName().compareTo(f2.getName()));

        int i = 0;
        for (final Film film : films) {
            map.put(i, film);
            i++;
        }
    }
    /** 
     * Fill combo box with films. 
     * */
    private void fillComboBox() {
        final List<String> list = new ArrayList<>();
        for (final Film film : map.values()) {
            list.add(film.getName() + " id: " + film.getID());
        }
        films.setModel(new DefaultComboBoxModel(list.toArray()));
    }
    /** 
     * Get selected film name .
     * @return selectedFilm selected film name
     * @throws IllegalArgumentException illegal argument exception
     * */
    public Film getSelectedFilm() {
        final Film selectedFilm = map.get(this.getSelectedIndex());

        if (selectedFilm != null) {
            return selectedFilm;
        } else {
            throw new IllegalArgumentException("Please selected a film");
        }
    }

    /** 
     * Update map and combo box.
     */
    public void update() {
        this.fillMap();
        this.fillComboBox();
    }

    /** 
     * Reset panel.
     * */
    public void reset() {
        this.setPrice(DEFAULT_STRING_PRICE);
        this.halls.setSelectedItem(null);
        this.films.setSelectedItem(null);
    }
}