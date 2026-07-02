package zombieversity.model.entities;

import java.util.Set;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import zombieversity.model.entities.weapon.LongRangeWeapon;
import zombieversity.model.entities.weapon.ShortRangeWeapon;

/**
 * 
 * The interface to model a player.
 *
 */
public interface Player extends ActiveLivingEntity {

    /**
     * 
     * @param obstacles .
     */
    void setObstacles(Set<BoundingBox> obstacles);

    /**
     * @return The main weapon.
     */
    LongRangeWeapon getFirstWeapon();

    /**
     * @return The second weapon.
     */
    ShortRangeWeapon getSecondWeapon();

    /**
     * 
     * @param damage dealt.
     */
    void hitPlayer(int damage);

    /**
     * Used to check if the player is going to collide with an obstacle.
     * 
     * @param nextPos vel.
     */
    void checkCollision(Point2D nextPos);

    /**
     * Used to compute player direction.
     * 
     * @param pos    direction.
     * @param offset .
     */
    void computeAngle(Point2D pos, Point2D offset);
}
