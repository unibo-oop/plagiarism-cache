package todo.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Test;

public class ChecksTest {
    private final static Collection<Integer> ITERABLE = Arrays.asList(10, 20, 30);
    private final static Class<? extends RuntimeException> EXCEPTION_CLASS = IllegalStateException.class;
    private final static String MESSAGE = "correct!";

    @Test
    public void testRequire() {
        testException(() -> Checks.require(false, EXCEPTION_CLASS), EXCEPTION_CLASS, Optional.empty());
    }

    @Test
    public void testRequireWithMessage() {
        testException(() -> Checks.require(false, EXCEPTION_CLASS, MESSAGE), EXCEPTION_CLASS, Optional.of(MESSAGE));
    }

    @Test
    public void testOnIterable() {
        testException(() -> Checks.requireOnIterable(ITERABLE, i -> i < 15, EXCEPTION_CLASS), EXCEPTION_CLASS,
                Optional.empty());
    }

    @Test
    public void testOnIterableWithMessage() {
        testException(() -> Checks.requireOnIterable(ITERABLE, i -> i < 15, EXCEPTION_CLASS, MESSAGE), EXCEPTION_CLASS,
                Optional.of(MESSAGE));
    }

    @Test
    public void testOnIterableNoException() {
        try {
            Checks.requireOnIterable(ITERABLE, i -> i < 35, EXCEPTION_CLASS);
        } catch (final Exception e) {
            fail();
        }
    }

    private <T extends Exception> void testException(final Thrower exceptionThrower, final Class<T> expectedException,
            final Optional<String> expectedMessage) {
        try {
            exceptionThrower.run();
        } catch (final Exception e) {
            assertEquals(expectedException, e.getClass());
            if (expectedMessage.isPresent()) {
                assertEquals(expectedMessage.get(), e.getMessage());
            } else {
                assertEquals(null, e.getMessage());
            }
        }
    }

    @FunctionalInterface
    private interface Thrower {
        void run();
    }
}
