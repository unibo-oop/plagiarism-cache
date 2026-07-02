package it.dpg.minigames.jumpMinigameTests.model;

import it.dpg.minigames.jumpgame.model.PlatformSpawner;
import it.dpg.minigames.jumpgame.model.PlatformSpawnerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class PlatformSpawnerTest {

    @Test
    void platformSpawnerTest() throws InterruptedException {
        PlatformSpawner p = new PlatformSpawnerImpl(600, 100, 25);

        Assertions.assertEquals(p.getPlatforms().size(), 1);

        TimeUnit.MILLISECONDS.sleep(1000);
        p.updatePlatformsGeneration();

        Assertions.assertEquals(p.getPlatforms().size(), 2);
        Assertions.assertTrue(
                p.getPlatforms().get(1).getId() > p.getPlatforms().get(0).getId()
        );

        TimeUnit.MILLISECONDS.sleep(1000);
        p.updatePlatformsGeneration();
        Assertions.assertEquals(p.getPlatforms().size(), 3);

        p.updatePlatformsGeneration();
        Assertions.assertEquals(p.getPlatforms().size(), 3);
        Assertions.assertFalse(p.getPlatforms().get(0).doesExist());

        p.updatePlatformsGeneration();
        Assertions.assertEquals(p.getPlatforms().size(), 2);
    }
}
