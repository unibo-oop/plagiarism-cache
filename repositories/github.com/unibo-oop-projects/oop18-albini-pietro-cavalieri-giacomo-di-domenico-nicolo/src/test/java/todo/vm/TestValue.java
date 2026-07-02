package todo.vm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TestValue {
    @Test
    public void testOrThrowWithPresentValues() {
        assertEquals(Value.number(42), Value.number(42).orThrow(RuntimeException.class));
        assertEquals(Value.ascii('p'), Value.ascii('p').orThrow(RuntimeException.class));
    }

    @Test
    public void testOrThrowWithMissingValue() {
        final RuntimeException e1 = getCaughtException(() -> {
            Value.empty().orThrow(RuntimeException.class);
        });
        assertEquals(null, e1.getMessage());

        final RuntimeException e2 = getCaughtException(() -> {
            Value.empty().orThrow(RuntimeException.class, "custom message");
        });
        assertEquals("custom message", e2.getMessage());
    }

    private RuntimeException getCaughtException(final Runnable test) {
        try {
            test.run();
        } catch (final RuntimeException e) {
            return e;
        }
        fail("test didn't raise an exception");
        return null; // Never reached due to the fail() above
    }
}
