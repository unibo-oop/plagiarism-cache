package it.unibo.biscia.view.actors.UI;

import it.unibo.biscia.view.utils.Switchable;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * A {@link StateOverLabel} that can switch state respectively with the Left and
 * Right arrow keys pressed. Subclasses will have to implement
 * {@link SwitchableStateOverLabel#setPrevious()} and
 * {@link SwitchableStateOverLabel#setNext()} because the state can be
 * implemented in many different ways and should be changed Consequently.
 * 
 * @param <T> The Type of the State.
 * 
 * @see StateOverLabel
 * @see InputListener
 * @see Switchable
 * @see State
 */
public abstract class SwitchableStateOverLabel<T> extends StateOverLabel<T> implements Switchable {

    public SwitchableStateOverLabel(final T initialData, final Skin skin) {
        super(initialData, skin);
        this.addListener(new InputListener() {
            @Override
            public boolean keyDown(final InputEvent event, final int keycode) {
                if (keycode == Keys.LEFT) {
                    setPrevious();
                    return true;
                }
                if (keycode == Keys.RIGHT) {
                    setNext();
                    return true;
                }
                return super.keyDown(event, keycode);
            }
        });
    }
}
