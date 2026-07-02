package todo.vm.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SpanTest {
    @Test
    public void testBasicSpan() {
        final Span span = new Span(1, 2);
        assertEquals(1, span.getStart());
        assertEquals(2, span.getEnd());
    }

    @Test
    public void testDummySpan() {
        assertFails(() -> {
            Span.DUMMY.getStart();
        }, IllegalStateException.class);
        assertFails(() -> {
            Span.DUMMY.getEnd();
        }, IllegalStateException.class);
    }

    @Test
    public void testMerge() {
        assertEquals(new Span(1, 4), new Span(1, 2).to(new Span(3, 4)));
        assertEquals(new Span(1, 4), new Span(3, 4).to(new Span(1, 2)));
        assertEquals(new Span(1, 4), new Span(1, 4).to(new Span(1, 4)));
        assertFails(() -> {
            Span.DUMMY.to(new Span(1, 2));
        }, IllegalArgumentException.class);
        assertFails(() -> {
            new Span(1, 2).to(Span.DUMMY);
        }, IllegalArgumentException.class);
        assertFails(() -> {
            Span.DUMMY.to(Span.DUMMY);
        }, IllegalArgumentException.class);
    }

    @Test
    public void testEquals() {
        assertEquals(new Span(1, 1), new Span(1, 1));
        assertNotEquals(new Span(1, 2), new Span(1, 3));
        assertNotEquals(new Span(1, 2), new Span(2, 2));
    }

    @Test
    public void testCompareTo() {
        assertTrue(new Span(0, 0).compareTo(new Span(1, 1)) < 0);
        assertTrue(new Span(1, 1).compareTo(new Span(0, 0)) > 0);
        assertTrue(new Span(1, 1).compareTo(new Span(1, 2)) < 0);
        assertTrue(new Span(1, 1).compareTo(new Span(1, 0)) > 0);
        assertEquals(new Span(1, 1), new Span(1, 1));
        assertFails(() -> {
            Span.DUMMY.compareTo(new Span(1, 2));
        }, IllegalArgumentException.class);
        assertFails(() -> {
            new Span(1, 2).compareTo(Span.DUMMY);
        }, IllegalArgumentException.class);
        assertFails(() -> {
            Span.DUMMY.compareTo(Span.DUMMY);
        }, IllegalArgumentException.class);
    }

    private void assertFails(final Runnable func, final Class<? extends Exception> kind) {
        try {
            func.run();
            fail("the function didn't fail");
        } catch (final Exception e) {
            assertEquals(kind, e.getClass());
        }
    }
}
