package it.unibo.biscia.view.actors.UI;

import it.unibo.biscia.view.utils.Actionable;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * An {@link OverLabel} with an action executed when the user press Enter or
 * Space key or Touches this {@link Label} with the mouse. If {@code action} set
 * was null it is replaced by an empty Runnable.
 * 
 * @see OverLabel
 * @see Actionable
 * @see InputListener
 *
 */
public class ActionOverLabel extends OverLabel implements Actionable {
    private Runnable action;
    private final Skin skin;

    private void runnableFIlter(final Runnable action) {
        if (action != null) {
            this.action = action;
        } else {
            setStyle(skin.get("disabled", LabelStyle.class));
            this.action = () -> {
            };
        }
    }

    /**
     * @param text   the initial label's text
     * @param skin   {@link skin} of the label
     * @param action the initial action to perform.
     */
    public ActionOverLabel(final CharSequence text, final Skin skin, final Runnable action) {
        super(text, skin);
        this.skin = skin;
        runnableFIlter(action);
        this.addListener(new InputListener() {
            @Override
            public boolean keyDown(final InputEvent event, final int keycode) {
                if (keycode == Keys.SPACE || keycode == Keys.ENTER) {
                    exec();
                }
                return super.keyDown(event, keycode);
            }

            @Override
            public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer,
                    final int button) {
                exec();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public final void exec() {
        this.action.run();
    }

    @Override
    public final Runnable getAction() {
        return action;
    }

    @Override
    public final void setAction(final Runnable action) {
        runnableFIlter(action);
    }
}
