package net.pokemonbt.model.move.components;

import java.io.Serial;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.gson.JsonObject;

import net.pokemonbt.model.battle.DamageInstance;
import net.pokemonbt.model.battle.MoveDamageInstance;
import net.pokemonbt.model.move.Move;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.RandomUtility;

/**
 * A move with {@link Damage} Behaviour interacts with the opponent creating at least one {@link DamageInstance}.
 */
public final class Damage extends AbstractBehaviourDecorator {

    public static final int CRIT_STAGE_POWER = 4;
    public static final int CRIT_BASE = 2;

    @Serial
    private static final long serialVersionUID = 1L;

    private final Float accuracy;
    private final int power;
    private final int critStage;

    private transient MoveDamageInstance dmg;

    private transient Function<Integer, Integer> powerModifier;
    private transient Supplier<Optional<Integer>> attackerStatModifier;

    /**
     * @param base The Behaviour to Decorate.
     * @param values An {@link Optional} of a {@link JsonObject} containing the values
     *      that need to be set up.
     */
    public Damage(final MoveBehaviour base, final Optional<JsonObject> values) {
        super(base);

        if (values.isEmpty()) {
            throw new IllegalArgumentException("values cannot be an empty Optional.");
        }

        final var v = values.get();
        this.power = v.get("power").getAsInt();
        this.accuracy = v.get("accuracy").getAsFloat();

        this.critStage = v.has("critStage") 
            ? v.get("critStage").getAsInt() 
            : 0;

        this.resetState();
    }

    /**
     * Create a new Instance using a base and a Decorator to copy.
     * 
     * @param base The Behaviour to Decorate.
     * @param toCopy An old instance of {@link Damage} to take the values from.
     */
    public Damage(final MoveBehaviour base, final Damage toCopy) {
        super(base);
        this.accuracy = toCopy.accuracy;
        this.power = toCopy.power;
        this.critStage = toCopy.critStage;
        this.resetState();
    }

    /**
     * Private method to find out if the {@link Move} can hit the target.
     * 
     * @param user Reference needed to consider the {@link Pokemon}'s' 'accuracy'
     *      before adding the {@link Move}'s 'accuracy'.
     * @param opponent Reference needed to balance 'accuracy' with 'evasion'.
     * @return 'true' if the damage reaches the opponent. 'false' Otherwise.
     */
    private boolean isHit(final Pokemon user, final Pokemon opponent) {

        final Float finalAcc = user.getStatComponent()
            .getStat(PokeStatType.ACC) / 100.0f;
        final Float finalEva = opponent.getStatComponent()
            .getStat(PokeStatType.EVA) / 100.0f;

        final Float threshold = this.accuracy * finalAcc * (1 / finalEva);

        return RandomUtility.check(Float.min(threshold, 1.0f));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DamageInstance getLastDamageAmount() {
        return this.dmg;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLastDamageAmount(final MoveDamageInstance amount) {
        this.dmg = amount;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public void modifyDamagePower(
        final Function<Integer, Integer> functionModifier
    ) {
        this.powerModifier = functionModifier;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public void modifyDamageStat(
        final Supplier<Optional<Integer>> attackerStatSupplier
    ) {
        this.attackerStatModifier = attackerStatSupplier;
    }

    /**
     * @param user {@inheritDoc}
     * @param opponent {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean apply(final Pokemon user, final Pokemon opponent) {

        final boolean success = super.apply(user, opponent) && this.isHit(user, opponent);

        if (success) {

            final Float critThreshold = (float) (1 / Math.pow(CRIT_BASE, CRIT_STAGE_POWER - critStage));

            final var thisMove = user.getMoveComponent()
                .getMoveMap()
                .get(super.getID());

            this.dmg = new MoveDamageInstance(
                user,
                thisMove,
                opponent,
                critThreshold
            );

            this.dmg.overridePower(this.powerModifier.apply(this.power));
            this.attackerStatModifier.get().ifPresent(atkStat -> {
                this.dmg.overrideAttack(atkStat);
            });

            opponent.getConditionComponent()
            .modifyDamage(dmg, false);

            user.getConditionComponent()
            .modifyDamage(dmg, true);

            if (!dmg.isFake()) {
                opponent.takeDamage(dmg);
            }
        }

        this.resetState();

        return Objects.nonNull(this.dmg)
            && !this.dmg.isBlocked()
            && success;
    }

    /**
     * Sets base value for 'transient' fields of this object.
     * 
     * @return The de-serialized instance of {@link Damage}.
     */
    public Object readResolve() {
        this.resetState();
        return this;
    }

    /**
     * Resets the varius modifiers to be applied on the damageInstance apon use.
     */
    private void resetState() {
        this.powerModifier = pow -> pow;
        this.attackerStatModifier = Optional::empty;
    }
}
