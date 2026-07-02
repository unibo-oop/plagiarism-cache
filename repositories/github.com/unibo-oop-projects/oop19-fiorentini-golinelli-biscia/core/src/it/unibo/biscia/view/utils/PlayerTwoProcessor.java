package it.unibo.biscia.view.utils;

import it.unibo.biscia.core.Direction;
import it.unibo.biscia.core.Player;
import it.unibo.biscia.events.ActionObserver;
import it.unibo.biscia.events.GenericEventSubject;

import com.badlogic.gdx.Input.Keys;

/**
 * Specialized class of {@link PlayerProcessorImpl} for {@link Player} "Two"
 * keyboard handle. This player fulfill its moves by keyboard's wasd keys.
 * 
 * @see Player
 * @see PlayerProcessor
 */
public class PlayerTwoProcessor extends PlayerProcessorImpl {

    public PlayerTwoProcessor(final Player player, final GenericEventSubject<ActionObserver> actionSubject) {
        super(player, actionSubject);
    }

    @Override
    public final boolean keyDown(final int keycode) {
        switch (keycode) {
        case Keys.W:
            getSubject().notify(a -> a.move(getPlayer(), Direction.UP));
            break;
        case Keys.S:
            getSubject().notify(a -> a.move(getPlayer(), Direction.DOWN));
            break;
        case Keys.A:
            getSubject().notify(a -> a.move(getPlayer(), Direction.LEFT));
            break;
        case Keys.D:
            getSubject().notify(a -> a.move(getPlayer(), Direction.RIGHT));
            break;
        default:
            break;
        }
        return false;
    }

}
