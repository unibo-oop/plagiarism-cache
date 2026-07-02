package it.unibo.biscia.view.utils;

import it.unibo.biscia.core.Direction;
import it.unibo.biscia.core.Player;
import it.unibo.biscia.events.ActionObserver;
import it.unibo.biscia.events.GenericEventSubject;

import com.badlogic.gdx.InputAdapter;

/**
 * Default implementation of {@link PlayerProcessor}. it uses an
 * {@link InputAdapter} to handle user input but specific implementation of it
 * should be provided by subclasses.
 * 
 * @see PlayerProcessorImpl#keyDown(int)
 * @see PlayerOneProcessor
 * @see PlayerTwoProcessor
 *
 */
public abstract class PlayerProcessorImpl extends InputAdapter implements PlayerProcessor {
    private final Player player;
    private final GenericEventSubject<ActionObserver> actionSubject;

    public PlayerProcessorImpl(final Player player, final GenericEventSubject<ActionObserver> actionSubject) {
        this.player = player;
        this.actionSubject = actionSubject;
    }

    @Override
    public abstract boolean keyDown(int keycode);

    @Override
    public final Player getPlayer() {
        return this.player;
    }

    @Override
    public final GenericEventSubject<ActionObserver> getSubject() {
        return this.actionSubject;
    }

    @Override
    public final void moveUp() {
        actionSubject.notify(a -> a.move(player, Direction.UP));
    }

    @Override
    public final void moveDown() {
        actionSubject.notify(a -> a.move(player, Direction.DOWN));
    }

    @Override
    public final void moveLeft() {
        actionSubject.notify(a -> a.move(player, Direction.LEFT));
    }

    @Override
    public final void moveRigth() {
        actionSubject.notify(a -> a.move(player, Direction.RIGHT));
    }

}
