package frogger.model.interfaces;

/**
 * Gives to an object the ability to start its action only after the trigger has been fired.
 * @param <X> the type of trigger that will start the action.
 */
public interface Startable<X> {
    /**
     * set the object in started mode.
     */
    void start();

    /**
     * stop the object from his action.
     */
    void stop();

    /**
     * set the condition to start the action.
     * @param trigger the value to memorize to trigger the action.
     */
    void setTrigger(X trigger);

    /**
     * get the value of the trigger.
     * @return the trigger value.
     */
    X getTrigger();
}
