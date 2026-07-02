package powpaw;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.geometry.Point2D;
import powpaw.player.model.api.Player;
import powpaw.player.model.impl.PlayerImpl;
import powpaw.player.model.impl.PlayerImpl.PlayerState;

class PlayerTest {

    private static final Point2D DEBUG_POSITION = new Point2D(0, 0);
    private static final int DEBUG_PLAYER_NUMBER = 1;
    private static final double DEBUG_PLAYER_WIDHT = 5;
    private static final double DEBUG_PLAYER_HEIGHT = 10;
    private static final Point2D DEBUG_PLAYER_DIRECTION = new Point2D(1, 0);

    private final Player player = new PlayerImpl(DEBUG_POSITION, DEBUG_PLAYER_NUMBER);

    @Test
    void numberTest() {
        assertEquals(DEBUG_PLAYER_NUMBER, this.player.getNumber());
    }

    @Test
    void initialPositionTest() {
        assertEquals(DEBUG_POSITION, this.player.getPosition());
    }

    @Test
    void setSizeTest() {
        this.player.setHeight(DEBUG_PLAYER_HEIGHT);
        assertEquals(DEBUG_PLAYER_HEIGHT, this.player.getHeight());

        this.player.setWidth(DEBUG_PLAYER_WIDHT);
        assertEquals(DEBUG_PLAYER_WIDHT, this.player.getWidth());

    }

    @Test
    void setDirectionTest() {
        final Point2D direction = DEBUG_PLAYER_DIRECTION;

        this.player.setDirection(direction);
        assertEquals(direction, this.player.getDirection());
    }

    @Test
    void setStateTest() {
        this.player.setCurrentState(PlayerState.ATTACK);
        assertEquals(PlayerState.ATTACK, this.player.getState());

        this.player.setCurrentState(PlayerState.IDLE);
        assertEquals(PlayerState.IDLE, this.player.getState());
    }
}
