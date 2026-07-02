package it.dpg.minigames.jumpMinigameTests.model;

import it.dpg.minigames.jumpgame.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void playerTest() {
        int size = 100;
        int startX = 100;
        int startY = 100;
        int startSpeed = 0;
        int gravity = 1;

        Player p = new Player(size, startX, startY, gravity, startSpeed);

        Assertions.assertEquals(p.getWidth(), size);
        Assertions.assertEquals(p.getHeight(), size);
        Assertions.assertEquals(p.getPosition().getLeft(), startX);
        Assertions.assertEquals(p.getPosition().getRight(), startY);
        Assertions.assertEquals(p.getSpeedY(), startSpeed);

        int speed = 20;
        p.setSpeedY(speed);
        p.setSpeedX(speed);
        var prevPos = p.getPosition();
        p.updatePosition();

        Assertions.assertEquals(p.getPosition().getLeft(), prevPos.getLeft()+speed);
        Assertions.assertEquals(p.getPosition().getRight(), prevPos.getRight()+speed);
        Assertions.assertEquals(p.getSpeedY(), speed-gravity);

    }
}
