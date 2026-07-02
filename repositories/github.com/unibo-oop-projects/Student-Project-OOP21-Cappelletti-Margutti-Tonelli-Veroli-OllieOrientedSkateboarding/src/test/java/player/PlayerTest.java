package player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Rectangle2D;
import model.player.JumpState;
import model.player.Player;
import model.player.PlayerImpl;
import sound.SoundFactoryImpl;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() { 
        /*Lines to initializes JavaFx environment, so tests work, otherwise we get
         * "java.lang.RuntimeException: Internal graphics not initialized yet" error */
        final JFrame frame = new JFrame("");
        final JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        this.player = new PlayerImpl(new SoundFactoryImpl());
    }

    @Test
    void testJump() {
        /*Make the player jump, the fields isJumping must be true, 
         * and the field isGoingDown must be false*/
        player.jump();
        assertEquals(JumpState.UP, player.getJumpState());
    }

    @Test
    void testUpdateJump1() {
        player.jump();
        player.updateJump();
        assertTrue(player.getBounds().getMinY() < PlayerImpl.LAND - PlayerImpl.MAIN_CHARACTER_HEIGHT);
        assertNotEquals(JumpState.DOWN, player.getJumpState());
    }

    @Test
    void testUpdateJump2() {
        player.updateJump();
        assertEquals(player.getBounds().getMinY(), PlayerImpl.LAND - PlayerImpl.MAIN_CHARACTER_HEIGHT);
        assertEquals(JumpState.NOT_JUMPING, player.getJumpState());
    }

    @Test
    void testLifes() {
        player.setNumberOfLives(2);
        assertEquals(player.getLives(), 3);
    }

    @Test
    void testJumpCounter() {
        for (int i = 0; i < 4; i++) {
            player.jump();
            player.updateJump();
            while (player.getBounds().getMaxY() < PlayerImpl.LAND) {
                player.updateJump();
            }
        }
        assertEquals(player.getJumpCounter(), 4);
    }

    @Test
   void testBounds() {
        assertEquals(player.getBounds(), new Rectangle2D(PlayerImpl.PLAYER_X,
                                                         PlayerImpl.LAND - PlayerImpl.MAIN_CHARACTER_HEIGHT,
                                                         PlayerImpl.MAIN_CHARACTER_WIDTH, 
                                                         PlayerImpl.MAIN_CHARACTER_HEIGHT));
    }

}
