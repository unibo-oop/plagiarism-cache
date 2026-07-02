package model.worldevent;

/**
 * Event generated when the player gain/lose heart points.
 */
public class PlayerHeartChange implements WorldEvent {

    private final int life;

    /**
     * Constructor for this event class.
     * @param life player's life.
     */
    public PlayerHeartChange(final int life) {
        this.life = life;
    }

    /**
     * Return new player life.
     * @return player's life.
     */
    public int getCurretLife() {
        return life;
    }
}
