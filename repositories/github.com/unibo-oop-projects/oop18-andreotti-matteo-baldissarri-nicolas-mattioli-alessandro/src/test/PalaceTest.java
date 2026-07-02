package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.entities.Position;
import model.palace.Palace;
import model.palace.PalaceImpl;
import model.palace.Window;
import model.palace.Window.StatusOfWindow;
import model.palace.WindowImpl;

/**
 * Test the Palace.
 */
public class PalaceTest {

    private static final int SIGHT_FLOOR = 12;
    private static final int WINDOWS_PER_FLOOR = 6;
    private static final double SCREEN_X_RATIO = 0.4;
    private static final double WINDOWS_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 12.5;
    private static final double WINDOWS_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 11;

    /**
     * Add the positions to the windows.
     * 
     * @return The list of position lists.
     */
    private List<List<Position>> createWindowsPosition() {
        List<List<Position>> windowsPosition = new ArrayList<List<Position>>();
        List<Position> tmp = new ArrayList<Position>();
        for (int i = 0; i <= SIGHT_FLOOR; i++) {
            for (int j = 0; j < WINDOWS_PER_FLOOR; j++) {
                tmp.add(new Position(
                        Toolkit.getDefaultToolkit().getScreenSize().getWidth() * SCREEN_X_RATIO + j * WINDOWS_WIDTH
                                + WINDOWS_WIDTH / 2,
                        Toolkit.getDefaultToolkit().getScreenSize().getHeight() - i * WINDOWS_HEIGHT
                                - WINDOWS_HEIGHT / 2));
            }
            windowsPosition.add(new ArrayList<>(tmp));
            tmp.clear();
        }
        return windowsPosition;
    }

    /**
     * Test window's change status.
     */
    @org.junit.Test
    public void testWindowStatus() {
        final Window window = new WindowImpl(null);
        assertEquals(window.getStatus(), StatusOfWindow.OPEN);
        window.changeStatus();
        assertEquals(window.getStatus(), StatusOfWindow.PARTIAL_CLOSE);
        window.changeStatus();
        assertEquals(window.getStatus(), StatusOfWindow.CLOSE);
        window.changeStatus();
        assertEquals(window.getStatus(), StatusOfWindow.OPEN);
    }

    /**
     * Test change status of a random window.
     */
    @org.junit.Test
    public void testPalace() {
        final Palace palace = new PalaceImpl(this.createWindowsPosition());
        palace.getRandomFloor().getRandomWindow().changeStatus();
        Optional<Window> opWindow = palace.getFloors()
                                          .stream()
                                          .map(fl -> fl.getWindows())
                                          .flatMap(List::stream)
                                          .filter(win -> win.getStatus().equals(StatusOfWindow.OPEN))
                                          .findAny();
        assertTrue(opWindow.isPresent());
    }
}
