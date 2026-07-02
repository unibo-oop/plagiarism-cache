package it.dpg.minigames.jumpMinigameTests.model;

import it.dpg.minigames.jumpgame.model.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlatformTest {

    @Test
    void platformTest() {
        int width = 100;
        int height = 25;
        int startX = 100;
        int startY = 100;
        int id = 0;

        Platform p = new Platform(startX, startY, width, height, id);

        Assertions.assertEquals(p.getWidth(), width);
        Assertions.assertEquals(p.getHeight(), height);
        Assertions.assertEquals(p.getPosition().getLeft(), startX);
        Assertions.assertEquals(p.getPosition().getRight(), startY);
        Assertions.assertEquals(p.getSpeedX(), 0);
        Assertions.assertEquals(p.getSpeedY(), 0);

        int speed = 20;
        p.setSpeedY(speed);
        p.setSpeedX(speed);
        var prevPos = p.getPosition();
        p.updatePosition();

        Assertions.assertEquals(p.getPosition().getLeft(), prevPos.getLeft()+speed);
        Assertions.assertEquals(p.getPosition().getRight(), prevPos.getRight()+speed);
    }
}
