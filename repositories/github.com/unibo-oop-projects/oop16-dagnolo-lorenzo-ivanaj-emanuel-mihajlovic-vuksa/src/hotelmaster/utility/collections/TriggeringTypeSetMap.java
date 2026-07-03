package hotelmaster.utility.collections;

/**
 * A {@link TypeSetMap} which performs the actions given through
 * {@link Triggering#addConsumer}.
 *
 * @param <E>
 *            the type of the elements
 */
public interface TriggeringTypeSetMap<E> extends TypeSetMap<E>, Triggering<E> {
    /**
     * Returns a new {@link TriggeringTypeSetMap}.
     * 
     * @param <E>
     *            the type of the elements.
     * @return the new instance
     */
    static <E> TriggeringTypeSetMap<E> create() {
        return new TriggeringTypeSetMapImpl<>();
    }
}
