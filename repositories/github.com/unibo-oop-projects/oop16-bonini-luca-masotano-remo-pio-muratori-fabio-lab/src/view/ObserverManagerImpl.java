package view;

import java.util.ArrayList;
import java.util.List;

import controller.Observer;
import controller.event.Event;

/**
 * Implementation of ObserverManager.
 */
public class ObserverManagerImpl implements ObserverManager {

    private final List<Observer> observerList = new ArrayList<>();

    @Override
    public void notifyAll(final Event event) {
        observerList.stream().forEach(e -> {
            e.onNotify(event);
        });
    }

    @Override
    public void addObserver(final Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(final Observer obs) {
        if (this.observerList.contains(obs)) {
            this.observerList.remove(obs);
        }
    }
}
