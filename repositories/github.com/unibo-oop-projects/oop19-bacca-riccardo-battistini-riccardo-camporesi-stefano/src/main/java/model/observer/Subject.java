package model.observer;

public interface Subject {

    /**
     * To register new observer to the specified Subject.
     * 
     * @param obj the new observer to be registered
     */
    void register(Observer obj);

    /**
     * To unregister an observer from this Subject.
     * 
     * @param obj the observer to be removed
     */
    void unregister(Observer obj);

    /**
     * To notify observers for a change has occurred.
     */
    void notifyObservers();

}
