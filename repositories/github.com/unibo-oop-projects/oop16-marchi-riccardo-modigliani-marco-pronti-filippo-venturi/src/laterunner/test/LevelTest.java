package laterunner.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import laterunner.model.level.SetLevel;
import laterunner.model.vehicle.Obstacle;

/**
 * Test the creation of level.
 *
 */
public class LevelTest {

    private SetLevel set = new SetLevel();
    private List<Obstacle> list = new LinkedList<>();
    private static final int DISTANCE = 150;

    /**
     * Test the correct function of the creation's algorithm.
     */
    @Test
    public void testInitialFields() {
        list.addAll(set.getLevel(1).getLevel());
        assertEquals(list.size(), 50);
        for (Obstacle o : list) {
            assertTrue(o.getCurrentSpd().getY() == 300);
            if (!list.get(0).equals(o)) {
                int i = list.indexOf(o);
                Obstacle obj = list.get(i - 1);
                if (o.getCurrentPos().getY() != obj.getCurrentPos().getY()) {
                    assertTrue((o.getCurrentPos().getY() - obj.getCurrentPos().getY()) >= DISTANCE);
                } else {
                    assertFalse(o.getCurrentPos().getX() == obj.getCurrentPos().getX());
                }
            }

        }

        list.clear();
        list.addAll(set.getLevel(10).getLevel());
        assertEquals(list.size(), 200);
        for (Obstacle o : list) {
            assertTrue(o.getCurrentSpd().getY() == 800);
            if (!list.get(0).equals(o)) {
                int i = list.indexOf(o);
                Obstacle obj = list.get(i - 1);
                if (o.getCurrentPos().getY() != obj.getCurrentPos().getY()) {
                    assertTrue((o.getCurrentPos().getY() - obj.getCurrentPos().getY()) >= DISTANCE);
                } else {
                    assertFalse(o.getCurrentPos().getX() == obj.getCurrentPos().getX());
                }
            }
        }
    }

}
