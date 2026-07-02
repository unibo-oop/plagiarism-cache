package it.unibo.oop18.cfc.Physics;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

import it.unibo.oop18.cfc.Objects.Items.Item;
import it.unibo.oop18.cfc.TileMap.Tile;
import it.unibo.oop18.cfc.Util.Velocity;

public interface DynamicPhysicsComponent {

	void stop();

	void move();
	
    /**
     * Gets the dynamic object's velocity.
     *
     * @return the dynamic object {@link VelocityImpl}.
     */
    Velocity getVelocity();
    
    /**
     * Gets an entity upper shape.
     *
     * @return a rectangle to check a upper bound collision
     */
    Rectangle2D getTopBound();

    /**
     * Gets an entity lower shape.
     *
     * @return a rectangle to check a lower bound collision
     */
    Rectangle2D getLowerBound();

    /**
     * Gets an entity left shape.
     *
     * @return a rectangle to check a left bound collision
     */
    Rectangle2D getLeftBound();

    /**
     * Gets an entity right shape.
     *
     * @return a rectangle to check a right bound collision
     */
    Rectangle2D getRightBound();
    
    /**
     * {@inheritDoc}
     */
    public abstract void checksCollisions(Set<Item> objects);


}
