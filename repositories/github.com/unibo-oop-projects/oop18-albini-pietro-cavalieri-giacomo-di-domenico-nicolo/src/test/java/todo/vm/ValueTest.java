package todo.vm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValueTest {
    @Test
    public void testNumber() {
        assertEquals("Value(number: 10)", Value.number(10).toString());
        assertEquals(Value.number(10), Value.number(10));
        assertEquals(10, Value.number(10).asNumber());
        assertFalse(Value.number(10).isASCII());
        assertTrue(Value.number(10).isPresent());
        assertNotEquals(Value.number(10), Value.number(11));
        assertNotEquals(Value.number(97), Value.ascii('a'));
        assertNotEquals(Value.number(0), Value.empty());
    }

    @Test
    public void testASCII() {
        assertEquals("Value(ascii: a)", Value.ascii('a').toString());
        assertEquals(Value.ascii('a'), Value.ascii('a'));
        assertEquals(97, Value.ascii('a').asNumber());
        assertTrue(Value.ascii('a').isASCII());
        assertTrue(Value.ascii('a').isPresent());
        assertNotEquals(Value.ascii('a'), Value.ascii('b'));
        assertNotEquals(Value.ascii('a'), Value.empty());
    }

    @Test
    public void testEmpty() {
        assertEquals("Value(empty)", Value.empty().toString());
        assertEquals(Value.empty(), Value.empty());
        assertFalse(Value.empty().isASCII());
        assertFalse(Value.empty().isPresent());
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyGetNumberFails() {
        Value.empty().asNumber();
    }
}
