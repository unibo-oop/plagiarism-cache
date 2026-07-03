package it.unibo.roguekong.test;

import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.LevelModel;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {
    LevelModel level;
    PositionImpl spawnPoint;
    PositionImpl endPoint;
    PlayerImpl player;
    TileManager tileManager;

    @BeforeEach
    void setUp() {
        this.player = new PlayerImpl();
        this.spawnPoint = new PositionImpl(0, 0);
        this.endPoint = new PositionImpl(10, 10);
        this.tileManager = new TileManager("maps/map1.txt", "maps/background1.txt");
        this.level = new LevelModel(this.spawnPoint, this.endPoint, List.of(), this.player, this.tileManager);

        this.player.setTileManager(this.tileManager);
        this.level.init();
    }

    @Test
    void checkIfLevelIsCompleteAtStart() {
        assertFalse(this.level.isLevelComplete());
    }

    @Test
    void checkIfLevelIsCompleteAtEnd() {
        this.player.setPosition(this.endPoint.getX(), this.endPoint.getY());
        this.level.checkIfPlayerIsOnEndPoint();
        assertTrue(this.level.isLevelComplete());
    }

    @Test
    void checkIfLevelIsNotCompleteIfPlayerIsNotAtTheStart() {
        this.player.setPosition(200, 300);
        this.level.checkIfPlayerIsOnEndPoint();
        assertFalse(this.level.isLevelComplete());
    }
}
