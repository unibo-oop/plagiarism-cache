package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.platforms.Platform;

/**
 * The interface Game object factory.
 */
public interface GameObjectFactory {
    /**
     * Method to create the playable character.
     *
     * @param x the x position
     * @param y the y position
     *
     * @return the character
     */
    Character createCharacter(float x, float y);

    /**
     * Method to create a standard platform.
     *
     * @param x the x
     * @param y the y
     *
     * @return the platform
     */
    Platform createStandardPlatform(float x, float y);

    /**
     * Method to create random-width platform.
     *
     * @param x the x
     * @param y the y
     *
     * @return the platform
     */
    Platform createRandomPlatform(float x, float y);

    /**
     * Method to create a moving platform.
     *
     * @param x           the x position
     * @param y           the y position
     * @param screenWidth the screen width
     *
     * @return the platform
     */
    Platform createMovingPlatform(float x, float y, int screenWidth);

    /**
     * Method to create a breakable platform.
     *
     * @param x the x position
     * @param y the y position
     *
     * @return the platform
     */
    Platform createBreakablePlatform(float x, float y);

    /**
     * Method to create a bounce platform.
     *
     * @param x            the x position
     * @param y            the y position
     * @param bounceFactor the bounce factor, determines how much will the character bounce off the platform
     *
     * @return the platform
     */
    Platform createBouncePlatform(float x, float y, float bounceFactor);

    /**
     * Method to create a coin.
     *
     * @param x the x position
     * @param y the y position
     *
     * @return the coin
     */
    Coin createCoin(float x, float y);
}
