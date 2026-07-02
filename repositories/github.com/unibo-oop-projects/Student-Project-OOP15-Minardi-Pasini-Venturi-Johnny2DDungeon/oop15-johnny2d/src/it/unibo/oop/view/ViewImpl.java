package it.unibo.oop.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.SwingUtilities;

import it.unibo.oop.controller.AppState;
import it.unibo.oop.utilities.Action;
import it.unibo.oop.utilities.Direction;
import it.unibo.oop.view.keyboard.ActionKey;
import it.unibo.oop.view.keyboard.ActionKeysManager;
import it.unibo.oop.view.keyboard.KeyboardObserverImpl;
import it.unibo.oop.view.keyboard.KeysManager;
import it.unibo.oop.view.keyboard.MovementKey;
import it.unibo.oop.view.keyboard.MovementKeysManager;

/**
 * Class which manages all the game views.
 */
public final class ViewImpl implements View {

    private static Optional<View> singleton = Optional.empty();
    private final LevelInterface level;
    private final MainFrame mainFrame; // class which contains all the
                                       // menu-views.
    private final KeysManager<MovementKey, Direction> movKeysMan;
    private final KeysManager<ActionKey, Action> actKeysMan;
    private List<AppState> history; // stack open-views.

    private ViewImpl() {
        this.history = new ArrayList<>();
        this.mainFrame = new MainFrameImpl();
        this.movKeysMan = new MovementKeysManager();
        this.actKeysMan = new ActionKeysManager();
        this.level = new Level(new KeyboardObserverImpl<>(MovementKey.class, this.movKeysMan),
                new KeyboardObserverImpl<>(ActionKey.class, this.actKeysMan));
    }

    /**
     * @return the singleton instance of the class.
     */
    public static synchronized View getInstance() {
        if (!singleton.isPresent()) {
            singleton = Optional.of(new ViewImpl());
        }
        return singleton.get();
    }

    @Override
    public Direction getMovement() {
        return this.movKeysMan.processKeys();
    }

    @Override
    public Action getAction() {
        return this.actKeysMan.processKeys();
    }

    @Override
    public LevelInterface getLevelView() {
        return this.level;
    }

    @Override
    public synchronized void showView(final AppState state) {
        try {
            SwingUtilities.invokeAndWait(() -> this.mainFrame.changeView(state));
            if (!this.history.contains(state)) {
                this.history.add(state);
            }
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void hideView() {
        try {
            SwingUtilities.invokeAndWait(() -> this.mainFrame.setVisible(false));
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void showLast() {
        final int lastIndex = this.history.size() - 1;
        if (lastIndex > 0) {
            this.history.remove(lastIndex); /*
                                             * rimuovo la view che ha fatto
                                             * "roll-back" per evitare loop
                                             */
            this.showView(this.history
                    .get(lastIndex - 1)); /* mostro quella che la precedeva */
        }
    }

    @Override
    public synchronized void reset() {
        this.history = new ArrayList<>(); /*
                                           * per evitare di sovraffollare
                                           * inutilmente la history
                                           */
        this.movKeysMan.reset();
        this.actKeysMan.reset();
    }
}