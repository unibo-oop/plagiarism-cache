package utilities;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Utility class that provides useful methods.
 */
public final class Utilities {

    private Utilities() {
    }

    /**
     * Return a random collection's element.
     * @param collection the collection by which get the element
     * @param <X> the type of collection's elements
     * @return a collection's random element
     */
    public static <X> X getRandom(final Collection<X> collection) {
        final List<X> list = new LinkedList<>(collection);
        Collections.shuffle(list);
        return list.get(0);
    }

    /**
     * Checks if a variable number of objects are null, in that case an IllegalArgumentException will be thrown.
     * @throws IllegalArgumentException
     *              with NULL_ARGUMENT message
     * @param objects
     *              the objects to check
     */
    public static void requireNonNull(final Object...objects) {
        if (Arrays.stream(objects).anyMatch(obj -> Utilities.isOrContainsNull(obj))) {
            throw new IllegalArgumentException(Messages.NULL_ARGUMENT);
        }
    }

    /**
     * Checks if an object is null or, in case it is a Collection or an Array, contains null elements.
     * Note that if you are using your own made collection class, this won't work.
     * @param obj
     *              the object to check
     * @return a boolean
     *              true if the object is or contains null element, false otherwise
     */
    public static boolean isOrContainsNull(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj.getClass().isArray()) {
            return Arrays.stream((Object[]) obj).anyMatch(elem -> Utilities.isOrContainsNull(elem));
        }
        if (obj.getClass().isAssignableFrom(Collections.class)) {
            return ((Collection<?>) obj).stream().anyMatch(elem -> Utilities.isOrContainsNull(elem));
        }
        return false;
    }

    /**
     * Allows to get the screen dimension.
     * @return the screen dimension
     */
    public static Dimension getScreenDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     * Allows to get the screen ratio (i.e. width / height),
     * @return a double representing the ratio
     */
    public static double getScreenRatio() {
        return Utilities.getScreenDimension().getWidth() / Utilities.getScreenDimension().getHeight();
    }
}
