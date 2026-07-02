package model.minigames;

/**
 * The interface to change MiniGames' settings.
 *
 */
public interface MiniGameSettings {

    /**
     * 
     * @return the grid size
     */
    int getGridSize();

    /**
     * 
     * @return the timer duration
     */
    int getSeconds();
}
