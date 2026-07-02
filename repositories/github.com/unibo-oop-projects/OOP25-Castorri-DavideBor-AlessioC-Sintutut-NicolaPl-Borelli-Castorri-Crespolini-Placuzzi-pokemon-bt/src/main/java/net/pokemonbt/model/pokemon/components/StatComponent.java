package net.pokemonbt.model.pokemon.components;

//import net.pokemonbt.model.pokemon.*;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.PokeStatValue;
import net.pokemonbt.model.pokemon.PokeEvasionValue;
import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.StatMod;
import net.pokemonbt.utility.Clone;

import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * {@link Pokemon} component that handles condition
 * behaviour for applying, removing and executing a
 * condition's effect.
 */
public final class StatComponent extends AbstractPokeComponent implements Clone<StatComponent> {
    @Serial
    private static final long serialVersionUID = 1L;

    private int hp;
    private final Map<PokeStatType, PokeStatValue> stats;

    private final PokeType primaryType;
    private final PokeType secondaryType;

    /**
     * Don't create this component by itself, only
     * use it inside {@link Pokemon} constructor.
     *
     * @param baseStats The base statistics of the Pokemon, without modifiers.
     * @param type1 The first type of the pokemon.
     * @param type2 The second type of the pokemon. Could be {@link PokeType}{@code .None}
     */
    public StatComponent(
            final Map<PokeStatType, Integer> baseStats,
            final PokeType type1,
            final PokeType type2
    ) {
        Objects.requireNonNull(baseStats);
        Objects.requireNonNull(type1);
        Objects.requireNonNull(type2);

        this.stats = new LinkedHashMap<>();
        for (final var entry : baseStats.entrySet()) {
            if (entry.getKey() == PokeStatType.ACC || entry.getKey() == PokeStatType.EVA) {
                this.stats.put(entry.getKey(), new PokeEvasionValue(entry.getValue()));
            } else {
                this.stats.put(entry.getKey(), new PokeStatValue(entry.getValue()));
            }
        }
        this.hp = this.stats.get(PokeStatType.HP_MAX).getValue();

        this.primaryType = type1;
        this.secondaryType = type2;
    }

    /* - For Battle - */

    /**
     * Removes the specified amount of health from this {@link Pokemon}.
     *
     * @param amount The amount of health to reduce.
     */
    public void removeHealth(final int amount) {
        /*If the specified amount is less than 0, it won't do anything.
        * Therefore, exit early.
        */
        if (amount <= 0) {
            return;
        }
        this.hp = Math.max(this.hp - amount, 0);
    }

    /**
     * Adds the specified amount of health to this {@link Pokemon}.
     *
     * @param amount The amount of health to increase.
     */
    public void increaseHealth(final int amount) {
        /*If the specified amount is less than 0, it won't do anything.
         * Therefore, exit early.
         */
        if (amount <= 0) {
            return;
        }
        final int maxAmount = this.getStat(PokeStatType.HP_MAX);
        /* Limit the hp amount to its current max value. */
        this.hp = Math.min(this.hp + amount, maxAmount);
    }

    /* - For Types - */

    /**
     * Gets the pokemon's primary type.
     *
     * @return The type.
     */
    public PokeType getPrimaryType() {
        return this.primaryType;
    }

    /**
     * Gets the pokemon's secondary type.
     *
     * @return The type.
     */
    public PokeType getSecondaryType() {
        return this.secondaryType;
    }

    /**
     * Checks if the pokemon has a certain specific type.
     *
     * @param type The type to check for.
     * @return The outcome.
     */
    public boolean hasType(final PokeType type) {
        Objects.requireNonNull(type);
        return this.primaryType == type || this.secondaryType == type;
    }

    /* - For Stat Modifiers - */

    /**
     * Tries to add a new Stat Modifier to the internal map. Returns the outcome.
     *
     * @param statType The {@link PokeStatType} to add the modifier to.
     * @param modID The ID of the new modifier to add.
     * @param modifier The {@link StatMod} to add, and to map to the passed ID.
     * @return The outcome of the operation: false if it could not be added; true otherwise.
     */
    public boolean addStatMod(final PokeStatType statType, final String modID, final StatMod modifier) {
        Objects.requireNonNull(statType);
        Objects.requireNonNull(modID);
        Objects.requireNonNull(modifier);
        if (modID.isBlank()) {
            return false;
        }
        if (this.stats.get(statType).containsMod(modID)) {
            return false;
        }
        this.stats.get(statType).addStatMod(modID, modifier);
        return true;
    }

    /**
     * Tries to update a specific modifier mapped to a certain ID and
     * overrides it with the passed newModifier.
     *
     * @param statType The {@link PokeStatType} to update the modifier from.
     * @param modID The ID of the already added modifier.
     * @param newModifier The new modifier to map to the passed ID instead of the previous one.
     * @return The outcome of the operation: false if it could not be overridden; true otherwise.
     */
    public boolean updateStatMod(final PokeStatType statType, final String modID, final StatMod newModifier) {
        Objects.requireNonNull(statType);
        Objects.requireNonNull(modID);
        Objects.requireNonNull(newModifier);
        if (modID.isBlank()) {
            return false;
        }
        if (!this.stats.get(statType).containsMod(modID)) {
            return false;
        }
        this.stats.get(statType).addStatMod(modID, newModifier);
        return true;
    }

    /**
     * Tries to update a specific modifier mapped to a certain ID and
     * sums or subtracts its current stage.
     *
     * @param statType The {@link PokeStatType} to update the modifier from.
     * @param modID The ID of the already added modifier.
     * @param stage The amount of stages to modify it by. Sums if positive,
     *              subtracts if negative.
     * @return The outcome of the operation: false if it could not be calculated; true otherwise.
     */
    public boolean updateStatModStage(final PokeStatType statType, final String modID, final int stage) {
        Objects.requireNonNull(statType);
        Objects.requireNonNull(modID);
        if (modID.isBlank()) {
            return false;
        }
        if (!this.stats.get(statType).containsMod(modID)) {
            return false;
        }
        this.stats.get(statType).updateStatModStage(modID, stage);
        return true;
    }

    /**
     * Tries to remove a modifier by searching it by ID.
     *
     * @param statType The {@link PokeStatType} to search in.
     * @param modID The ID to search for.
     * @return The outcome of the operation: false if the
     *         modifier could not be removed, true otherwise.
     */
    public boolean removeStatMod(final PokeStatType statType, final String modID) {
        Objects.requireNonNull(statType);
        Objects.requireNonNull(modID);
        if (modID.isBlank()) {
            return false;
        }
        if (!this.stats.get(statType).containsMod(modID)) {
            return false;
        }
        this.stats.get(statType).removeStatMod(modID);
        return true;
    }

    /**
     * Checks if the given {@link PokeStatType} has an associated modifier with the given ID.
     *
     * @param statType The {@link PokeStatType} to search in.
     * @param modID The ID to search for.
     * @return If the modifier is present or not.
     */
    public boolean hasMod(final PokeStatType statType, final String modID) {
        Objects.requireNonNull(modID);
        return !modID.isBlank() && this.stats.get(statType).containsMod(modID);
    }

    /**
     * Tries to get the modifier associated with the given {@link PokeStatType}
     * and with the given ID.
     *
     * @param statType The {@link PokeStatType} to search in.
     * @param modID The ID to search for.
     * @return The {@link StatMod} associated with the ID applied to the given {@link PokeStatType}.
     */
    public Optional<StatMod> getStatMod(final PokeStatType statType, final String modID) {
        return this.hasMod(statType, modID)
                ? Optional.of(this.stats.get(statType).getStatMod(modID))
                : Optional.empty();
    }

    /* - For Stats - */

    /**
     * Gets the pokemon's base stat, without any modifiers applied.
     *
     * @param stat The stat to get.
     * @return The value associated with the given stat.
     */
    public int getBaseStat(final PokeStatType stat) {
        Objects.requireNonNull(stat);
        return this.stats.get(stat).getBaseValue();
    }

    /**
     * Gets the current pokemon's stat, after calculating every modifier.
     * These stats are actually pre-calculated, and the stat requested from
     * this method is gotten from the pre-calculated internal map, making
     * it as computationally heavy as getting the base stat.
     *
     * @param stat The stat to get.
     * @return The value associated with the given stat.
     */
    public int getStat(final PokeStatType stat) {
        Objects.requireNonNull(stat);
        return this.stats.get(stat).getValue();
    }

    /**
     * @return The amount of health points the {@link Pokemon} currently has.
     */
    public int getHP() {
        return this.hp;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public StatComponent copyOf() {
        final Map<PokeStatType, Integer> baseStats = new LinkedHashMap<>();
        for (final var stat : this.stats.entrySet()) {
            baseStats.put(stat.getKey(), stat.getValue().getBaseValue());
        }
        return new StatComponent(
                baseStats,
                this.primaryType,
                this.secondaryType
        );
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
        final StatComponent that = (StatComponent) o;
        return this.hp == that.hp
                && Objects.equals(this.stats, that.stats)
                && this.primaryType == that.primaryType
                && this.secondaryType == that.secondaryType;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                this.hp,
                this.stats,
                this.primaryType,
                this.secondaryType);
    }
}
