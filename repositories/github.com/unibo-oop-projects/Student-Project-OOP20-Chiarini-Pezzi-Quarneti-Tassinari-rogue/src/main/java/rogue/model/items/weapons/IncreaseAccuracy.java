package rogue.model.items.weapons;

/**
 * A decorator to increase the weapon accuracy.
 */
public final class IncreaseAccuracy extends WeaponDecorator {

    private static final int ADDITIONAL_ACCURACY = 2;

    public IncreaseAccuracy(final Weapon weapon) {
        super(weapon);
    }

    @Override
    public int getAccuracy() {
        return super.getAccuracy() + ADDITIONAL_ACCURACY;
    }

    @Override
    public String toString() {
        return super.toString() + " increased accuracy";
    }

}
