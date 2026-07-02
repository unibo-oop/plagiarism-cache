package tmw.controller.entities;

import tmw.common.Dim2D;
import tmw.model.entities.GameEntity;

/**
 * This interface represents the base for any entity controller.
 *
 * @param <T> - the entity class that the controller handle
 */
public interface EntityController<T extends GameEntity> {

    /**
     * This method is called to update the entity controlled by the controller.
     */
    void update();

    /**
     * This method is called when the entity has to be draw on the screen.
     */
    void draw();

    /**
     * This method is called to get the {@link GameEntity} controlled by the
     * controller.
     * 
     * @return the entity controlled by the controller
     */
    T getEntity();

    /**
     * This method is called when the entity has to be removed from the game.
     */
    void delete();

    /**
     * This method is called when the window is resized and the entity has to be
     * resized.
     * 
     * @param newDimension - The new dimension of the window that is used to
     *                     calculate the new dimension for the entity
     */
    void resizeEntity(Dim2D newDimension);
}
