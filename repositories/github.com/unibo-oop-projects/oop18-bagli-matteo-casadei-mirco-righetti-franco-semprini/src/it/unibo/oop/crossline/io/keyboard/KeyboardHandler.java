package it.unibo.oop.crossline.io.keyboard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The class of KeyboardHandler.
 */
public class KeyboardHandler implements KeyboardObservable {

    /**
     * List of keyboard buttons.
     */
    private List<KeyboardObserver> observers;
    /**
     * The keyCode.
     */
    private int keyCode;

    /**
     * KeyboardHanlder constructor.
     */
    public KeyboardHandler() {
        this.observers = new ArrayList<KeyboardObserver>();
    }

    @Override
    public final boolean registerObserver(final KeyboardObserver observer) {
        if (observer != null) {
            this.observers.add(observer);
            return true;
        }
        return false;
    }

    @Override
    public final boolean notifyObserver() {
        final Iterator<KeyboardObserver> it = observers.iterator();
        if (it != null) {
            while (it.hasNext()) {
                final KeyboardObserver observer = it.next();
                observer.update(this.keyCode);
            }
            return true;
        }
        return false;
    }

    @Override
    public final boolean removeObserver(final KeyboardObserver observer) {
        if (observer != null) {
            this.observers.remove(observer);
            return true;
        }
        return false;
    }

    @Override
    public final boolean updateKeyPressed(final int keyCode) {
        this.keyCode = keyCode;
        this.notifyObserver();
        return true;
    }

    /**
     * @return the observers
     */
    public final List<KeyboardObserver> getObservers() {
        return observers;
    }

    /**
     * @param observers the observers to set
     */
    public final void setObservers(final List<KeyboardObserver> observers) {
        this.observers = observers;
    }

    /**
     * @return the keyCode
     */
    public final int getKeyCode() {
        return keyCode;
    }

    /**
     * @param keyCode the keyCode to set
     */
    public final void setKeyCode(final int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * ToString method.
     */
    @Override
    public String toString() {
        return "KeyboardHandler [" + "observers=" + this.getObservers() + ",\n keyCode=" + this.getKeyCode() + "]";
    }

    /**
     * HashCode method.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + keyCode;
        result = prime * result + ((observers == null) ? 0 : observers.hashCode());
        return result;
    }

    /**
     * Equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KeyboardHandler other = (KeyboardHandler) obj;
        if (keyCode != other.keyCode) {
            return false;
        }
        if (null == observers) {
            if (other.observers != null) {
                return false;
            }
        } else if (!observers.equals(other.observers)) {
            return false;
        }
        return true;
    }
}
