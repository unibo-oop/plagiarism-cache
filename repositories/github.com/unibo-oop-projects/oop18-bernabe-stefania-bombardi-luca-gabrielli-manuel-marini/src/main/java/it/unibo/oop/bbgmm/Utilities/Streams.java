package it.unibo.oop.bbgmm.Utilities;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Streams {
    /**
     * Utility class for streams
     */
    private Streams() {
    }

    /**
     * Generate a {@link Stream} from an {@link Iterable}
     * @param it
     *          the {@link Iterable}
     * @param <T>
     *          Generic
     * @return the {@link Stream}
     */

    public static <T> Stream<T> stream(final Iterable<T> it){
        return StreamSupport.stream(it.spliterator(), false);
    }

    /**
     * Generate a {@link Stream} from an {@link Iterator}
     * @param it
     *          the {@link Iterator}
     * @param <T>
     *          Generic
     * @return the {@link stream}
     */
    public static <T> Stream <T> stream(final Iterator<T> it ){
        return stream(() -> it);
    }
}
