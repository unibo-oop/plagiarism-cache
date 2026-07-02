package it.unibo.oop.view.keyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for a generic KeysManager.
 *
 * @param <I>
 *            Type of input, b.p. a wrapping type for the keys of keyboard.
 * @param <O>
 *            Type of output after the keys processing.
 */
public abstract class AbstractKeysManager<I, O> implements KeysManager<I, O> {

    private List<I> keysPressed; // list containing long-pressed keys.
    private List<I> keysTyped; // list containing typed keys.

    public AbstractKeysManager() {
        this.reset();
    }

    @Override
    public final synchronized void reset() {
        this.keysPressed = new ArrayList<>();
        this.keysTyped = new ArrayList<>();
    }

    @Override
    public synchronized void addKey(final I key) {
        if (!this.keysPressed.contains(key)) {
            this.keysPressed.add(key);
        }
        // in caso di pressione prolungata la key viene inserita
        // solo la prima volta.
        if (!this.keysTyped.contains(key)) {
            this.keysTyped.add(key);
        }
    }

    @Override
    public synchronized void removeKey(final I key) {
        if (this.keysPressed.contains(key)) {
            this.keysPressed.remove(key);
        }
    }

    @Override
    public synchronized boolean isAKeyPressed(final I key) {
        return this.keysPressed.contains(key) || this.keysTyped.contains(key);
    }

    @Override
    public abstract O processKeys();

    protected void processPressed(final int limit, final List<I> outList) {
        this.process(limit, this.keysPressed, outList);
    }

    protected synchronized void processTyped(final int limit, final List<I> outList) {
        this.process(limit, this.keysTyped, outList);
        this.keysTyped = new ArrayList<>(); /* resetto la keysTyped list */
    }

    private void process(final int noElem, final List<I> inList, final List<I> outList) {
        for (final I key : inList) {
            if (outList.size() <= noElem && !outList.contains(key)) {
                outList.add(key);
            } else {
                break;
            }
        }
    }
}