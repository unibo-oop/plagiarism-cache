package giocoscudetto.view.api.initialize;

import java.awt.Font;

/**
 * This interface represents the default panel of the game, 
 * it contains the default fonts for the title, the buttons and the exit button.
 */
public interface DefaultPanel {

    /**
     * This method returns the font for the title of the game.
     * 
     * @return the font for the title of the game.
     */
    Font getTitleFont();

    /**
     * This method returns the font for the buttons of the game.
     * 
     * @return the font for the buttons of the game.
     */
    Font getButtonFont();

    /**
     * This method returns the font for the exit button of the game.
     * 
     * @return the font for the exit button of the game.
     */
    Font getExitFont();
}
