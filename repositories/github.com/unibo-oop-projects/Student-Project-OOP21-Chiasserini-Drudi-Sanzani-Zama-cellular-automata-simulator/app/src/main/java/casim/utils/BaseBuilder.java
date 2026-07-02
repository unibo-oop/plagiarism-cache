package casim.utils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Abstract class that describes a Builder.
 * Not thread safe.
 */
public class BaseBuilder {
    private final Set<String> calledMethods = new HashSet<>();

    /**
     * Register the method that was called last.
     * 
     * @exception IllegalStateException if the method that was called last was called twice.
     */
    public void registerCall() {
        final var stackTrace = Thread.currentThread().getStackTrace();
        final var method = stackTrace[2].getMethodName();
        if (this.calledMethods.contains(method)) {
            throw new IllegalStateException(method + " has been called twice.");
        }
        this.calledMethods.add(method);
    }

    /**
     * Check a value with a {@link Predicate} and throws an exception if the test fails.
     * 
     * @exception IllegalArgumentException if the value fails the {@link Predicate} test.
     * 
     * @param <T> The type of the value.
     * @param value The value that will be set.
     * @param predicate {@link Predicate} used to check the value.
     * @param errMsg Message displayed in case of failed test.
     * @return value if predicate returns true.
     */
    public <T> T checkValue(final T value, final Predicate<T> predicate, final String errMsg) {
        if (!predicate.test(value)) {
            throw new IllegalArgumentException(errMsg);
        }
        return value;
    }

    /**
     * Check if the value is not null, if it is throws an exception.
     * 
     * @exception IllegalArgumentException if value is null.
     * 
     * @param <T> The type of the value.
     * @param value The value that will be set.
     * @param errMsg The Message of the exception.
     * @return value if predicate returns true.
     */
    public <T> T checkNonNullValue(final T value, final String errMsg) {
        return this.checkValue(value, Objects::nonNull, errMsg);
    }
}
