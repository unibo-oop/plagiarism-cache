package utility;

@FunctionalInterface
public interface Observer<T> {

    /**
     * @param source   the source of the call
     * @param argument the new object
     */
    void update(Source<? extends T> source, T argument);
}
