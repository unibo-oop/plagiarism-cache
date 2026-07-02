package it.unibo.biscia.view.actors.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Implementation of {@link SwitchableStateOverLabel} for Integer State.
 *
 */
public class IntOverLabel extends SwitchableStateOverLabel<Integer> {
    private final int minValue;
    private final int maxValue;

    /**
     * @param minValue    State's minimum value
     * @param maxValue    State's maximum value
     * @param initialData initial label's text
     * @param skin        {@link Skin}'s label style.
     */
    public IntOverLabel(final int minValue, final int maxValue, final Integer initialData, final Skin skin) {
        super(initialData, skin);
        if (minValue > maxValue || initialData < minValue || initialData > maxValue) {
            throw new IllegalStateException();
        }
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public void setPrevious() {
        if (getCurrentStateValue() > minValue) {
            setState(getCurrentStateValue() - 1);
        }
    }

    @Override
    public void setNext() {
        if (getCurrentStateValue() < maxValue) {
            setState(getCurrentStateValue() + 1);
        }
    }

}
