package clashclass.engine;

import clashclass.commons.Vector2D;
import clashclass.elements.troops.BattleTroopFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEngineTest {

    @Test
    void TestGameEngineThreadActsAsExpected() {
        final var gameEngine = new GameEngineImpl(Optional.empty());

        gameEngine.start();

        gameEngine.addGameObject(new BattleTroopFactoryImpl().createBarbarian(Vector2D.zero()));

        gameEngine.stop();
    }
}
