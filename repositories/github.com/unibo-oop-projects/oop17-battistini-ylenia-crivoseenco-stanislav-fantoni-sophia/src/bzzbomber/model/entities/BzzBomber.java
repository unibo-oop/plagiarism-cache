package bzzbomber.model.entities;

import java.util.Set;

import bzzbomber.controller.collision.BomberCollision;
import bzzbomber.model.ResetType;
import bzzbomber.model.utilities.Direction;

/**
 * The interface of the Hero.
 */
public interface BzzBomber extends LivingCreature {
    /**
     * Method to move BzzBomber in the @GameField .
     * 
     * @param dir
     *            the direction where the hero should go.
     * @param blockSet
     *            the set of block in map.
     * @param bombSet
     *            the set of bomb in map.
     * @param insectSet
     *            the set of insect in map.
     */
    void move(Direction dir, Set<Block> blockSet, Set<Bomb> bombSet, Set<Insects> insectSet);

    /**
     * Method to plant a bomb in @GameField .
     * 
     * @param bombSet
     *            the set of bomb in map.
     */
    void plantBomb(Set<Bomb> bombSet);

    /**
     * Getter for @BomberCollision .
     * 
     * @return the bomberCollision's instance.
     */
    BomberCollision getHeroCollision();

    /**
     * Control the contact with other entity in a @GameField .
     */
    void contactWithEntity();

    /**
     * Add number of enemy killed by bomberman.
     */
    void addEnemyKilled();

    /**
     * Decrement immunity after the hero collide with an @Insect .
     */
    void decrementImmunity();

    /**
     * Getter of enemy killed.
     * 
     * @return the number of enemy killed.
     */
    int getEnemyKilled();

    /**
     * Calculate score.
     * 
     * @return the score that a player has done.
     */
    int calculateScore();

    /**
     * Setter of initial number of @Bomb .
     */
    void setNumberBomb();

    /**
     * Method to reset all field of Hero, so that we can restart a match.
     * 
     * @param rt
     *            The reset type.
     */
    void reset(ResetType rt);
}
