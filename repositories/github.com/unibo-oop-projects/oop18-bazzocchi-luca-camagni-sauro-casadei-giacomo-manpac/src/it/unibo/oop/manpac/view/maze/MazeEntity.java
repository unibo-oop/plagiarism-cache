package it.unibo.oop.manpac.view.maze;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

import it.unibo.oop.manpac.model.Entity;

/**
 * A generic static entity that lives in the maze.
 */
public interface MazeEntity {

    /**
     * Getter for the reference entity of the model.
     * @return The entity (Enumeration)
     */
    Entity getEntity();
    /**
     * Getter for the fixture of the entity.
     * 
     * @return The fixture
     */
    Fixture getFixture();

    /**
     * Getter for the body of the entity.
     * 
     * @return The body
     */
    Body getBody();

    /**
     * Setter for the fixture's filter's category bit.
     * 
     * @param filterBit the new category filter
     * @param entity the entity reference for the model
     */
    void setCollisionProperties(int filterBit, Entity entity);

    /**
     * Getter for the cell that contains the entity.
     * 
     * @param index the layer of the tiledMap from which the cell is taken
     * 
     * @return The cell 
     */
    Cell getCell(int index);

}
