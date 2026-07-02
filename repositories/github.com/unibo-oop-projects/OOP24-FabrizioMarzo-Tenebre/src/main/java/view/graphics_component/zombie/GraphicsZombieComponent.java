package view.graphics_component.zombie;

import model.entities.zombie.Zombie;
import view.graphics.GraphicsZombie;

/**
 * Interface for components responsible for updating
 * the graphical representation of a Zombie entity.
 */
public interface GraphicsZombieComponent {

    /**
     * Updates the graphics of the given Zombie using the provided GraphicsZombie
     * instance.
     *
     * @param zob      the Zombie entity to update graphics for
     * @param graphZob the graphics handler used to draw the Zombie
     */
    void update(final Zombie zob, final GraphicsZombie graphZob);
}
