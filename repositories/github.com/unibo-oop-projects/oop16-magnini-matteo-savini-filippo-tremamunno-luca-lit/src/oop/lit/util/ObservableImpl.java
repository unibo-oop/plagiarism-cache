package oop.lit.util;

import java.util.HashSet;
import java.util.Set;

/**
 * An implementation of an observable object.
 */
public class ObservableImpl implements Observable {
    private final Set<Observer> observers = new HashSet<>();

    /**
     * 
     */
    protected ObservableImpl() {
        //Non voglio instanziarlo di per se, e non ha metodi abstract.
    }

    @Override
    public final void attach(final Observer o) {
        observers.add(o);
    }

    @Override
    public final void detach(final Observer o) {
        observers.remove(o);
    }

    /**
     * notifies all attached observers.
     */
    protected final void notifyObservers() {
        // non posso lavorare con il foreach direttamente su il set, altrimenti
        // se un elemento vuole fare il detach durante la notify change ho
        // errore
        new HashSet<>(observers).forEach(Observer::notifyChange);
    }
}
