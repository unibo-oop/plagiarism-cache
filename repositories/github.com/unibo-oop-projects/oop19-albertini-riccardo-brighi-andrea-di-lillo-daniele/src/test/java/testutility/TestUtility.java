package testutility;

import utility.*;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtility {

    private final RangeFactory factory = new RangeFactoryImpl();

    @Test
    void testRangeIterator() {

        final Iterator<Integer> range = factory.standardRange(2).iterator();

        assertTrue(range.hasNext());
        assertEquals(0, range.next());

        assertTrue(range.hasNext());
        assertEquals(1, range.next());

        assertFalse(range.hasNext());

    }

    @Test
    void testWrongRange() {

        assertThrows(IllegalArgumentException.class, () -> factory.standardRangeFrom(2, 0));

    }

    @Test
    void testCountFrom0() {
        final Counter counter = new CounterImpl();
        assertEquals(0, counter.getValue());
        for (final int i : factory.standardRange(3)) {
            counter.increment();
        }
        assertEquals(3, counter.getValue());
        counter.reset();
        assertEquals(0, counter.getValue());
    }

    @Test
    void testCountFromN() {
        final Counter counter = new CounterImpl(3);
        assertEquals(3, counter.getValue());
        for (final int i : factory.standardRange(3)) {
            counter.increment();
        }
        assertEquals(6, counter.getValue());
        counter.reset();
        assertEquals(3, counter.getValue());
    }

    @Test
    void testObserverAndSource() {
        final Source<Integer> s1 = new SourceImpl<>();
        final Source<Integer> s2 = new SourceImpl<>();
        final Integer integer = 2;

        final Observer<Integer> observer = (source, argument) -> {
            assertEquals(s2, source);
            assertEquals(integer, argument);
        };

        s1.addObserver((source, arg) -> {
            assertEquals(s1, source);
            assertEquals(integer, arg);
        });

        s1.notifyObservers(integer);

        s2.addAllObservers(Set.of(observer));

        s2.notifyObservers(integer);

        assertEquals(Set.of(observer), s2.getObserversSet());

        s2.removeObserver(observer);

        assertEquals(Set.of(), s2.getObserversSet());
    }
}
