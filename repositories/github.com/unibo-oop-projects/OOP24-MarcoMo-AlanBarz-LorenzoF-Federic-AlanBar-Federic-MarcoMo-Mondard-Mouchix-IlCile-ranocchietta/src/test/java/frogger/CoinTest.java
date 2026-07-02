package frogger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.controller.GameControllerImpl;
import frogger.model.implementations.Coin;
import frogger.model.implementations.PickableObjectDependency;

class CoinTest {

    private Coin coin;

    @BeforeEach
    void setUp() {
        final Position position = new Position(1, 2);
        final Pair dimension = new Pair(10, 10);
        coin = new Coin(position, dimension);
    }

    @Test
    void testGetRequiredDependenciesReturnsGameController() {
        assertEquals(PickableObjectDependency.GAME_CONTROLLER, coin.getRequiredDependencies());
    }

    @Test
    void testOnPickIncreasesCoinsByRandomValue() {
        final GameControllerImpl controller = new GameControllerImpl();
        controller.setCoins(10);
        final int startCoinValue = controller.getCoins();

        coin.setRelatedEntity(controller);

        coin.onPick();

        final int coins = controller.getCoins();
        final int minCoinValue = 1;
        final int maxCoinValue = 5;
        assertTrue(coins >= startCoinValue + minCoinValue && coins <= startCoinValue + maxCoinValue,
                "Coins after pick: " + coins);
    }

    @Test
    void testOnPickDoesNothingIfRelatedEntityIsNotGameController() {
        final Object unrelatedEntity = new Object();
        coin.setRelatedEntity(unrelatedEntity);
        assertDoesNotThrow(coin::onPick);
    }
}
