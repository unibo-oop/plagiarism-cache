package it.unibo.oop.crossline.io.mouse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The class of MouseHandler.
 */
public class MouseHandler implements MouseObservable {

    /**
     * Enum of modes of update.
     */
    public enum MODE {
        /**
         * None.
         */
        NONE,
        /**
         * Update.
         */
        UPDATE,
        /**
         * Update position.
         */
        UPDATE_POSITION,
        /**
         * Update position dragger.
         */
        UPDATE_POSITION_DRAGGER,
        /**
         * Scrolled.
         */
        SCROLLED
    }

    /**
     * List of mouse buttons.
     */
    private List<MouseObserver> observers;
    /**
     * The keyCode.
     */
    private int keyCode;
    /**
     * The coordinate x.
     */
    private int x;
    /**
     * The coordinate y.
     */
    private int y;
    /**
     * The dragger boolean.
     */
    private boolean dragger;
    /**
     * The scrolled amount.
     */
    private int scrolledAmount;

    /**
     * MouseHanlder constructor.
     */
    public MouseHandler() {
        this.observers = new ArrayList<MouseObserver>();
    }

    @Override
    public final boolean registerObserver(final MouseObserver observer) throws IllegalArgumentException {
        if (observer != null) {
            this.observers.add(observer);
            return true;
        }
        return false;
    }

    @Override
    public final boolean notifyObserver(final MODE mode) throws IllegalArgumentException, IndexOutOfBoundsException {
        final Iterator<MouseObserver> it = observers.iterator();
        while (it.hasNext()) {
            final MouseObserver observer = it.next();
            switch (mode) {
            case UPDATE:
                observer.update(this.x, this.y, this.dragger, this.keyCode);
                break;
            case UPDATE_POSITION:
                observer.updatePosition(this.x, this.y);
                break;
            case UPDATE_POSITION_DRAGGER:
                observer.updatePositionDragged(this.x, this.y);
                break;
            case SCROLLED:
                observer.updateScrolled(this.scrolledAmount);
            case NONE:
                break;
            default:
                break;
            }
        }
        return true;
    }

    @Override
    public final boolean removeObserver(final MouseObserver observer)
            throws IllegalArgumentException, NullPointerException {
        if (observer != null) {
            this.observers.remove(observer);
            return true;
        }
        return false;
    }

    @Override
    public final boolean updateButtonPressed(final int x, final int y, final boolean dragger, final int keyCode)
            throws IllegalArgumentException, IndexOutOfBoundsException, NullPointerException {
        this.setKeyCode(keyCode);
        setCoordinates(x, y);
        this.setDragger(dragger);
        this.notifyObserver(MODE.UPDATE);
        return true;
    }

    @Override
    public final boolean updateMoved(final int x, final int y) {
        setCoordinates(x, y);
        this.notifyObserver(MODE.UPDATE_POSITION);
        return true;
    }

    @Override
    public final boolean updateMovedDragger(final int x, final int y) {
        setCoordinates(x, y);
        this.notifyObserver(MODE.UPDATE_POSITION_DRAGGER);
        return true;
    }

    @Override
    public final boolean updateScrolled(final int scrolledAmount) {
        this.scrolledAmount = scrolledAmount;
        this.notifyObserver(MODE.SCROLLED);
        return true;
    }

    /**
     * @param x the x coordinate
     * @param y the y coordinate
     */
    private void setCoordinates(final int x, final int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * @return the observers
     */
    public final List<MouseObserver> getObservers() {
        return observers;
    }

    /**
     * @param observers the observers to set
     */
    public final void setObservers(final List<MouseObserver> observers) {
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
     * @return the x
     */
    public final int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public final void setX(final int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public final int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public final void setY(final int y) {
        this.y = y;
    }

    /**
     * @return the dragger
     */
    public boolean isDragger() {
        return dragger;
    }

    /**
     * @param dragger the dragger to set
     */
    public void setDragger(final boolean dragger) {
        this.dragger = dragger;
    }

    /**
     * ToString method.
     */
    @Override
    public String toString() {
        return "MouseHandler [" + "observers=" + this.getObservers() + ",\n keyCode=" + this.getKeyCode() + ", x="
                + this.getY() + ", y=" + this.getY() + ", dragger=" + this.isDragger() + "]";
    }

    /**
     * HashCode method.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        final int magicNumber1 = 1231;
        final int magicNumber2 = 1237;
        result = prime * result + (dragger ? magicNumber1 : magicNumber2);
        result = prime * result + keyCode;
        result = prime * result + ((observers == null) ? 0 : observers.hashCode());
        result = prime * result + x;
        result = prime * result + y;
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
        final MouseHandler other = (MouseHandler) obj;
        if (dragger != other.dragger) {
            return false;
        }
        if (keyCode != other.keyCode) {
            return false;
        }
        if (observers == null) {
            if (other.observers != null) {
                return false;
            }
        } else if (!observers.equals(other.observers)) {
            return false;
        }
        if (x != other.x) {
            return false;
        } else if (y != other.y) {
            return false;
        }
        return true;
    }
}
