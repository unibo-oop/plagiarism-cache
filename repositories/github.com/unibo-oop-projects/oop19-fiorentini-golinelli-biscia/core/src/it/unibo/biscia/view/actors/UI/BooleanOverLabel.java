package it.unibo.biscia.view.actors.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Implementation of {@link SwitchableStateOverLabel} for Boolean State.
 *
 */
public class BooleanOverLabel extends SwitchableStateOverLabel<Boolean> {

    /**
     * @param initialData inital label's text
     * @param skin        {@link Skin}'s label style
     */
    public BooleanOverLabel(final Boolean initialData, final Skin skin) {
        super(initialData, skin);
    }

    @Override
    public final void setPrevious() {
        this.setNext();
    }

    @Override
    public final void setNext() {
        this.setState(!this.getCurrentStateValue());
    }

}
