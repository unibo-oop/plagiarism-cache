package casim.utils;

/**
 * Models a Supplier that may throw an exception.
 *
 * @param <T> the type of the output value.
 * @param <E> the type of the exception.
 */
public interface FailableSupplier<T, E extends Exception> {
    /**
     * The method that the supplier runs.
     * 
     * @return the produced value.
     * @throws E the exception that may be thrown.
     */
    T run() throws E;
}
