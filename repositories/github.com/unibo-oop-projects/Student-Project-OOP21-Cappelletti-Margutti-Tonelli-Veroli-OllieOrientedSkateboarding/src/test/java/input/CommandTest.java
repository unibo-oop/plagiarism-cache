package input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import model.Model;
import model.ModelImpl;
import model.player.JumpState;
import model.player.Player;
import sound.SoundFactoryImpl;

class CommandTest {

    private static final double SCREEN_WIDTH = 854.0;
    private static final double SCREEN_HEIGHT = 480.0;

    private Model model;
    private Player player;

    @BeforeEach
    void init() {
        // Initialize JavaFX environment
        final JFrame frame = new JFrame();
        final JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        this.model = new ModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT, new SoundFactoryImpl());
        this.player = this.model.getGameState().getPlayer();
    }

    @Test
    void testJump() {
        final Command space = new Space(this.model.getGameState());
        assertEquals(JumpState.NOT_JUMPING, this.player.getJumpState());
        space.execute();
        assertEquals(JumpState.UP, this.player.getJumpState());
    }
}
