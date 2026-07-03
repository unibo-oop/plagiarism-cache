package hotelmaster.utility.collections;

import java.util.function.Consumer;

/**
 * An operation which can trigger {@link Consumer#accept} on a
 * {@link Triggering}.
 */
public enum TriggeringOperation {
    /**
     * An element has been added to the collection, through any function.
     */
    ADD,
    /**
     * An element has been removed from the collection, through any function
     * (for example: remove, removeIf, removeAll).
     */
    REMOVE,

    /**
     * An element in the collection has been updated.
     */
    UPDATE_ELEMENT
}
