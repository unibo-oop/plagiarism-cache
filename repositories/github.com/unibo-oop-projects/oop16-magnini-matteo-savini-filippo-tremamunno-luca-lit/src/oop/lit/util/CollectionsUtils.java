package oop.lit.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * A utility class about collections.
 */
public final class CollectionsUtils {
    private CollectionsUtils() {
        //hide constructor
    }

    /**
     * Get a map containing all provided elements as values. The key for an element is obtained using the provided function.
     * If two elements provide the same key a number will be added in order to differentiate them.
     * (A -> "a", B -> "a") --> (("a", A), ("a (1)", B))
     * @param elements
     *      the elements.
     * @param stringMapper
     *      a function used to get a string from the provided elements.
     * @return
     *      the map.
     *
     * @param <T>
     *      the elements type.
     */
    public static <T> Map<String, T> mapWithDifferentStrings(final Iterable<T> elements, final Function<? super T, String> stringMapper) {
        final Map<String, T> res = new HashMap<>();
        for (final T element : elements) {
            String name = stringMapper.apply(element);
            if (res.containsKey(name)) {
                int i = 1;
                while (res.containsKey(name + " (" + i + ")")) {
                    i++;
                }
                name = name + " (" + i + ")";
            }
            res.put(name, element);
        }
        return res;
    }

    /**
     * @param elements
     *      some sets.
     * @return
     *      an optional of a set containing only elements contained in all
     *      the provided sets, or an empty optional if the provided list does
     *      not contain any set.
     * @param <T>
     *            the type of objects contained in the set.
     */
    public static <T> Optional<Set<T>> intersection(final List<Set<T>> elements) {
        if (elements.isEmpty()) {
            return Optional.empty();
        }
        final Set<T> res = new HashSet<>(elements.get(0));
        for (int i = 1; i < elements.size() && !res.isEmpty(); i++) {
            res.retainAll(elements.get(i));
        }
        return Optional.of(res);
    }
}
