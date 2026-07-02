package rogue.model.creature;

import java.util.concurrent.ThreadLocalRandom;

import javafx.util.Pair;
import rogue.model.items.potion.Potion;
import rogue.model.world.Direction;

/**
 * An implementation for a {@link Monster}. 
 *
 */

public class MonsterImpl implements Monster {

    private final MonsterType type;

    public MonsterImpl(final MonsterType type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MonsterLife getLife() {
        return this.type.getLife();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MonsterType getMonsterType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAC() {
        return this.type.getAC();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getDamage() {
        return this.type.getDamage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Special getSpecial() {
        return this.type.getSpecial();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMoney() {
        return this.type.getMoney();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Potion getItem() {
        return this.type.getItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemChange() {
        return this.type.getItemChange();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int attackDamage() {
        return ThreadLocalRandom.current().nextInt(this.getDamage().getKey(), this.getDamage().getValue() - this.getDamage().getKey() + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int dropItem() {
       return ThreadLocalRandom.current().nextInt(0, 100);
    }

    private Direction randomMove() {
        final int direction = ThreadLocalRandom.current().nextInt(0, 4);
        switch (direction) {
        case 1:
            return Direction.NORTH;
        case 2:
            return Direction.EAST;
        case 3:
            return Direction.SOUTH;
        case 4:
            return Direction.WEST;
        default:
            return Direction.NONE;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction monsterMove(final Direction playerDirection) {
        if (this.getSpecial().isFlyingRandom()) {
            return this.randomMove();
        } else if (this.getSpecial().isHostile()) {
            return playerDirection;
        } else {
            return Direction.NONE;
        }
    }

}
