package view;
/**
 * Define the Observer
 * @author lucadalseno
 *
 * @param <T> type of the Observer
 */

public interface ObserverInterface<T>{
    /**
     * Adds an observer of the view
     * @param loginController
     */
    void attachObserver(T observer);
}
