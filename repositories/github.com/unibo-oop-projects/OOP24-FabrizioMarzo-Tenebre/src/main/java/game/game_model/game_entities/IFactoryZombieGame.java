package game.game_model.game_entities;

import model.entities.zombie.Zombie;

/**
 * <p>
 * Provides methods to create different types of game zombies with
 * their associated graphics.
 * </p>
 */
public interface IFactoryZombieGame {

    /**
     * Creates a {@link IGameZombie} instance representing a "clicker" zombie,
     * initializing it with the given {@link Zombie} model and the appropriate
     * graphics component.
     * 
     * @param clicker the {@link Zombie} instance representing the clicker zombie
     * @return a new {@link IGameZombie} configured with the clicker zombie and its
     *         graphics component
     */
    IGameZombie gameZombieClicker(final Zombie clicker);
}
