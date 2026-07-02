package controller.time;

/**
 * Interface that represent event of time changed.
 *
 */
public interface TimeEventListener {

    /**
     * Notify event of time changed.
     * @param t time.
     */
    void notifyTimeChange(Time t);
}
