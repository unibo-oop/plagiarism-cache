package model.worldevent;

/**
 * Event generated when player kill one enemy.
 * Allow to update the score in GameState. 
 */
public class PlayerKillEnemy implements WorldEvent {

    private final int point;

    /**
     * Constructor for this class.
     * @param point Points contained in enemy killed.
     */
    public PlayerKillEnemy(final int point) {
        this.point = point;
    }

    /**
     * 
     * @return points of enemy killed.
     */
    public int getPoint() {
        return point;
    }
}
