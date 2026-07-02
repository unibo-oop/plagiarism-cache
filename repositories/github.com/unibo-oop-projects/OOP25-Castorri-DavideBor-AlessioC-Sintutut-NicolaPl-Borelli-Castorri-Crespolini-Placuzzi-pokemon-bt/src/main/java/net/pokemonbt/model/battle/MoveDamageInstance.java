package net.pokemonbt.model.battle;

import net.pokemonbt.controller.battle.DamageUtils;
import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.move.MoveCategory;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;

import java.util.Objects;

/**
 * {@link DamageInstance} representing a default damage instance created by
 * a {@link Move} without any special behaviour and with the standard
 * damage calculation implemented.
 */
public class MoveDamageInstance implements DamageInstance {
    private static final float BASE_MULTIPLIER = 2.2f;
    private static final int BASE_FLAT = 2;
    private static final float STAB_MULTIPLIER = 1.5f;
    private static final float CRIT_MULTIPLIER = 1.5f;

    private final boolean isCrit;

    private int power;
    private int atk;
    private int def;
    private int finalDamage;

    private final float multiplier;

    private boolean isBlocked;

    private boolean isDirty;

    /**
     * @param user The {@link Pokemon} that caused the damage.
     * @param move The {@link Move} that caused the damage.
     * @param opponent The {@link Pokemon} that received the damage.
     * @param critThreshold A number between 0 and 1 determining the critical chance.
     */
    public MoveDamageInstance(
            final Pokemon user,
            final Move move,
            final Pokemon opponent,
            final float critThreshold
    ) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(move);
        Objects.requireNonNull(opponent);

        this.isCrit = RandomUtility.check(critThreshold);

        this.multiplier =
                /* If the move is the same type as one of the pokemon's types */
                (user.getStatComponent().hasType(move.getType())
                        ? STAB_MULTIPLIER
                        : 1f)
                /* Type-chart multiplier */
                * DamageUtils.getTypeMultiplier(move.getType(),
                        opponent.getStatComponent().getPrimaryType(),
                        opponent.getStatComponent().getSecondaryType())
                /* Critical damage */
                * (this.isCrit ? CRIT_MULTIPLIER : 1f);

        this.power = move.getPower();
        this.atk = move.getCategory() == MoveCategory.PHYSICAL
                ? user.getStatComponent().getStat(PokeStatType.ATK)
                : user.getStatComponent().getStat(PokeStatType.SPA);
        this.def = move.getCategory() == MoveCategory.PHYSICAL
                ? opponent.getStatComponent().getStat(PokeStatType.DEF)
                : opponent.getStatComponent().getStat(PokeStatType.SPD);
    }

    /**
     * @param newPower The new move power to assign to the DamageInstance.
     */
    public void overridePower(final int newPower) {
        this.power = newPower;
        this.isDirty = true;
    }

    /**
     * @param newAtk The new attack to assign to the DamageInstance.
     */
    public void overrideAttack(final int newAtk) {
        this.atk = newAtk;
        this.isDirty = true;
    }

    /**
     * @param newDef The new defence to assign to the DamageInstance.
     */
    public void overrideDefence(final int newDef) {
        this.def = newDef;
        this.isDirty = true;
    }

    /**
     * @param state If the damage is now blocked or not.
     */
    public void overrideBlocked(final boolean state) {
        this.isBlocked = state;
    }

    /**
     * Calculates the damage via the standard formula.
     */
    private void calculateDamage() {
        final float baseDmg;
        if (this.power > 0) {
            baseDmg = this.power * this.atk / (this.def * BASE_MULTIPLIER) + BASE_FLAT;
            this.finalDamage = (int) Math.clamp(baseDmg * this.multiplier, 0, Integer.MAX_VALUE);
        } else {
            this.finalDamage = 0;
        }
        this.isDirty = false;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public int getDamage() {
        if (this.isDirty) {
            this.calculateDamage();
        }
        return this.finalDamage;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFake() {
        return this.power == 0;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean isBlocked() {
        return this.isBlocked;
    }

    /**
     * @return  The {@link DamageTypeMultiplier} calculated and used for
     *          damage on this specific {@link DamageInstance}.
     */
    @Override
    public DamageTypeMultiplier getTypeMultiplier() {
        return DamageTypeMultiplier.floatToTypeMult(this.multiplier);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean isCritical() {
        return this.isCrit;
    }
}
