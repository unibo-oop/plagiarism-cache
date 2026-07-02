package input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import model.Model;
import model.ModelImpl;
import sound.SoundFactoryImpl;

class InputObserverTest {

    private static final double SCREEN_WIDTH = 854.0;
    private static final double SCREEN_HEIGHT = 480.0;

    private InputObserver inputObserver;
    private Model model;

    @BeforeEach
    void init() {
        // Initialize JavaFX environment
        final JFrame frame = new JFrame();
        final JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        this.model = new ModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT, new SoundFactoryImpl());
        this.inputObserver = new InputObserverImpl();
    }

    @Test
    void testInitialState() {
        assertTrue(this.inputObserver.getCommands().isEmpty());
    }

    @Test
    void testAddRemoveCommand() {
        this.inputObserver.notify(new Space(this.model.getGameState()));
        assertEquals(1, this.inputObserver.getCommands().size());
        this.inputObserver.clear();
        assertEquals(0, this.inputObserver.getCommands().size());
    }

}
