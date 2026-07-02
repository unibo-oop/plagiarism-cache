package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.EntityType;

/**
 * This class represents the "Bunker" entity.
 */
public class Bunker extends AbstractEntity {

    /**
     * Creates a new Bunker entity.
     * 
     * @param type   the type of the bunker.
     * @param x      the initial x coordinate.
     * @param y      the initial y coordinate.
     * @param width  the witdh of the bunker.
     * @param height the height of the bunker.
     * @param health the initial health of the bunker.
     * 
     */
    public Bunker(final EntityType type, final int x, final int y, final int width, final int height,
            final int health) {
        super(type, x, y, width, height, health);
    }

}
