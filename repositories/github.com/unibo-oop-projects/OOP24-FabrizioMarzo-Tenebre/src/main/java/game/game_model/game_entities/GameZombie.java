package game.game_model.game_entities;

import model.entities.zombie.Zombie;
import view.graphics.GraphicsZombie;
import view.graphics_component.zombie.GraphicsZombieComponent;

/**
 * Implementation of the {@link IGameZombie} interface.
 * Represents a game zombie entity that manages the zombie's state and graphical
 * representation.
 */
public class GameZombie implements IGameZombie {

    private Zombie zob;
    private GraphicsZombieComponent imgZob;

    /**
     * Constructs a {@link GameZombie} with the specified zombie model and graphics
     * component.
     *
     * @param zob    the {@link Zombie} model object
     * @param imgZob the {@link GraphicsZombieComponent} responsible for rendering
     *               the zombie
     */
    public GameZombie(final Zombie zob, final GraphicsZombieComponent imgZob) {
        this.zob = zob;
        this.imgZob = imgZob;
    }

    /**
     * Returns the {@link Zombie} model associated with this game zombie.
     *
     * @return the zombie model object
     */
    @Override
    public Zombie getZombie() {
        return this.zob;
    }

    /**
     * Updates the zombie's graphical representation.
     *
     * @param graphZob the {@link GraphicsZombie} graphics context used for
     *                 rendering
     */
    @Override
    public void updateGraphics(final GraphicsZombie graphZob) {
        imgZob.update(this.getZombie(), graphZob);
    }

}
