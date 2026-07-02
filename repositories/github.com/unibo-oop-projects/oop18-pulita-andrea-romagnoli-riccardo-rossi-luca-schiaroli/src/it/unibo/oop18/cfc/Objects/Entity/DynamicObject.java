package it.unibo.oop18.cfc.Objects.Entity;

import java.awt.Graphics2D;

import it.unibo.oop18.cfc.Objects.GameObject;
import it.unibo.oop18.cfc.Physics.DynamicPhysicsComponent;
import it.unibo.oop18.cfc.TileMap.TileMap;

/**
 *  Interface for moving objects.
 */
public interface DynamicObject extends GameObject{

    /**
     * Process input entity.
     */
    void update();

    /**
     * Gets the entity's physics.
     * 
     * @return a physics component.
     */
    DynamicPhysicsComponent getPhysics();

    /**
     * Gets the world.
     * 
     * @return world that contains {@code AbstractEntity}
     */
    TileMap getTileMap();
    
    /**
     * Renders object's sprite.
     * 
     * @param g component that draw the sprite
     */
    void draw(Graphics2D g);

}
