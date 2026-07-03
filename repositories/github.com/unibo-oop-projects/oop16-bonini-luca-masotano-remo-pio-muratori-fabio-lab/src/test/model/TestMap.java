package test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

import model.GameModelImpl;
import model.map.Room;
import model.map.StandardRoom;
import model.utils.Direction;
import util.Pair;
import model.GameModel;

/**
 * 
 * Test some aspects of the map.
 *
 */
public class TestMap {

    private static final double MDELTA = 1;
    private static final Pair<Double, Double> MIDCOORDINATES = new Pair<>(StandardRoom.getWidth() / 2,
            StandardRoom.getHeight() / 2);
    private static final int LIMITCREATION = 200;

    /**
     * Test the map and the change of room when the player pass through the
     * door.
     */
    @Test
    public void testRooms() {
        GameModel gm;
        int i = 0;
        do {
            gm = new GameModelImpl(false);
        } while (!gm.getMap().getCurrentRoom().getDoors().contains(Direction.UP));

        while (i <= MIDCOORDINATES.getY()) {
            i += gm.getPlayer().getSteps();
            gm.update(new ArrayList<Direction>(Arrays.asList(Direction.UP)), new ArrayList<>(), MDELTA);
        }

        if (!gm.roomChanged()) {
            fail("Room not changed");
        }
    }

    /**
     * Test if enemies are generated in every room, except the first room.
     */
    @Test
    public void testEnemiesCreatedAndBossRoom() {
        GameModel gm = new GameModelImpl(false);
        boolean isFirst;

        // The first room is without enemies.
        for (int limit = 0; limit < LIMITCREATION; limit++) {
            gm = new GameModelImpl(false);

            final Iterator<Room> i = gm.getMap().getAllRooms().iterator();
            isFirst = true;
            if (i.hasNext()) {
                final Room r = i.next();
                if (isFirst) {
                    assertEquals(r.getEnemies().size(), 0);
                    isFirst = false;
                } else {
                    if (r.getEnemies().size() == 0) {
                        fail("Enemy not created.");
                    }
                }
            } else { // This is the boss room
                if (!gm.getMap().isBossRoom()) {
                    fail("No boss room found");
                }
            }
        }
    }
}
