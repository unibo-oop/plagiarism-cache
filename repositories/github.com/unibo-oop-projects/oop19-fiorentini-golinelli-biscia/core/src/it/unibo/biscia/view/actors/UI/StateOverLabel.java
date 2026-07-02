package it.unibo.biscia.view.actors.UI;

import it.unibo.biscia.view.utils.State;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * An {@link OverLabel} with a {@link State}.
 *
 * @see OverLabel
 * @see State
 *
 * @param <T> The Type of the State.
 */
public class StateOverLabel<T> extends OverLabel implements State<T> {
    private T currentStateValue;

    /**
     * Creates a Label with {@code initialData.toStirng()} as initial text.
     * 
     * @param initialData Initial data of the string
     * @param skin        {@link skin} of the label
     */
    public StateOverLabel(final T initialData, final Skin skin) {
        super(initialData.toString(), skin);
        this.setText(initialData.toString());
        this.currentStateValue = initialData;
    }

    @Override
    public final void setState(final T newStateValue) {
        this.currentStateValue = newStateValue;
        this.setText(newStateValue.toString());

    }

    @Override
    public final T getCurrentStateValue() {
        return this.currentStateValue;
    }

    @Override
    public final Class<?> getType() {
        return currentStateValue.getClass();
    }

}
