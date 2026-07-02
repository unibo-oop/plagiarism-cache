package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.platforms.BouncePlatform;
import it.unibo.javajump.model.entities.platforms.BreakablePlatform;
import it.unibo.javajump.model.entities.platforms.MovingPlatform;
import it.unibo.javajump.model.entities.platforms.Platform;

/**
 * The Abstract game object factory.
 */
public abstract class AbstractGameObjectFactory implements GameObjectFactory {

    @Override
    public abstract Character createCharacter(float x, float y);

    @Override
    public abstract Platform createStandardPlatform(float x, float y);

    @Override
    public abstract Platform createRandomPlatform(float x, float y);

    @Override
    public abstract MovingPlatform createMovingPlatform(float x, float y, int screenWidth);

    @Override
    public abstract BreakablePlatform createBreakablePlatform(float x, float y);

    @Override
    public abstract BouncePlatform createBouncePlatform(float x, float y, float bounceFactor);

    @Override
    public abstract Coin createCoin(float x, float y);
}
