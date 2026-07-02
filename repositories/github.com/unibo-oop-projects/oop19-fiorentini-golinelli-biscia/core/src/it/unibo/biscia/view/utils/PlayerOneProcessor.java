package it.unibo.biscia.view.utils;

import it.unibo.biscia.core.Direction;
import it.unibo.biscia.core.Player;
import it.unibo.biscia.events.ActionObserver;
import it.unibo.biscia.events.GenericEventSubject;

import com.badlogic.gdx.Input.Keys;

/**
 * Specialized class of {@link PlayerProcessorImpl} for {@link Player} "One"
 * keyboard handle. This player fulfill its moves by ketboard's arrow keys.
 * 
 * @see Player
 * @see PlayerProcessor
 */
public class PlayerOneProcessor extends PlayerProcessorImpl {

    public PlayerOneProcessor(final Player player, final GenericEventSubject<ActionObserver> actionSubject) {
        super(player, actionSubject);
    }

    @Override
    public final boolean keyDown(final int keycode) {
        switch (keycode) {
        case Keys.UP:
            getSubject().notify(a -> a.move(getPlayer(), Direction.UP));
            break;
        case Keys.DOWN:
            getSubject().notify(a -> a.move(getPlayer(), Direction.DOWN));
            break;
        case Keys.LEFT:
            getSubject().notify(a -> a.move(getPlayer(), Direction.LEFT));
            break;
        case Keys.RIGHT:
            getSubject().notify(a -> a.move(getPlayer(), Direction.RIGHT));
            break;
        default:
            break;
        }
        return false;
    }
}
