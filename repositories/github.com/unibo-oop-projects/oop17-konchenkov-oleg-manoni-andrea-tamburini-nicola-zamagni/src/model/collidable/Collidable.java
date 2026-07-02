package model.collidable;

import model.physics.collider.Polycollider2D;

/**
 * Represents a collidable objects.
 * 
 * @author Nicola Tamburini
 *
 */
public interface Collidable {

    /**
     * 
     * @return
     *  a {@code Polycollider2D}
     */
    Polycollider2D getPolycollider();
}
