package vg.model;

import org.junit.jupiter.api.Test;
import vg.model.entity.dynamicEntity.player.BasePlayer;
import vg.model.entity.dynamicEntity.player.Player;
import vg.utils.Direction;
import vg.utils.V2D;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    V2D position = new V2D(5, 5);
    Player pl = BasePlayer.newPlayer(position);

    @Test
    void lifeDecInc() {
        assertEquals(BasePlayer.PLAYER_MAX_LIFE, pl.getLife());
        pl.incLife();
        assertEquals(BasePlayer.PLAYER_MAX_LIFE+1, pl.getLife());
        pl.decLife();
        assertEquals(BasePlayer.PLAYER_MAX_LIFE, pl.getLife());
    }

    @Test
    void playerMove() {
        assertEquals(position, pl.getPosition());
        pl.move();
        assertEquals(new V2D(5, 5), pl.getPosition());
        pl.changeDirection(Direction.DOWN, true);
        pl.move();
        pl.move();
        pl.move();
        assertEquals(new V2D(5, 8), pl.getPosition());

        pl.changeDirection(Direction.LEFT, true);
        pl.move();
        pl.move();
        pl.move();
        assertEquals(new V2D(2, 8), pl.getPosition());

    }

    @Test
    void playerSpeedup() {
        //Default player speed is (1,1)
        Player pl1 = BasePlayer.newPlayer(new V2D(0, 0));
        V2D bonusSpeed = new V2D(3, 3);
        pl1.changeDirection(Direction.DOWN, true);

        pl1.move();
        assertEquals(new V2D(0, 1), pl1.getPosition());

        //-----ENABLE SPEEDUP BONUS-------
        pl1.enableSpeedUp(bonusSpeed);
        //Speed of player must be increased of amount speedImprove
        assertEquals(BasePlayer.DEFAULT_PLAYER_SPEED.sum(bonusSpeed), pl1.getSpeed());

        pl1.move();
        assertEquals(new V2D(0, 5), pl1.getPosition());
        //-----DISABLE SPEEDUP BONUS-------
        pl1.disableSpeedUp();
        pl1.move();
        assertEquals(new V2D(0, 6), pl1.getPosition());
    }
}
