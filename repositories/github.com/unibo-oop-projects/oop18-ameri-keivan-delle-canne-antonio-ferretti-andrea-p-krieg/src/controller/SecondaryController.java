package controller;

/**
 * When notified from it's attached view it notifies the attached observers.
 */
public interface SecondaryController extends Updater {

    /**
     * used to attach the observer.
     * 
     * @param observer the observer to add
     */
    void addObserver(Updater observer);

    /**
     * notifies the observer.
     */
    void notifyObserver();
}
