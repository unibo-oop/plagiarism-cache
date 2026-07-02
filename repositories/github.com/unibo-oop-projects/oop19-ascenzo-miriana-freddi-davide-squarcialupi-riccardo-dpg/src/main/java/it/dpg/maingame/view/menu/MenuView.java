package it.dpg.maingame.view.menu;

import it.dpg.maingame.view.View;

/**
 * Interface for the MenuGUI
 *
 * @author Riccardo Squarcialupi
 */
public interface MenuView extends View {

    /**
     * display credit in a new scene
     */
    void displayCredit();

    /**
     * display options in a new scene
     */
    void displayOptions();


}
