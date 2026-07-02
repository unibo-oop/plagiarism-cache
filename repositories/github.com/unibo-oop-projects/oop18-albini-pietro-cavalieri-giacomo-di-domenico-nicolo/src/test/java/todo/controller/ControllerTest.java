package todo.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.junit.Test;

import todo.model.program.DummyClipboard;

public class ControllerTest {
    private final static String LEVEL_TITLE = "Busy Mail Room";
    private final Controller controller;

    public ControllerTest() throws IOException {
        this.controller = new ControllerImpl(new DummyClipboard());
    }

    @Test
    public void testLoadLevel() {
        final LevelController lc = this.controller.loadLevel(LEVEL_TITLE);
        assertEquals(LEVEL_TITLE, lc.getLevelTitle());
    }

    @Test(expected = NoSuchElementException.class)
    public void testLoadUnexistingLevel() {
        this.controller.loadLevel("MailRoom");
    }
}
