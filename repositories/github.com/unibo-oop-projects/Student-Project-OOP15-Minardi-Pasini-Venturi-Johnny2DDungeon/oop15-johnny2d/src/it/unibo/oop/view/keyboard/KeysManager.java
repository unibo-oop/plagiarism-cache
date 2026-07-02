package it.unibo.oop.view.keyboard;

/**
 * Interface for a generic Keyboard manager.
 * 
 * @param <I>
 *            Type of input, b.p. a wrapping type for the keys of keyboard.
 * @param <O>
 *            Type of output after the keys processing.
 */
public interface KeysManager<I, O> extends Manager {

    /**
     * @param key
     *            adds the key passed.
     */
    void addKey(I key);

    /**
     * @param key
     *            removes the key passed.
     */
    void removeKey(I key);

    /**
     * @param key
     * 
     * @return
     */
    boolean isAKeyPressed(I key);

    /**
     * @return some value associated with the keys pressed setup.
     */
    O processKeys();
}