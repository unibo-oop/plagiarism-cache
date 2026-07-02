package it.unibo.biscia.view.actors.UI;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Implementation of {@link SwitchableStateOverLabel} for an Enum State.
 *
 * @param <T> the Enum's type.
 */
public class EnumerableOverLabel<T extends Enum<T>> extends SwitchableStateOverLabel<T> {
    private List<T> values;
    private int iterator;

    /**
     * @param enumClass   {@link Enum}'s class
     * @param initialData Label's initial text
     * @param skin        {@link Skin}'s label style.
     */
    public EnumerableOverLabel(final Class<T> enumClass, final T initialData, final Skin skin) {
        super(initialData, skin);
        this.values = Arrays.asList(enumClass.getEnumConstants());
        this.iterator = values.indexOf(getCurrentStateValue());
    }

    @Override
    public final void setPrevious() {
        if (iterator > 0) {
            setState(values.get(--iterator));
        }
    }

    @Override
    public final void setNext() {
        if (iterator < values.size() - 1) {
            setState(values.get(++iterator));
        }
    }
}
