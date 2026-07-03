package hotelmaster.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import hotelmaster.utility.collections.Trigger;
import hotelmaster.utility.collections.TriggeringOperation;
import hotelmaster.utility.collections.TriggeringSet;

/**
 * {@link TriggeringSet} tests.
 */
public class TriggeringSetTest {

    /**
     * 
     */
    @org.junit.Test
    public void adding() {
        final TriggeringSet<Integer> set = TriggeringSet.create();
        assertTrue("Should be able to add a non-existing element", set.add(2));
        assertFalse("Shouldn't be able to add an existing element", set.add(2));
        final Set<Integer> ints = new HashSet<>();
        ints.add(3);
        ints.add(4);
        assertTrue("Should be able to add non-existing collections", set.addAll(ints));
        assertFalse("Shouldn't be able to add existing collections", set.addAll(ints));
        set.add(3);
        assertFalse("Shouldn't be able to add partially existing collections", set.addAll(ints));
    }

    /**
     * 
     */
    @org.junit.Test
    public void removing() {
        final TriggeringSet<Integer> set = TriggeringSet.create();
        assertFalse("Shouldn't be able to remove non-existing elements", set.remove(3));
        set.add(3);
        assertTrue("Should be able to remove existing elements", set.remove(3));
        final Set<Integer> ints = new HashSet<>();
        ints.add(3);
        ints.add(4);
        set.addAll(ints);
        assertTrue("Should be able to remove existing collections", set.removeAll(ints));
        assertFalse("Shouldn't be able to remove non-existing collections", set.removeAll(ints));
    }

    /**
     * 
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void unsupportedTriggers() {
        final TriggeringSet<Integer> set = TriggeringSet.create();
        set.addTrigger(Trigger.create(TriggeringOperation.UPDATE_ELEMENT, (e) -> {
            return;
        }));
    }

    /**
     * 
     */
    @org.junit.Test
    public void triggers() {
        final TriggeringSet<Integer> set = TriggeringSet.create();
        final Set<Integer> mirror = new HashSet<>();
        set.addTrigger(Trigger.create(TriggeringOperation.ADD, e -> mirror.add(e)));
        set.addTrigger(Trigger.create(TriggeringOperation.REMOVE, e -> mirror.remove(e)));
        set.add(4);
        set.add(3);
        set.add(2);
        assertTrue("Sets should be equal", set.containsAll(mirror));
        set.remove(2);
        set.remove(3);
        assertTrue("Sets should be equal", set.containsAll(mirror));
    }
}
