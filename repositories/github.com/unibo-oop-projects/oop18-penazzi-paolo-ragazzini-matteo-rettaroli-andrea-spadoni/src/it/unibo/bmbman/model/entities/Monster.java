package it.unibo.bmbman.model.entities;

import java.util.Random;

import it.unibo.bmbman.model.collision.Collision;
import it.unibo.bmbman.model.utilities.BombState;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.Direction;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;

/**
 * Class that model a monster.
 */
public class Monster extends AbstractLivingEntity {

    private final Random rand = new Random();
    private static final int DIM = 48;
    private static final int NLIVES = 1;

    /**
     * Create a monster.
     * @param position start position of the monster
     */
    public Monster(final Position position) {
        super(position, EntityType.MONSTER, new Dimension(DIM, DIM), NLIVES);
        this.setDirection(randomDirection());
    }
    /**
     * Method used to generate a random direction.
     * @return a new direction
     */
    private Direction randomDirection() {
        final int dir = rand.nextInt(4);
        Direction d = this.getDirection();
        switch (dir) {
        case 0 : d = Direction.DOWN;
        break;
        case 1 : d = Direction.UP;
        break;
        case 2 : d = Direction.LEFT;
        break;
        case 3 : d = Direction.RIGHT;
        break;
        default :
            break;
        }
        return d;
    } 
    /**
     * {@inheritDoc}
     */
    @Override
    public void onCollision(final Collision c) {
        switch (c.getReceiver().getType()) {
        case BOMB:
            if (((BombImpl) c.getReceiver()).getState() == BombState.IN_EXPLOSION) {
                removeLife();
            } else {
                setDirection(randomDirection());
                this.setPosition(c.getPosition());
            }
            break;
        case HERO:
            setDirection(Direction.getOpposite(getDirection()));
            this.setPosition(c.getPosition());
            break;
        case POWER_UP:
            break;
        default:
            setDirection(randomDirection());
            this.setPosition(c.getPosition());
            break;
        }
    }

}
