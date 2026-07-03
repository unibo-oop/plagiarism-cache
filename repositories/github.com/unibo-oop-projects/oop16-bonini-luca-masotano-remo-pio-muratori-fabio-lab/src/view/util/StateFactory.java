package view.util;

import view.state.ViewState;

/**
 * Represents a factory of state objects.
 */
public interface StateFactory {

    /**
     * Creates and return a CreditsState object.
     * 
     * @return a new WindowState object
     */
    ViewState createCreditsState();

    /**
     * Creates and return a GameOverState object.
     * 
     * @return a new WindowState object
     */
    ViewState createGameOverState();

    /**
     * Creates and return a HelpState object.
     * 
     * @return a new WindowState object
     */
    ViewState createHelpState();

    /**
     * Creates and return a OptionsState object.
     * 
     * @return a new WindowState object
     */
    ViewState createOptionsState();

    /**
     * Creates and return a PauseState object.
     * 
     * @return a new WindowState object
     */
    ViewState createPauseState();

    /**
     * Creates and return a MenuState object.
     * 
     * @return a new MenuState object
     */
    ViewState createMenuState();

    /**
     * Creates and return a GameState object.
     * 
     * @return a new GameState object
     */
    ViewState createGameState();

}
