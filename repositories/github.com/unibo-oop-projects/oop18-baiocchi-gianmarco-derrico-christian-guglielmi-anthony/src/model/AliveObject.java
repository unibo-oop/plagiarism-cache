package model;

/**
 * Interface for any "alive object" in game (bomb includes).
 */
public interface AliveObject extends GameObject {

    /**
     * Updates entity state.
     * @param elapsedTime : passed time between two consecutive frames
     */
    void update(long elapsedTime);
}
