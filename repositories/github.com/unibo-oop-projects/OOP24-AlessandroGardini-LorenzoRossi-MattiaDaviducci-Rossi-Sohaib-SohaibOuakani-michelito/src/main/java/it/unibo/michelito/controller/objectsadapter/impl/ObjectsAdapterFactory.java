package it.unibo.michelito.controller.objectsadapter.impl;

import it.unibo.michelito.controller.objectsadapter.api.ObjectsAdapter;
import it.unibo.michelito.util.GameObject;

import java.util.Set;
import java.util.function.Function;

/**
 * Factory class for creating instances of {@link ObjectsAdapter}.
 * Its use is recommended for obtaining {@link ObjectsAdapter} instances.
 */
public final class ObjectsAdapterFactory {
    /**
     * Private constructor preventing instantiation.
     */
    private ObjectsAdapterFactory() {
    }

    /**
     * Creates and returns an instance of {@link ObjectsAdapter}.
     * The implementation choice may vary.
     *
     * @param levelGenerator {@link Function} that provided a number return a Set of {@link GameObject}.
     * @return a new {@link ObjectsAdapter} instance.
     */
    public static ObjectsAdapter createObjectsAdapter(final Function<Integer, Set<GameObject>> levelGenerator) {
        return new ObjectsAdapterWithCache(levelGenerator);
    }
}
