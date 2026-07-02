package controller;

import java.util.ArrayList;
import java.util.List;

import view.UpdatableView;

/**
 * Models each single controller that can notify the main controller or be
 * notified by it. When the view notifies it, all of the observers are notified,
 * then one of them eventually calls the update.
 */
public abstract class AbstractSecondaryController implements SecondaryController {

    private final List<Updater> observers;
    private final UpdatableView view;

    /**
     * @param view  the view for this controller
     */
    public AbstractSecondaryController(final UpdatableView view) {
        this.observers = new ArrayList<>();
        this.view = view;
    }

    /**{@inheritDoc}**/@Override
    public void update() {
        this.view.update();
    }

    /**{@inheritDoc}**/@Override
    public void addObserver(final Updater observer) {
        this.observers.add(observer);
    }

    /**{@inheritDoc}**/@Override
    public void notifyObserver() {
        this.observers.forEach(o -> o.update());
    }

}
