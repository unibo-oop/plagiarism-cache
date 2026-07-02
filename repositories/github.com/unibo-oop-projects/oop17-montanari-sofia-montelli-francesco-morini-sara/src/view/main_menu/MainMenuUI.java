package view.main_menu;

/**
 * Interface for the main menu ui.
 */
public interface MainMenuUI {

    /**
     * new game set up event.
     */
    void newGameSetup();

    /**
     * new game classic event.
     */
    void newGameClassic();

    /**
     * Show some initial statistics.
     */
    void statistics();

    /**
     * 
     */
    void credits();
}
