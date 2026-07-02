package it.unibo.javajump.model;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.collectibles.Coin;
import it.unibo.javajump.model.entities.collectibles.CoinState;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.states.ingame.InGameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static it.unibo.javajump.utility.Constants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.Constants.SCREEN_WIDTH;
import static it.unibo.javajump.utility.TestConstants.COUNTER_START;
import static it.unibo.javajump.utility.TestConstants.DELTA_TIME;
import static it.unibo.javajump.utility.TestConstants.DIV_TO_CENTER;
import static it.unibo.javajump.utility.TestConstants.MAX_COUNT_PLATFORM;
import static it.unibo.javajump.utility.TestConstants.PLATFORM_OFFSET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The Collision test.
 */
class CollisionTest {

    private GameModel model;

    /**
     * Sets up the environment before each test.
     */
    @BeforeEach
    void setUp() {

        model = new GameModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT);
        model.startGame();
        model.setState(new InGameState());

    }

    /**
     * Tests the collisions with the Coin game object.
     */
    @Test
    void testCoin() {
        final float x = (float) SCREEN_WIDTH / DIV_TO_CENTER;
        final float y = (float) SCREEN_HEIGHT / DIV_TO_CENTER;
        model.getPlayer().setY(y);
        model.getPlayer().setX(x);
        final Coin coin = model.getSpawnManager().getFactory().createCoin(x, y);
        model.getGameObjects().add(coin);
        model.update(DELTA_TIME);

        assertEquals(CoinState.COLLECTING, coin.getState(), "Coin State should be COLLECTING.");
    }

    /**
     * Tests the collisions with the Platform game object.
     */
    @Test
    void testPlatform() {
        int counter = COUNTER_START;
        final List<GameObject> toRemove = new ArrayList<>();
        for (final GameObject go : model.getGameObjects()) {
            if (go instanceof Platform c) {
                toRemove.add(c);
            }
        }
        model.getGameObjects().removeAll(toRemove);
        final Platform platform = model.getSpawnManager().getFactory().createStandardPlatform(model.getPlayer().getX(),
                model.getPlayer().getY() + PLATFORM_OFFSET);
        model.getGameObjects().add(platform);
        while (!model.getPlayer().isOnPlatform() && counter < MAX_COUNT_PLATFORM) {
            model.update(DELTA_TIME);
            counter++;
        }
        assertTrue(platform.consumeTouched(), "Platform Should consume touched .");
    }

}
