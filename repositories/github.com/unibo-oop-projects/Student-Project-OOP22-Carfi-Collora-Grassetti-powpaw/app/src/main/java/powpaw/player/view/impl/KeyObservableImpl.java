package powpaw.player.view.impl;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import powpaw.player.view.api.KeyObservable;
import powpaw.player.view.api.KeyObserver;

/**
 * The {@code KeyObservableImpl} class is an implementation of the
 * {@code KeyObservable} interface. It provides a mechanism for registering
 * KeyObserver objects and notifying them when keys are pressed or released.
 * 
 * @author Alessia Carfì
 */
public final class KeyObservableImpl implements KeyObservable {

    private final List<KeyObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(final KeyObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(final KeyObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserversPressed(final KeyCode event) {
        observers.forEach(obs -> obs.keyPressed(event));
    }

    @Override
    public void notifyObserversReleased(final KeyCode event) {
        observers.forEach(obs -> obs.keyReleased(event));
    }
}
