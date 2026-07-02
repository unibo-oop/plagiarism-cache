package todo.utils;

/**
 * This interface can be implemented by objects that have to manually free up
 * any used resources.
 */
public interface Disposable {
    /**
     * Manually dispose of any loaded resource. If the object is used after calling
     * this method, its behavior is undefined.
     */
    void dispose();
}
