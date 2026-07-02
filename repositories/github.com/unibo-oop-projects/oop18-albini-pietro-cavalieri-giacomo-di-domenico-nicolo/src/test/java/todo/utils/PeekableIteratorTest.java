package todo.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class PeekableIteratorTest {
    @Test
    public void testWithEmptyIterator() {
        final PeekableIterator<Object> iter = new PeekableIteratorImpl<>(Collections.emptyList().iterator());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testWithSingleElementIterator() {
        final PeekableIterator<String> iter = new PeekableIteratorImpl<>(Arrays.asList("foo").iterator());
        assertTrue(iter.hasNext());
        assertEquals("foo", iter.peek());
        assertTrue(iter.hasNext());
        assertEquals("foo", iter.peek());
        assertTrue(iter.hasNext());
        assertEquals("foo", iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testWithMultipleElementsIterator() {
        final PeekableIterator<String> iter = new PeekableIteratorImpl<>(Arrays.asList("foo", "bar").iterator());
        assertTrue(iter.hasNext());
        assertEquals("foo", iter.peek());
        assertTrue(iter.hasNext());
        assertEquals("foo", iter.peek());
        assertTrue(iter.hasNext());
        assertEquals("foo", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("bar", iter.peek());
        assertTrue(iter.hasNext());
        assertEquals("bar", iter.peek());
        assertTrue(iter.hasNext());
        assertEquals("bar", iter.next());
        assertFalse(iter.hasNext());
    }
}
