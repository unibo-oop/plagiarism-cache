package todo.utils;

/**
 * This interface represents an object that can be identified uniquely during
 * the execution of the program. No instances of a class can have the same ID.
 */
public interface Identifiable {
    /**
     * @return the unique identifier of this class
     */
    UniqueId getId();
}
