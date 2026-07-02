package game.game_model.game_entities;

import model.entities.zombie.Zombie;
import view.graphics_component.zombie.GraphicsClickerComponent;

/**
 * {@inheritDoc}
 */
public class FactoryZombieGame implements IFactoryZombieGame {

    /**
     * {@inheritDoc}
     */
    public IGameZombie gameZombieClicker(final Zombie clicker) {
        return new GameZombie(clicker,
                new GraphicsClickerComponent(clicker.getClass().getSimpleName()));
    }
}
