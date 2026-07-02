package model.world_state;

public interface GameState {

    /**
     * 
     * @return true if the value of the word position is equal to the unicorn one
     * 
     */
    boolean isGameOver();

    /**
     * 
     * @return true if the game is actually in a state of pause.
     */
    boolean isPauseState();

    /**
     * 
     * @param pause
     * 
     */
    void setPause(boolean pause);

}
