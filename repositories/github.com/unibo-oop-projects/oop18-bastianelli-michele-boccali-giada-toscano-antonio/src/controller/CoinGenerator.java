package controller;

import java.util.Random;
import java.util.Set;

import org.jbox2d.common.Vec2;

import controller.entities.Coin;
import controller.entities.Platform;
import enumerators.CoinType;
import enumerators.Level;
import factories.AbstractFactory;
import utils.EntityCreationUtils;

/**
 * Class that handles the coin generation, based on difficulty. Must be initialized first.
 */
public class CoinGenerator implements Generator {

    private static final int MAX_COINS = 4;
    private static final int DEFAULT_COIN_SPAWN_RATE = 5;
    private static final Random RANDOM = new Random();
    private Level level;
    private CoinType coinType;

    @Override
    public final void init(final Level level) {
        this.level = level;
        this.coinType = this.level.getCoinType();
    }

    @Override
    public final void update() {
        final Set<Coin> coins = GameController.getInstance().getGameModel().getCoinSet();

        final Set<Platform> platforms = GameController.getInstance().getGameModel().getPlatformSet();
        if (coins.size() < MAX_COINS && !platforms.isEmpty()) {
             final Platform platform = GameController.getInstance().getGameModel().getTopPlatform();
             generateCoin(platform);
        }
    }


    private void generateCoin(final Platform platform) {
        final int spawnRate = (int) (DEFAULT_COIN_SPAWN_RATE / level.getDifficulty());
        if (RANDOM.nextInt(spawnRate) == 0 && !platform.isBusy()) {
            final Vec2 pos = EntityCreationUtils.getPositionOnPlatform(platform, coinType.getWidth(), coinType.getHeight());
            final Coin coin = AbstractFactory.createCoin(this.coinType, pos);
            GameController.getInstance().getGameModel().addEntityToMap(coin, coin.getBody());
           // Log.add("generated coin on " + pos.x + "-" + pos.y);
            platform.setBusy(true);
        }
    }


}
