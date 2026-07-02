package it.unibo.biscia.view.actors.UI;

import it.unibo.biscia.view.utils.Listenable;
import it.unibo.biscia.view.utils.Listener;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ListenableIntOverLabel extends IntOverLabel implements Listenable {

    private final Set<Listener> listeners;

    public ListenableIntOverLabel(final int minValue, final int maxValue, final Integer initialData, final Skin skin) {
        super(minValue, maxValue, initialData, skin);
        this.listeners = new HashSet<>();
    }

    @Override
    public final void setPrevious() {
        super.setPrevious();
        this.update();
    }

    @Override
    public final void setNext() {
        super.setNext();
        this.update();
    }

    @Override
    public final void addListener(final Listener listener) {
        this.listeners.add(listener);
    }

    @Override
    public final void removeListener(final Listener listener) {
        this.listeners.add(listener);
    }

    @Override
    public final void update() {
        listeners.stream().forEach(Listener::stateChanged);
    }

}
