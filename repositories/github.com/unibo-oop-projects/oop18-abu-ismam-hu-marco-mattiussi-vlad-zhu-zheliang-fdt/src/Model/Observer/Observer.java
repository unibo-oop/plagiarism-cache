package model.observer;
/**
 * Interface for the Observer.
 */
public interface Observer {
    /**
     * method called when the observer has to do something (update something).
     * @param subject the subject who notified the Observer
     */
    void update(ObservableEntity subject);
}
