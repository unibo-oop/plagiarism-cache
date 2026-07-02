package todo.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

public final class Checks {
    private Checks() {
    }

    /**
     * @param condition the boolean expression determining wether an exception will
     *            be thrown
     * @param type the {@link Class} of the exception to be thrown
     * @param message a message the exception will receive when created
     * @param <T> the type of the exception
     * @throws T the exception that may be thrown
     */
    public static <T extends Exception> void require(final boolean condition, final Class<T> type, final String message)
            throws T {
        if (!condition) {
            try {
                throw type.getConstructor(String.class).newInstance(message);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                    | NoSuchMethodException e) {
                throw new RuntimeException("Failed to throw exception: " + e.toString());
            }
        }
    }

    /**
     * @param condition the boolean expression determining wether an exception will
     *            be thrown
     * @param type the {@link Class} of the exception to be thrown without a
     *            specific message
     * @param <T> the type of the exception
     * @throws T the exception that may be thrown
     */
    public static <T extends Exception> void require(final boolean condition, final Class<T> type) throws T {
        if (!condition) {
            try {
                throw type.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Failed to throw exception: " + e.toString());
            }
        }
    }

    /**
     * @param iterable the {@link Iterable} containing the elements to check
     * @param p the {@link Predicate} which will determine wether an exception will
     *            be thrown for each element inspected
     * @param type the {@link Class} of the exception to be thrown
     * @param message a message the exception will receive when created
     * @param <X> the type of the elements of the Iterable
     * @param <T> the type of the exception
     * @throws T the exception that may be thrown
     */
    public static <X, T extends Exception> void requireOnIterable(final Iterable<X> iterable, final Predicate<X> p,
            final Class<T> type, final String message) throws T {
        for (final X elem : iterable) {
            require(p.test(elem), type, message);
        }
    }

    /**
     * @param iterable the {@link Iterable} containing the elements to check
     * @param p the {@link Predicate} which will determine wether an exception will
     *            be thrown for each element inspected
     * @param type the {@link Class} of the exception to be thrown without a
     *            specific message
     * @param <X> the type of the elements of the Iterable
     * @param <T> the type of the exception
     * @throws T the exception that may be thrown
     */
    public static <X, T extends Exception> void requireOnIterable(final Iterable<X> iterable, final Predicate<X> p,
            final Class<T> type) throws T {
        for (final X elem : iterable) {
            require(p.test(elem), type);
        }
    }
}
