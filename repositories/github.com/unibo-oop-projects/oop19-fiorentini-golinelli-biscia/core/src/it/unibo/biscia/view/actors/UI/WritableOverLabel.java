package it.unibo.biscia.view.actors.UI;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * A {@link StateOverLabel} that set her text based on what the user types.
 * 
 * @see InputListener.
 *
 */
public class WritableOverLabel extends StateOverLabel<String> {

    /**
     * @param initialData Initial Text of the String
     * @param maxSize     The max lenght of the text
     * @param skin        {@link skin} of the label
     */
    public WritableOverLabel(final String initialData, final int maxSize, final Skin skin) {
        super(initialData, skin);
        if (maxSize < 1 || initialData.length() > maxSize || initialData.length() < 1) {
            throw new IllegalStateException();
        }
        this.addListener(new InputListener() {
            @Override
            public boolean keyTyped(final InputEvent event, final char character) {
                if (Character.isLetter(character) && getCurrentStateValue().length() < maxSize) {
                    setState(Character.toUpperCase(character) + getCurrentStateValue());
                }
                return super.keyTyped(event, character);
            }

            @Override
            public boolean keyDown(final InputEvent event, final int keycode) {
                if (keycode == Keys.BACKSPACE) {
                    setState(getCurrentStateValue().substring(0, Math.max(getCurrentStateValue().length() - 1, 1)));
                }
                return super.keyDown(event, keycode);
            }
        });
    }

}
