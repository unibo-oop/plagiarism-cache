package it.unibo.bmbman.model.entities;
/**
 *Inteface to model the hero in our game.
 */
public interface Hero extends LivingEntity {
    /**
     * Used to set that Hero has pick up the key.
     */
    void setKey();
    /**
     * Used to know if hero has already pick up the key.
     * @return true if the hero has the key
     */
    boolean hasKey();
    /**
     * Method called only when the hero reach the door.
     * If he already got the key, he win the match, otherwise do nothing.
     */
    void checkWin();
    /**
     * Used to know if the hero has won.
     * @return true if he has the key and he is in {@link Door} position
     */
    boolean hasWon();
    /**
     * Increment number of bombs that hero can plant.
     */
    void incrementBombsNumber();
    /**
     * Used to know how many bomb the hero can plant.
     * @return number of bombs
     */
    int getBombsNumber();
    /**
     * Used to set the range of bombs.
     * @param range of {@link Bomb}
     */
    void setBombRange(int range);
    /**
     * Used to know the dimension of bombs range.
     * @return range of {@link Bomb}
     */
    int getBombRange();
}
