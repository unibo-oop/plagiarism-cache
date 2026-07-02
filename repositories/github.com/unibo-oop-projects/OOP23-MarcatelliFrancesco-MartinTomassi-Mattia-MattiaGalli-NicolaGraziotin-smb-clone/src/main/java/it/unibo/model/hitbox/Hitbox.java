package it.unibo.model.hitbox;

import java.awt.Graphics2D;

/**
 * The Hitbox<T> interface rapresents the real size of an entity
 * and it is a generic Hitbox, T rapresents the shape of it.
 * 
 * @param <T> the shape of the Hitbox
 */
public interface Hitbox<T> {

    /**
     * Update the position of the Hitbox following the entity moves.
     * 
     * @param x coordinate x
     * @param y coordinate y
     */
    void updatePosition(double x, double y);

    /**
     * Returns the Hitbox T.
     * 
     * @return T shape of the hitbox returned
     */
    T getHitbox();

    /**
     * Draw the Hitbox for test.
     * 
     * @param g graphic component
     */
    void draw(Graphics2D g);
}
