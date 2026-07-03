package oop.lit.util;
/**
 * An object that when attached to one (or more) Observable will do something when notified by the observed object.
 */
public interface Observer {
    /**
     * A method called by the observed object (when the observed object changes).
     */
    void notifyChange();
}
