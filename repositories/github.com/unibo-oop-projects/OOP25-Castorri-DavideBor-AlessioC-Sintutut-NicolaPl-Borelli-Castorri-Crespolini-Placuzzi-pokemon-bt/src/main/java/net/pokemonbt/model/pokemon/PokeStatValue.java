package net.pokemonbt.model.pokemon;

import net.pokemonbt.utility.Clone;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Helper class for representing both the base stat value of a {@link Pokemon}
 * and its modifiers. Contains multiple helper value but should not be
 * handled directly.
 */
public class PokeStatValue implements Serializable, Clone<PokeStatValue> {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final int MIN_STAGE_LEVEL = -6;
    private static final int MAX_STAGE_LEVEL = 6;

    private static final int DEFAULT_FRACTION = 2;

    private final int baseValue;
    private int currentValue;
    private final Map<String, StatMod> modifiers;
    private boolean isDirty;

    /**
     * @param baseValue The base value the {@link Pokemon} has at the start of the battle.
     */
    public PokeStatValue(
            final int baseValue
    ) {
        this.baseValue = baseValue;
        this.currentValue = baseValue;
        this.modifiers = new LinkedHashMap<>();
    }

    /**
     * @return The value of the fraction used to calculate the stat's value.
     */
    protected int getFraction() {
        return DEFAULT_FRACTION;
    }

    /**
     * @return The base value of this stat.
     */
    public int getBaseValue() {
        return this.baseValue;
    }

    /**
     * @return The current value of this stat.
     */
    public int getValue() {
        if (this.isDirty) {
            this.preCalculateValue();
        }
        return this.currentValue;
    }

    /**
     * Adds a stat modifier under the given ID.
     *
     * @param modID The modifier's ID.
     * @param modifier The {@link StatMod} over the current stat.
     */
    public void addStatMod(final String modID, final StatMod modifier) {
        this.modifiers.put(modID, modifier);
        this.isDirty = true;
    }

    /**
     * Updates the stage of a currently applied modifier.
     *
     * @param modID The modifier's ID.
     * @param stage The amount of stages to increase or lower,
     *              depending on the value's sign.
     */
    public void updateStatModStage(final String modID, final int stage) {
        if (this.containsMod(modID)) {
            this.modifiers.computeIfPresent(
                    modID,
                    (k, oldMod) -> new StatMod(
                            Math.clamp(
                                    oldMod.stageLevel() + stage,
                                    MIN_STAGE_LEVEL, MAX_STAGE_LEVEL
                            ),
                            oldMod.canBeTransferred(),
                            oldMod.canBeRemoved()
            ));
            this.isDirty = true;
        }
    }

    /**
     * @param modID The modifier's ID to check.
     * @return If the stat currently has a modifier on it with the same ID as the given one.
     */
    public boolean containsMod(final String modID) {
        return this.modifiers.containsKey(modID);
    }

    /**
     * Removes a stat modifier under the given ID.
     *
     * @param modID The modifier's ID.
     */
    public void removeStatMod(final String modID) {
        this.modifiers.remove(modID);
        this.isDirty = true;
    }

    /**
     * Gets the {@link StatMod} associated with this stat under the given ID.
     *
     * @param modID The modifier's ID.
     * @return The associated {@link StatMod}.
     */
    public StatMod getStatMod(final String modID) {
        return this.modifiers.get(modID);
    }

    /**
     * Pre-calculates the value of the current stat according to its base value
     * and the modifiers currently associated to it.
     */
    private void preCalculateValue() {
        this.currentValue = this.baseValue;
        final int stage = Math.clamp(
                this.modifiers.values().stream()
                .map(StatMod::stageLevel)
                .reduce(0, Integer::sum),
                MIN_STAGE_LEVEL, MAX_STAGE_LEVEL
        );
        if (stage > 0) {
            this.currentValue = (int) Math.floor(
                    this.baseValue * (float) (this.getFraction() + stage) / this.getFraction());
        } else if (stage < 0) {
            this.currentValue = (int) Math.floor(
                    this.baseValue * (float) this.getFraction() / (this.getFraction() - stage));
        }

        this.isDirty = false;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Base: ".concat(String.valueOf(this.baseValue))
                .concat(" | Curr: ").concat(String.valueOf(this.currentValue));
    }

    /**
     * @return A new instance with the same values.
     */
    @Override
    public PokeStatValue copyOf() {
        final PokeStatValue clone = new PokeStatValue(this.baseValue);
        clone.modifiers.putAll(this.modifiers);
        return clone;
    }

    /**
     * {@inheritDoc}
     *
     * @param o {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PokeStatValue that = (PokeStatValue) o;
        return this.baseValue == that.baseValue
                && Objects.equals(this.modifiers, that.modifiers);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                this.baseValue,
                this.modifiers);
    }
}
