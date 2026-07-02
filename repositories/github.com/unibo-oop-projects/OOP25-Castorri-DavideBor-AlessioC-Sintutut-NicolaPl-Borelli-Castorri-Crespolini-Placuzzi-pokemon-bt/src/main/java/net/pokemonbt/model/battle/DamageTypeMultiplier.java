package net.pokemonbt.model.battle;

import java.util.Arrays;

/**
 * Enum defining all possible damage multipliers based on type-on-type clashes.
 */
public enum DamageTypeMultiplier {
    SUPER_EFFECTIVE(4f, 2),
    EFFECTIVE(2f, 1),
    NORMAL(1.0f, 0),
    WEAK(0.5f, -1),
    SUPER_WEAK(0.25f, -2),
    NULL(0f, -3);

    private final float multiplier;
    private final int value;

    /**
     * @param multiplier The actual multiplier.
     * @param value A number representing the multiplier.
     */
    DamageTypeMultiplier(final float multiplier, final int value) {
        this.multiplier = multiplier;
        this.value = value;
    }

    /**
     * @return A number representing the multiplier.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @return The multiplier's associated value.
     */
    public float getMultiplier() {
        return this.multiplier;
    }

    /**
     * @param value The number representing the multiplier.
     * @return The multiplier's associated value.
     */
    public static float getMultiplier(final int value) {
        final int clampedValue = Math.clamp(value, NULL.getValue(), SUPER_EFFECTIVE.getValue());
        return Arrays.stream(values())
                .filter(x -> x.value == clampedValue).findFirst()
                .map(DamageTypeMultiplier::getMultiplier)
                .orElse(NORMAL.getMultiplier());
    }

    /**
     * @param value The number representing the multiplier.
     * @return The associated {@link DamageTypeMultiplier} with the same value.
     */
    public static DamageTypeMultiplier intToTypeMult(final int value) {
        final int clampedValue = Math.clamp(value, NULL.getValue(), SUPER_EFFECTIVE.getValue());
        return Arrays.stream(values())
                .filter(x -> x.value == clampedValue).findFirst()
                .orElse(NORMAL);
    }

    /**
     * @param multiplier The multiplier's value.
     * @return The associated {@link DamageTypeMultiplier} with the same multiplier.
     */
    public static DamageTypeMultiplier floatToTypeMult(final float multiplier) {
        return Arrays.stream(values())
                .filter(x -> Float.compare(x.multiplier, multiplier) == 0).findFirst()
                .orElse(NORMAL);
    }
}
