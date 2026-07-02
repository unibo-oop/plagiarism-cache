package utility;

import java.util.HashSet;
import java.util.Set;

public class SourceImpl<T> implements Source<T> {

    private final Set<Observer<? super T>> observers = new HashSet<>();

    @Override
    public void addObserver(final Observer<? super T> observer) throws NullPointerException {
        this.observers.add(observer);
    }

    @Override
    public void addAllObservers(final Set<? extends Observer<? super T>> observers) throws NullPointerException {
        this.observers.addAll(observers);
    }

    @Override
    public void removeObserver(final Observer<? super T> observer) throws NullPointerException {
        this.observers.remove(observer);
    }

    @Override
    public void removeAllObservers(final Set<? extends Observer<? super T>> observers) throws NullPointerException {
        this.observers.removeAll(observers);
    }

    @Override
    public Set<Observer<? super T>> getObserversSet() {
        return this.observers;
    }

    @Override
    public void notifyObservers(final T arg) {
        Set.copyOf(observers).forEach(i -> i.update(this, arg));
    }
}
