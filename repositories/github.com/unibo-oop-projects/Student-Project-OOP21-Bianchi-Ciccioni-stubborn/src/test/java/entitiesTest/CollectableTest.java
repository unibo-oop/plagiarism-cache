package entitiesTest;

import org.junit.jupiter.api.Test;

import models.Collectable;
import models.CollectableImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollectableTest {

    private static int MIN = 60;
    private static int NUMCOLL = 10;
    private List<Collectable> listCollectables = new ArrayList<>();

    @Test
    public void testCollectable() {
        for (int i = 0; i < NUMCOLL; i++) {
            listCollectables.add(new CollectableImpl());
        }
        assertEquals(NUMCOLL, listCollectables.size());
        listCollectables.removeIf(col -> col.getPoints() < MIN);
        Iterator<Collectable> iter = listCollectables.iterator();
        while (iter.hasNext()) {
            assertTrue(iter.next().getPoints() > MIN);
        }
    }

}
