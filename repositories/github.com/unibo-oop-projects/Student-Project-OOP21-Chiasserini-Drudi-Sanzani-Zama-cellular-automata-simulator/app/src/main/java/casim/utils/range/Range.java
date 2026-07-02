package casim.utils.range;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Interface that models a range of elements.
 * Used to provide something that can mimic python's range function.
 * 
 * @param <T> the type of the values that the range iterates over.
 */
public interface Range<T> extends Iterable<T> {
    /**
     * Return a stream from {@link Range}.
     * @return a Stream of T.
     */
    default Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }
}
