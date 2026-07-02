package it.unibo.bmbman.model.entities;

import it.unibo.bmbman.model.collision.Collision;
import it.unibo.bmbman.model.utilities.BombState;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.model.utilities.Velocity;
/**
 * Representing a hero in our application.
 */
public class HeroImpl extends AbstractLivingEntity implements Hero {
    private Double velocityModifier = 1.0;
    private static final int MILLIS = 1000;
    private static final int IMMUNITY_DURATION = 1;
    private long lastCollision;
    private boolean gotKey;
    private boolean win;
    private int bombsNumber;
    private int bombRange;
    private static final int START_POSITION = 50;
    private static final int DIMX = 48;
    private static final int DIMY = 48;
    private static final int NLIVES = 3;
    /**
     * Construct a Hero in game.
     */
    public HeroImpl() {
        super(new Position(START_POSITION, START_POSITION), EntityType.HERO, new Dimension(DIMX, DIMY), NLIVES);
        this.bombsNumber = 1;
        this.bombRange = 3;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onCollision(final Collision c) {
        switch (c.getReceiver().getType()) {
        case BOMB:
            if (((BombImpl) c.getReceiver()).getState() == BombState.IN_EXPLOSION 
            && System.currentTimeMillis() / MILLIS - lastCollision > IMMUNITY_DURATION) {
                    removeLife();
                    lastCollision = System.currentTimeMillis() / MILLIS;
            }
            break;
        case MONSTER:
            if (System.currentTimeMillis() / MILLIS - lastCollision > IMMUNITY_DURATION) {
                removeLife();
                lastCollision = System.currentTimeMillis() / MILLIS;
            }
            this.setPosition(c.getPosition());
            break;
        default:
            this.setPosition(c.getPosition());
            break;
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        switch (getDirection()) {
        case IDLE:
            setVelocity(Velocity.ZERO);
            break;
        case UP:
            setVelocity(new Velocity(0, (int) (-Velocity.SPEED * velocityModifier)));
            break;
        case DOWN:
            setVelocity(new Velocity(0, (int) (Velocity.SPEED * velocityModifier)));
            break;
        case LEFT:
            setVelocity(new Velocity((int) (-Velocity.SPEED * velocityModifier), 0));
            break;
        case RIGHT:
            setVelocity(new Velocity((int) (Velocity.SPEED * velocityModifier), 0));
            break;
        default:
            break;
        }
    }
    /**
     * Set the velocity modifier field.
     * @param modifier the new value.
     */
    public void setVelocityModifier(final Double modifier) {
        this.velocityModifier = modifier;
    }
    /**
     * Used to set if the hero has got the key.
     */
    public void setKey() {
        this.gotKey = true;
    }
    /**
     * Used to know if the hero has got the key.
     * @return a boolean 
     */
    public boolean hasKey() {
        return this.gotKey;
    }
    /**
     * {@inheritDoc}
     */
    public void checkWin() {
        win = gotKey;
    }
    /**
     * {@inheritDoc}
     */
    public boolean hasWon() {
        return win;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementBombsNumber() {
       this.bombsNumber++;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setBombRange(final int range) {
        this.bombRange = range;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getBombsNumber() {
        return this.bombsNumber;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getBombRange() {
        return this.bombRange;
    }
}
