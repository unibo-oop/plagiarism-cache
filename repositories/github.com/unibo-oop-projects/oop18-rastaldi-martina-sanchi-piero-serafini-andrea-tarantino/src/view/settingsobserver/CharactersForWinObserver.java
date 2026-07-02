package view.settingsobserver;

import controller.Controller;
import javafx.scene.control.ComboBox;

/**
 *
 * Chiara Tarantino. 
 * A class that represents an observer for combobox that
 * contains the number of characters to bring to the temple for win.
 *
 */
public class CharactersForWinObserver implements Observer {

    private final ComboBox<Integer> cfwComboBox;
    private final Controller controller;

    /**
     * Class constructor.
     *
     * @param charactersForWin
     *            combobox that contains the number of characters to bring to the
     *            temple for win.
     * @param controller
     *            controller
     */
    public CharactersForWinObserver(final ComboBox<Integer> charactersForWin, final Controller controller) {
        this.cfwComboBox = charactersForWin;
        this.controller = controller;
    }

    @Override
    public final void updateSettings() {
        this.controller.setCharactersForWin(this.cfwComboBox.getValue());
    }

}
