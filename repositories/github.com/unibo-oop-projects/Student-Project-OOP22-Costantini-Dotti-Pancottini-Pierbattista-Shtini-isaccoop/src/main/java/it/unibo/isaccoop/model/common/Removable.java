package it.unibo.isaccoop.model.common;
/**
 * Removable interface to model objects delegates to delete elements.
 */
public interface Removable {

    /**
     * Remove a certain element.
     * @param e
     */
    void remove(MapElement e);

}
