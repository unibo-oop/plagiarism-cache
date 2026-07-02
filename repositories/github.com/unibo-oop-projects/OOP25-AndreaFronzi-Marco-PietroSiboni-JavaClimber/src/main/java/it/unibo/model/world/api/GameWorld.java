package it.unibo.model.world.api;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;

/**
 * Interface for a world where specific entities can be removed.
 * This interface extends {@link BaseWorld} and adds capabilities to remove
 * specific game objects from the world.
 */
public interface GameWorld extends BaseWorld {

    /**
     * Removes a specific static platform from the world.
     *
     * @param platform the platform to be removed.
     * @return true if the platform was found and removed, false otherwise.
     */
    boolean removeStaticPlatform(Platform platform);

    /**
     * Removes a specific moving platform from the world.
     *
     * @param platform the platform to be removed.
     * @return true if the platform was found and removed, false otherwise.
     */
    boolean removeMovingPlatform(Platform platform);

    /**
     * Removes a specific on-touch platform from the world.
     *
     * @param platform the platform to be removed.
     * @return true if the platform was found and removed, false otherwise.
     */
    boolean removeOnTouchPlatform(Platform platform);

    /**
     * Removes a specific enemy (monster) from the world.
     *
     * @param monster the enemy to be removed.
     * @return true if the enemy was found and removed, false otherwise.
     */
    boolean removeMonster(Enemy monster);

    /**
     * Removes a specific gadget from the world.
     *
     * @param gadget the gadget to be removed.
     * @return true if the gadget was found and removed, false otherwise.
     */
    boolean removeGadget(Gadget gadget);

    /**
     * Removes a specific coin (money) from the world.
     *
     * @param money the coin to be removed.
     * @return true if the coin was found and removed, false otherwise.
     */
    boolean removeMoney(Coin money);

    /**
     * Returns the player character.
     * 
     * @return the alien instance
     */
    Alien getAlien();

}
