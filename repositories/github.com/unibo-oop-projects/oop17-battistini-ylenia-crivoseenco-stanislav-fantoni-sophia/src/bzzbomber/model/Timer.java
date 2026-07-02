package bzzbomber.model;

import java.util.ArrayList;
import java.util.List;

/**
 * the timer of the game. This forces the maximum number of seconds for each
 * level.
 *
 */
public class Timer {

    private final List<EventListener> listeners;
    private int seconds;

    /**
     * the constructor.
     * 
     * @param base
     *            the number of second when the play starts.
     */
    public Timer(final int base) {
        listeners = new ArrayList<>();
        this.seconds = base;
    }

    /**
     * decrement the number of seconds and alert that the second are changed.
     */
    public void dec() {
        this.seconds--;
        notifyEvent(new TimerEvent(this.seconds));
    }

    /**
     * get the current number of seconds.
     * 
     * @return number of seconds left.
     */
    public int getSecond() {
        return this.seconds;
    }

    /**
     * add a listener to the timer.
     * 
     * @param l
     *            the event that indicates that the number of seconds is changed.
     */
    public void addListener(final EventListener l) {
        listeners.add(l);
    }

    private void notifyEvent(final TimerEvent ev) {

        for (final EventListener l : listeners) {
            l.counterChanged(ev);
        }

    }

    /**
     * set the number of seconds when change the level or reset the game.
     * 
     * @param seconds
     *            number of initial seconds.
     */
    public void setSecond(final int seconds) {
        this.seconds = seconds;
        notifyEvent(new TimerEvent(this.seconds));
    }
}
