package it.dpg.minigames.molegame.model;

public interface Score {
    /**
     * return the final Score of the game
     */
    int finalScore();

    /**
     * when player hit the mole add 1 point to his score
     */
    void addPoint();

}

