package todo.utils;

/**
 * This interface represents any object that can be copied.
 *
 * @param <T> is the type of object that is returned after copy (i.e. the self
 *            type)
 */
public interface Copyable<T> {
    T copy();
}
