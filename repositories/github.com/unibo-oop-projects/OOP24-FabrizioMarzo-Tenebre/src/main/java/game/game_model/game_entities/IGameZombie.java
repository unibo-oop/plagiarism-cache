package game.game_model.game_entities;

import model.entities.zombie.Zombie;
import view.graphics.GraphicsZombie;

/**
 * Interface representing a game zombie entity.
 * Provides methods to access the zombie model and update its graphical
 * representation.
 */
public interface IGameZombie {

    /**
     * Returns the underlying {@link Zombie} model object.
     *
     * @return the zombie instance
     */
    Zombie getZombie();

    /**
     * Updates the graphical representation of the zombie.
     *
     * @param graphZob the {@link GraphicsZombie} graphics context used to render
     *                 the zombie
     */
    void updateGraphics(final GraphicsZombie graphZob);

}
