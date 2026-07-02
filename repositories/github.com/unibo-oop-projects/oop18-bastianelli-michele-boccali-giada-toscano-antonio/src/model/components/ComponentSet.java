package model.components;

import java.util.stream.Stream;

/**
 * A collection of entity components.
 * 
 * @param <T>
 */
public interface ComponentSet<T> {

    /**
     * Add a component into the collection.
     * 
     * @param component the component to be added
     */
    void add(T component);

    /**
     * Remove the component according to its Class type.
     * 
     * @param type the component Class
     */
    <C extends T> void remove(Class<C> type);

    /**
     * Remove the component according to its implemented interface.
     * 
     * @param component the implemented interface
     */
    <C extends T> void remove(C component);

    /**
     * Remove all components from the component set.
     */
    void clear();

    /**
     * Get the component by looking for for its Class type.
     * 
     * @param type the the component Class
     * @return the component
     */
    <C extends T> C getComponent(Class<C> type);

    /**
     * Get the component by looking for its Class type.
     * 
     * @param type the component Class
     * @return true if the component class is contained, false otherwise
     */
    <C extends T> boolean isPresent(Class<C> type);

    /**
     * @return the stream of components.
     */
    Stream<T> stream();

}
