package it.unibo.javajump.model.factories;

import it.unibo.javajump.model.entities.character.Character;
import it.unibo.javajump.model.entities.character.CharacterImpl;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinImpl;
import it.unibo.javajump.model.entities.platforms.BouncePlatform;
import it.unibo.javajump.model.entities.platforms.BouncePlatformImpl;
import it.unibo.javajump.model.entities.platforms.BreakablePlatform;
import it.unibo.javajump.model.entities.platforms.BreakablePlatformImpl;
import it.unibo.javajump.model.entities.platforms.MovingPlatform;
import it.unibo.javajump.model.entities.platforms.MovingPlatformImpl;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.entities.platforms.PlatformImpl;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.BOUNCE_PLATFORM_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.BOUNCE_PLATFORM_WIDTH;
import static it.unibo.javajump.utility.Constants.BREAKABLE_PLATFORM_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.BREAKABLE_PLATFORM_WIDTH;
import static it.unibo.javajump.utility.Constants.CHARACTER_HEIGHT;
import static it.unibo.javajump.utility.Constants.CHARACTER_JUMP_FORCE;
import static it.unibo.javajump.utility.Constants.CHARACTER_WIDTH;
import static it.unibo.javajump.utility.Constants.COIN_HEIGHT;
import static it.unibo.javajump.utility.Constants.COIN_WIDTH;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_RANGE;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_RANGE_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_SPEED;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_SPEED_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_WIDTH;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_WIDTH_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.PLATFORM_HEIGHT;
import static it.unibo.javajump.utility.Constants.RANDOM_PLATFORM_RNG_FACTOR;
import static it.unibo.javajump.utility.Constants.RANDOM_PLATFORM_WIDTH;
import static it.unibo.javajump.utility.Constants.STANDARD_PLATFORM_WIDTH;

/**
 * The implementation of GameObjectFactory, implementing the AbstractGameObjectFactory.
 */
public final class GameObjectFactoryImpl extends AbstractGameObjectFactory {
    private final Random rand = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public Character createCharacter(final float x, final float y) {
        return new CharacterImpl(x, y, CHARACTER_WIDTH, CHARACTER_HEIGHT, CHARACTER_JUMP_FORCE);
    }

    /**
     * {@inheritDoc} The standard platform is simple: it has a fixed width.
     */
    @Override
    public Platform createStandardPlatform(final float x, final float y) {
        return new PlatformImpl(x, y, STANDARD_PLATFORM_WIDTH, PLATFORM_HEIGHT);
    }

    /**
     * {@inheritDoc} The random-width platform uses an RNG factor to choose a semi-random width.
     */
    @Override
    public Platform createRandomPlatform(final float x, final float y) {
        final float width = RANDOM_PLATFORM_WIDTH + rand.nextInt(RANDOM_PLATFORM_RNG_FACTOR);
        return new PlatformImpl(x, y, width, PLATFORM_HEIGHT);
    }

    /**
     * {@inheritDoc} The moving platform uses RNG factors to set its width, range of movement and speed.
     */
    @Override
    public MovingPlatform createMovingPlatform(final float x, final float y, final int screenWidth) {
        final float width = MOVING_PLATFORM_WIDTH + rand.nextInt(MOVING_PLATFORM_WIDTH_RNG_FACTOR);
        final float range = MOVING_PLATFORM_RANGE + rand.nextFloat() * MOVING_PLATFORM_RANGE_RNG_FACTOR;
        final float speed = MOVING_PLATFORM_SPEED + rand.nextInt(MOVING_PLATFORM_SPEED_RNG_FACTOR);
        return new MovingPlatformImpl(x, y, width, PLATFORM_HEIGHT, range, screenWidth, speed);
    }

    /**
     * {@inheritDoc} The breakable platform uses RNG factors to set its width.
     */
    @Override
    public BreakablePlatform createBreakablePlatform(final float x, final float y) {
        final float width = BREAKABLE_PLATFORM_WIDTH + rand.nextInt(BREAKABLE_PLATFORM_RNG_FACTOR);
        return new BreakablePlatformImpl(x, y, width, PLATFORM_HEIGHT);
    }

    /**
     * {@inheritDoc} The bounce platform uses RNG factors to set its width.
     */
    @Override
    public BouncePlatform createBouncePlatform(final float x, final float y, final float bounceFactor) {
        final float width = BOUNCE_PLATFORM_WIDTH + rand.nextInt(BOUNCE_PLATFORM_RNG_FACTOR);
        return new BouncePlatformImpl(x, y, width, PLATFORM_HEIGHT, bounceFactor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coin createCoin(final float x, final float y) {
        return new CoinImpl(x, y, COIN_WIDTH, COIN_HEIGHT);
    }
}
