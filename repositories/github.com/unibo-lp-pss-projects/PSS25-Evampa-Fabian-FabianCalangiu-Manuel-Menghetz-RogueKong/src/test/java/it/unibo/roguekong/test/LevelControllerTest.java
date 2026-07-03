package it.unibo.roguekong.test;

import it.unibo.roguekong.controller.GameController;
import it.unibo.roguekong.model.entity.Player;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.LevelBuilderImpl;
import it.unibo.roguekong.model.game.impl.LevelModel;
import it.unibo.roguekong.model.game.impl.TileManager;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.view.impl.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.roguekong.controller.LevelController;

import java.util.List;


public class LevelControllerTest {
    LevelController levelController;
    PlayerImpl player;
    @BeforeEach
    void setUp() {
        this.player = new PlayerImpl();
        this.player.setTileManager(new TileManager("maps/map1.txt", "maps/background1.txt"));

        LevelModel level = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(10, 10))
                .setEndPoint(new PositionImpl(10, 10))
                .setEnemiesList(List.of())
                .setPlayer(player)
                .setTileManager(new TileManager("maps/map1.txt", "maps/background1.txt"))
                .build();

        LevelModel level2 = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(10, 10))
                .setEndPoint(new PositionImpl(10, 10))
                .setEnemiesList(List.of())
                .setPlayer(player)
                .setTileManager(new TileManager("maps/map2.txt", "maps/background1.txt"))
                .build();

        LevelModel level3 = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(10, 10))
                .setEndPoint(new PositionImpl(10, 10))
                .setEnemiesList(List.of())
                .setPlayer(player)
                .setTileManager(new TileManager("maps/map2.txt", "maps/background1.txt"))
                .build();

        List<LevelModel> levels = List.of(level, level2, level3);
         this.levelController = new LevelController(levels);
         this.levelController.setUpLevel();

    }

    @Test
    public void TestLevelControllerListToBeNotEmpty() {
        assertFalse(this.levelController.getLevelsList().isEmpty());
    }

    @Test
    public void LevelIndexMustBeZeroAtStart() {
        assertEquals(0, this.levelController.getCurrentLevelIndex());
    }

    @Test
    public void LevelIndexMustBeOneAfterFirstLevelIsComplete() {
        this.levelController.nextLevelIfIsComplete();
        assertEquals(1, this.levelController.getCurrentLevelIndex());
    }

    @Test
    public void LevelIndexMustBeTwoAfterSecondLevelIsComplete() {
        // First Level
        this.levelController.nextLevelIfIsComplete();

        // Second Level
        this.levelController.nextLevelIfIsComplete();

        assertEquals(2, this.levelController.getCurrentLevelIndex());
    }

    @Test
    public void checkIfIsThereAnotherLevelAfterTheFirstOne() {
        assertTrue(this.levelController.isThereAnotherLevel());
    }
}
