package towerDefense.entities.api;

import java.awt.Point;
import java.awt.Rectangle;

public interface Entity {

    /**
     * @return the current position of the entity, expressed by 2 coordinates
     */
    public Point getPosition();

    /**
     * @return the speed of the entity
     */
    public int getSpeed();

    /**
     * @return the current HP of the entity
     */
    public int getHp();

    /**
     * @return  the damage that the entity deals
     */
    public int getDamage();

    /**
     * Calculates the incoming damage from another entity's attack
     * 
     * @param value
     *          the amount of incoming damage
     */
    public void incomeDamage(int value);

    /**
     * @return the hitbox of the entity, expressed by a rectangle
     */
    public Rectangle getHitbox();
}
