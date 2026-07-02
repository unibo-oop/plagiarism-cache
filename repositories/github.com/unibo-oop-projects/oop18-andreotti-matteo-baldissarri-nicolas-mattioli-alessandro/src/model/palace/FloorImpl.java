package model.palace;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.entities.Position;

/**
 * 
 * Implements a Floor of a Palace.
 *
 */
public class FloorImpl implements Floor {

    private static final int WINDOWS_PER_FLOOR = 6;

    private List<Window> windows;

    /**
     * 
     * Floor Builder.
     * 
     * @param positions List contains the position of Windows in this Floor.
     * 
     */
    public FloorImpl(final List<Position> positions) {
        this.windows = Stream.generate(() -> new WindowImpl(positions.remove(0))).limit(WINDOWS_PER_FLOOR)
                .collect(Collectors.toList());
    }

    /**
     * 
     * @return A random Window.
     * 
     */
    public Window getRandomWindow() {
        return this.windows.get(new Random().nextInt(WINDOWS_PER_FLOOR));
    }

    /**
     * 
     * @return A random Window.
     * @param maxWindow The maximum window to end random.
     * 
     */
    public Window getRandomWindow(final int maxWindow) {
        return this.windows.get(new Random().nextInt(WINDOWS_PER_FLOOR - (WINDOWS_PER_FLOOR - maxWindow)));
    }

    /**
     * 
     * @return All the windows of this Floor.
     * 
     */
    public List<Window> getWindows() {
        return Collections.unmodifiableList(this.windows);
    }
}
