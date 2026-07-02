package it.unibo.biscia.view.utils;

/**
 * Sequential behavior of an Object. Most of the times used along {@link State}
 * 
 * @see State
 *
 */
public interface Switchable {

    /**
     * Sequentially before.
     */
    void setPrevious();

    /**
     * Sequentially after.
     */
    void setNext();

}
