package hotelmaster.utility.collections;

import java.util.Collection;
import java.util.Set;

/**
 * A set extending the {@link Triggering} interface.
 * 
 * @param <E>
 *            the type of the elements
 */
public interface TriggeringSet<E> extends Triggering<E>, Set<E> {

    /**
     * Instances a new {@link TriggeringSet}.
     * 
     * @param <E>
     *            the type of the elements
     * @return the new instance
     */
    static <E> TriggeringSet<E> create() {
        return new TriggeringSetImpl<>();
    }

    /**
     * Instances a new {@link TriggeringSet} from a given collection.
     * 
     * @param <E>
     *            the type of the elements
     * @param collection
     *            the collection
     * @return the new instance
     */
    static <E> TriggeringSet<E> from(Collection<? extends E> collection) {
        return new TriggeringSetImpl<>(collection);
    }
}
