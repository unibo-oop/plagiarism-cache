package hotelmaster.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import hotelmaster.test.typesetmap.Animal;
import hotelmaster.test.typesetmap.BigCat;
import hotelmaster.test.typesetmap.Bird;
import hotelmaster.test.typesetmap.Dog;
import hotelmaster.utility.collections.Trigger;
import hotelmaster.utility.collections.TriggeringOperation;
import hotelmaster.utility.collections.TriggeringTypeSetMap;

/**
 * Tests for {@link TriggeringTypeSetMap}.
 */
public class TriggeringTypeSetMapTest {

    /**
     * 
     */
    @org.junit.Test
    public void addTriggers() {
        final TriggeringTypeSetMap<Animal> instance = TriggeringTypeSetMap.create();
        instance.addTrigger(Trigger.create(TriggeringOperation.ADD, (e) -> {
            return;
        }));
        instance.addTrigger(Trigger.create(TriggeringOperation.REMOVE, (e) -> {
            return;
        }));

    }

    /**
     * 
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void addUnsupportedTriggers() {
        final TriggeringTypeSetMap<Animal> instance = TriggeringTypeSetMap.create();
        instance.addTrigger(Trigger.create(TriggeringOperation.UPDATE_ELEMENT, (e) -> {
            return;
        }));
    }

    /**
     * 
     */
    @org.junit.Test
    public void adding() {
        final TriggeringTypeSetMap<Animal> instance = TriggeringTypeSetMap.create();
        final List<Animal> added = new ArrayList<>();
        instance.addTrigger(Trigger.create(TriggeringOperation.ADD, elem -> added.add(elem)));
        instance.add(new Dog("corgi", false));
        final Bird bird = new Bird("macaw");
        instance.add(bird);
        assertTrue("added should contain all elements in instance", added.containsAll(instance));
    }

    /**
     * 
     */
    @org.junit.Test
    public void removing() {
        final TriggeringTypeSetMap<Animal> instance = TriggeringTypeSetMap.create();
        final List<Animal> removed = new ArrayList<>();
        instance.addTrigger(Trigger.create(TriggeringOperation.REMOVE, elem -> removed.add(elem)));
        final BigCat cat = new BigCat("lion");
        instance.add(cat);
        instance.remove(cat);
        assertTrue("removed should contain the removed element", removed.contains(cat));
    }
}
