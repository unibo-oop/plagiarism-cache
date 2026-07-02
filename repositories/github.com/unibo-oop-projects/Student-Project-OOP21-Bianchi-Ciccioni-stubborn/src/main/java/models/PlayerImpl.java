package models;

/**
 * PlayerImpl is a class that implements Player and its contracts.
 * It generate player entity, initialized with a position and set amount of health.
 */
public class PlayerImpl implements Player {

    private Point2D position;
    private int health;
    private static final int MAXHEALTH = 3;

    /**
     * This is the constructor for player.
     * 
     * @param position the initial position of player
     * @param health the initial amount of health of player
     */
    public PlayerImpl(final Point2D position, final int health) {
        this.position = position;
        this.health = health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealth(final int value) {
        if (this.health + value < MAXHEALTH) {
            this.health = this.health + value;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point2D position) {
        this.position = position;
    }

}
