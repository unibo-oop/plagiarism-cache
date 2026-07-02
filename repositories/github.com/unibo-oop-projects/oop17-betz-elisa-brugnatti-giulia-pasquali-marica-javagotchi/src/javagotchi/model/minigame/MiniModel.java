package javagotchi.model.minigame;

import javagotchi.model.Javagotchi;

/**
 * 
 * @author marica
 *
 */
public interface MiniModel {

    /**
     * 
     * @author marica
     *
     */
    enum GameState {
        NULL, START, OVER, PAUSE;
    }

    /**
     * Gets Javagotchi chosen.
     * 
     * @return {@link javagotchi.model.Javagotchi}
     */
    Javagotchi getGotchi();

    /**
     * Sets the javagotchi chosen.
     * 
     * @param java
     *            {@link javagotchi.model.Javagotchi}
     */
    void setGotchi(Javagotchi java);

    /**
     * Gets GameGrid.
     * 
     * @return {@link GameGrid}
     */
    GameGrid getGameGrid();

    /**
     * Gets Time.
     * 
     * @return {@link Time}
     */
    Time getTime();

    /**
     * Sets Time.
     * 
     * @param time
     *            {@link Time}
     */
    void setTime(Time time);

    /**
     * Gets Score.
     * 
     * @return {@link Score}
     */
    Score getScore();

    /**
     * Sets Score.
     * 
     * @param score
     *            {@link Score}
     */
    void setScore(Score score);

    /**
     * Sets the game state.
     * 
     * @param state
     *            {@link GameState}
     */
    void setGameState(GameState state);

    /**
     * Indicates if current game state is equals state.
     * 
     * @param state
     *            {@link GameState}
     * @return true if current game state is equals state
     */
    boolean isGameState(GameState state);

    /**
     * Initializes the model components.
     */
    void initModel();

    /**
     * Indicates if it is the moment to add time.
     * 
     * @return true if it is the moment to add time
     */
    boolean isMomentToAddTime();
}
