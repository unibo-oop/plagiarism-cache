package model.palace;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.entities.Position;
import model.palace.Window.StatusOfWindow;

/**
 * 
 * Implements the Palace.
 *
 */
public class PalaceImpl implements Palace {

    private static final int VISIBLE_FLOORS = 11;
    private static final int INVISIBLE_FLOOR = 12;
    private static final int WINDOWS_PER_FLOOR = 6;
    private static final int MAX_WINDOWS_NOT_DEFAULT_STATUS = 2;

    private List<Floor> floors;

    /**
     * 
     * Palace builder.
     * 
     * @param p List contains the Position of all Windows.
     * 
     */
    public PalaceImpl(final List<List<Position>> p) {
        this.floors = Stream.generate(() -> new FloorImpl(p.remove(0)))
                            .limit(VISIBLE_FLOORS + 1)
                            .collect(Collectors.toList());
    }

    /**
     * 
     * @return A random Floor of this Palace.
     * 
     */
    public Floor getRandomFloor() {
        return this.floors.get(new Random().nextInt(VISIBLE_FLOORS + 1));
    }

    /**
     * 
     * @return A random Floor of this Palace.
     * @param minFloor The minimum floor to start random.
     * 
     */
    public Floor getRandomFloor(final int minFloor) {
        return this.floors.get(new Random().nextInt(VISIBLE_FLOORS + 1 - minFloor) + minFloor);
    }

    /**
     * 
     * @return the list of all the floors of this Palace.
     * 
     */
    public List<Floor> getFloors() {
        return this.floors;
    }

    /**
     * 
     * Remove the first floor and add a new floor on the top of palace.
     * 
     */
    public void addFloor() {
        int count = 0;
        for (int i = 0; i < WINDOWS_PER_FLOOR; i++) {
            floors.get(INVISIBLE_FLOOR - 1).getWindows().get(i).setStatus(WindowImpl.getRandomStatus());
            if (floors.get(INVISIBLE_FLOOR - 1).getWindows().get(i).getStatus() == StatusOfWindow.CLOSE) {
                count++;
                if (count > MAX_WINDOWS_NOT_DEFAULT_STATUS) {
                    floors.get(INVISIBLE_FLOOR - 1).getWindows().get(i).changeStatus();
                }
            }
        }
        for (int i = 0; i < floors.size() - 1; i++) {
            for (int k = 0; k < WINDOWS_PER_FLOOR; k++) {
                floors.get(i).getWindows().get(k).setStatus(floors.get(i + 1).getWindows().get(k).getStatus());
            }
        }
    }
}
