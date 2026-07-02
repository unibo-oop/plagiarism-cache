package it.unibo.javajump.model.level.spawn.collectiblespawn;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.platforms.MovingPlatform;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.factories.GameObjectFactory;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.COIN_OFFSET;
import static it.unibo.javajump.utility.Constants.COIN_X_DIV;
import static it.unibo.javajump.utility.Constants.COIN_X_MUL;

/**
 * Class implementation of the CollectiblesSpawner interface.
 */
public class CollectiblesSpawnerImpl implements CollectiblesSpawner {
    /**
     * Factory to create a Coin object.
     */
    private final GameObjectFactory factory;
    /**
     * Random object useful to spawn coins in random-like way (there may or may not be a coin on a Platform).
     */
    private final Random rand;
    /**
     * Settable float value to confront to Random, determines the probability of coin spawn on a platform.
     */
    private final float coinSpawnChance;

    /**
     * Constructor for CollectiblesSpawnerImpl, which manages the spawn of coins on a given platform.
     *
     * @param factory         the factory, used to create the Coin object
     * @param coinSpawnChance the probability to spawn a Coin on a Platform (higher -> more coins overall)
     */
    public CollectiblesSpawnerImpl(final GameObjectFactory factory, final float coinSpawnChance) {
        this.factory = factory;
        this.rand = new Random();
        this.coinSpawnChance = coinSpawnChance;
    }

    /**
     * {@inheritDoc} The implemented method first checks the coinSpawnChance to decide whether to spawn a Coin or not
     * on the given Platform. It then sets the general Coin position (X, Y) on the Platform, and creates the given coin
     * demanding it to the factory. An added if branch checks whether the given Platform is a MovingPlatform, and if so,
     * attaches the Coin to the MovingPlatform (the attaching logic is specified in CoinImpl class). Finally, the Coin
     * gets added to the GameObjects in model.
     */
    @Override
    public void spawnCollectible(final GameModel model, final float platformX, final float platformY,
                                 final float platformWidth, final Platform platform) {
        if (rand.nextFloat() < coinSpawnChance) {
            final float coinX = platformX + (platformWidth / COIN_X_DIV) - (platformWidth * COIN_X_MUL);
            final float coinY = platformY - COIN_OFFSET;
            final Coin coin = factory.createCoin(coinX, coinY);

            if (platform instanceof MovingPlatform) {
                coin.attachToPlatform(platform);
            }
            model.getGameObjects().add(coin);
        }
    }
}
