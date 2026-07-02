package it.trashwarecesena.trustalodesktopclient.repository.test.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import it.trashwarecesena.trustalodesktopclient.model.test.TestConstants;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * A test for the utility class {@link ExtendedObjects}.
 * @author Manuel Bonarrigo
 *
 */
@RunWith(JUnitPlatform.class)
public class TestExtendedObjects {

    private static final String RIGHT_A = "           a";
    private static final String LEFT_A = "a        ";
    private static final String CENTER_A = "        a        ";

    /**
     * Test over the check for string emptiness.
     */
    @Test
    public void testStringEmptiness() {
        assertTrue(Objects.isNull(ExtendedObjects.requireNonEmpty(null)));
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requireNonEmpty(TestConstants.EMPTY_STRING);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requireNonEmpty(TestConstants.SINGLE_SPACE_STRING);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requireNonEmpty(TestConstants.MULTI_SPACE_STRING);
        });
        assertTrue(ExtendedObjects.requireNonEmpty(TestConstants.A_STRING).equals(TestConstants.A_STRING));
        assertTrue(ExtendedObjects.requireNonEmpty(TestConstants.A_DIFFERENT_STRING)
                .equals(TestConstants.A_DIFFERENT_STRING));
        assertTrue(ExtendedObjects.requireNonEmpty(RIGHT_A).equals(RIGHT_A));
        assertTrue(ExtendedObjects.requireNonEmpty(LEFT_A).equals(LEFT_A));
        assertTrue(ExtendedObjects.requireNonEmpty(CENTER_A).equals(CENTER_A));
    }

    /**
     * Test over the check for strictly positive Integer.
     */
    @Test
    public void testIntegerPositivity() {
        assertTrue(Objects.isNull(ExtendedObjects.requireNonEmpty(null)));
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requirePositive(TestConstants.A_NEGATIVE_INTEGER);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requirePositive(TestConstants.A_DIFFERENT_NEGATIVE_INTEGER);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requirePositive(TestConstants.INT_ZERO);
        });
        assertTrue(ExtendedObjects.requirePositive(TestConstants.A_POSITIVE_INTEGER)
                .equals(TestConstants.THE_SAME_POSITIVE_INTEGER));
        assertTrue(ExtendedObjects.requirePositive(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .equals(TestConstants.A_DIFFERENT_POSITIVE_INTEGER));
    }

    /**
     * Test over the check for strictly positive Float.
     */
    @Test
    public void testFloatPositivity() {
        assertTrue(Objects.isNull(ExtendedObjects.requireNonEmpty(null)));
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requirePositive(TestConstants.A_NEGATIVE_FLOAT);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requirePositive(TestConstants.A_DIFFERENT_NEGATIVE_FLOAT);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requirePositive(TestConstants.FLO_ZERO);
        });
        assertTrue(ExtendedObjects.requirePositive(TestConstants.A_POSITIVE_FLOAT)
                .equals(TestConstants.THE_SAME_POSITIVE_FLOAT));
        assertTrue(ExtendedObjects.requirePositive(TestConstants.A_DIFFERENT_POSITIVE_FLOAT)
                .equals(TestConstants.A_DIFFERENT_POSITIVE_FLOAT));
    }

    /**
     * Test over the check for a non negative Integer.
     */
    @Test
    public void testIntegerNonNegativity() {
        assertTrue(Objects.isNull(ExtendedObjects.requireNonEmpty(null)));
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requireNonNegative(TestConstants.A_NEGATIVE_INTEGER);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requireNonNegative(TestConstants.A_DIFFERENT_NEGATIVE_INTEGER);
        });
        assertTrue(ExtendedObjects.requireNonNegative(TestConstants.INT_ZERO).equals(TestConstants.INT_ZERO));
        assertTrue(ExtendedObjects.requireNonNegative(TestConstants.INT_ZERO).equals(0));
        assertTrue(ExtendedObjects.requireNonNegative(TestConstants.A_POSITIVE_INTEGER)
                .equals(TestConstants.THE_SAME_POSITIVE_INTEGER));
        assertTrue(ExtendedObjects.requireNonNegative(TestConstants.A_DIFFERENT_POSITIVE_INTEGER)
                .equals(TestConstants.A_DIFFERENT_POSITIVE_INTEGER));
    }

    /**
     * Test over the check for a non negative Float.
     */
    @Test
    public void testFloatNonNegativity() {
        assertTrue(Objects.isNull(ExtendedObjects.requireNonEmpty(null)));
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requireNonNegative(TestConstants.A_NEGATIVE_FLOAT);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ExtendedObjects.requireNonNegative(TestConstants.A_DIFFERENT_NEGATIVE_FLOAT);
        });
        assertTrue(ExtendedObjects.requireNonNegative(TestConstants.FLO_ZERO).equals(TestConstants.FLO_ZERO));
        assertTrue(ExtendedObjects.requireNonNegative(TestConstants.FLO_ZERO).equals(0f));
        assertTrue(ExtendedObjects.requireNonNegative(TestConstants.A_POSITIVE_FLOAT)
                .equals(TestConstants.THE_SAME_POSITIVE_FLOAT));
        assertTrue(ExtendedObjects.requireNonNegative(TestConstants.A_DIFFERENT_POSITIVE_FLOAT)
                .equals(TestConstants.A_DIFFERENT_POSITIVE_FLOAT));
    }

}
