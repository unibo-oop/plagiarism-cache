package casim.utils;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link BaseBuilder}.
 */
class BaseBuilderTest {
    private static final String NULL_VALUE = "Null Value Error.";
    private static final String PREDICATE_FAIL = "Predicate returned false.";

    /**
     * Test {@link BaseBuilder#checkNonNullValue(Object, String)} method.
     */
    @Test
    void testCheckNonNullValue() {
        final var value = 42;
        final var builder = new BaseBuilder() { };
        assertEquals(value, builder.checkNonNullValue(value, NULL_VALUE));
        assertThrows(IllegalArgumentException.class, () -> builder.checkNonNullValue(null, NULL_VALUE));
    }

    @Test
    void testCheckValue() {
        final var value1 = 42;
        final var value2 = -1;
        final var builder = new BaseBuilder() { };
        final Predicate<Integer> pred = x -> x >= 0;
        assertEquals(value1, builder.checkValue(value1, pred, PREDICATE_FAIL));
        assertThrows(IllegalArgumentException.class, () -> builder.checkValue(value2, pred, PREDICATE_FAIL));
    }

    @Test
    void testRegisterCall() {
        final var builder = new BaseBuilder() { 
            public void testFunction() {
                this.registerCall();
            }
        };
        assertDoesNotThrow(() -> builder.testFunction());
        assertThrows(IllegalStateException.class, () -> builder.testFunction());
    }
}
