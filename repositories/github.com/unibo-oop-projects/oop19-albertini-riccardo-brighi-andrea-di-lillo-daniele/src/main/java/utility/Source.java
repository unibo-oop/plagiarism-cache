package utility;

import java.util.Set;

public interface Source<T> {

    /**
     * @param observer the observer to add
     * @throws NullPointerException if @param observer is null
     *                              <p>
     *                              add the observer
     *                              </p>
     */

    void addObserver(Observer<? super T> observer);

    /**
     * @param observers the observers to add
     * @throws NullPointerException if @param observers is null
     *                              <p>
     *                              add all the observers
     *                              </p>
     */

    void addAllObservers(Set<? extends Observer<? super T>> observers);

    /**
     * @param observer the observer to remove
     * @throws NullPointerException if @param observer is null
     *                              <p>
     *                              remove the observer
     *                              </p>
     */

    void removeObserver(Observer<? super T> observer);

    /**
     * @param observers the observers to remove
     * @throws NullPointerException if @param observer is null
     *                              <p>
     *                              remove all the observers
     *                              </p>
     */

    void removeAllObservers(Set<? extends Observer<? super T>> observers);

    /**
     * @return all the Observer of the source
     */

    Set<Observer<? super T>> getObserversSet();


    /**
     * @param arg the change to notify
     *            <p>
     *            notify the change to all the observer
     *            </p>
     */

    void notifyObservers(T arg);
}
