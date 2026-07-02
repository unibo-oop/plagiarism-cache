package net.pokemonbt.model.pokemon.components;

import net.pokemonbt.model.pokemon.Pokemon;
import net.pokemonbt.utility.Clone;
import net.pokemonbt.model.condition.Condition;
import net.pokemonbt.model.condition.components.ConditionDispenser;
import net.pokemonbt.model.move.components.Damage;

import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import net.pokemonbt.model.battle.BattleEvent;
import net.pokemonbt.model.battle.MoveDamageInstance;

/**
 * {@link Pokemon} component that handles condition
 * behaviour for applying, removing and executing a
 * condition's effect.
 */
public final class ConditionComponent extends AbstractPokeComponent implements Clone<ConditionComponent> {

    @Serial
    private static final long serialVersionUID = 1L;

    private transient Optional<Condition> permanentCondition;
    private final Map<String, Condition> volatileCond;
    private final List<String> toRemove;

    /**
     * Don't create this component by itself, only
     * use it inside {@link Pokemon} constructor.
     */
    public ConditionComponent() {
        this.permanentCondition = Optional.empty();
        this.volatileCond = new LinkedHashMap<>();
        this.toRemove = new LinkedList<>();
    }

    /**
     * @return if the pokemon has one or more volatile conditions
     */
    public boolean hasVolatileCondition() {
        return !volatileCond.isEmpty();
    }

    /**
     * @return if the pokemon has a permanent condition
     */
    public boolean hasPermanentCondition() {
        return permanentCondition.isPresent();
    }

    /**
     * @return the permanent Condition applied to the pokemon
     */
    public Condition getPermanentCondition() {
        if (permanentCondition.isPresent()) {
            return permanentCondition.get();
        }
        return null;
    }

    /**
     * @param condID the id of the condition to find
     * @return if the pokemon has the condition searched
     */
    public boolean hasCondition(final String condID) {
        Objects.requireNonNull(condID);

        return permanentCondition.isPresent() && permanentCondition.get().getCondID().equals(condID)
                || volatileCond.containsKey(condID);
    }

    /**
     * @param newCondID the id of the condition to apply
     * @return if the condition was applied
     */
    public boolean applyCondition(final String newCondID) {
        Objects.requireNonNull(newCondID);
        final Condition newCond = ConditionDispenser.get(newCondID);

        if (newCond.isPermanent()) {
            if (!permanentCondition.isPresent() && newCond.canBeApplied(this.getPokeParent())) {
                permanentCondition = Optional.ofNullable(newCond);
                return true;
            }
        } else if (!hasCondition(newCond.getCondID()) && newCond.canBeApplied(this.getPokeParent())) {
            volatileCond.put(newCondID, newCond);
            return true;
        }
        return false;
    }

    /**
     * Updates the condition.
     * 
     * @param condID    the id of the condition already present
     * @param newCondID the id of the new condition
     */
    public void updateCondition(final String condID, final String newCondID) {
        Objects.requireNonNull(condID);
        Objects.requireNonNull(newCondID);
        final Condition newCond = ConditionDispenser.get(newCondID);
        if (hasCondition(condID)) {
            if (permanentCondition.get().getCondID().equals(condID)) {
                permanentCondition = Optional.ofNullable(newCond);
            } else if (volatileCond.containsKey(condID)) {
                volatileCond.remove(condID);
                volatileCond.put(newCondID, newCond);
            }
        }
    }

    /**
     * @param condID the id of the condition to remove
     * @return if it was able to remove the condition
     */
    public boolean removeCondition(final String condID) {
        Objects.requireNonNull(condID);
        if (permanentCondition.isPresent() && permanentCondition.get().getCondID().equals(condID)) {
            permanentCondition.get().getBehaviour().onRemove(getPokeParent());
            permanentCondition = Optional.empty();
            return true;
        }
        if (volatileCond.containsKey(condID)) {
            volatileCond.get(condID).getBehaviour().onRemove(getPokeParent());
            volatileCond.remove(condID);
            return true;
        }
        return false;
    }

    /**
     * Clears the Map with volitile conditions.
     */
    public void removeVolatileConditions() {
        for (final Condition cond : volatileCond.values()) {
            cond.getBehaviour().onRemove(getPokeParent());
        }
        volatileCond.clear();
    }

    /**
     * Clears the permanent condition.
     */
    public void removePermanentCondition() {
        if (permanentCondition.isPresent()) {
            permanentCondition.get().getBehaviour().onRemove(getPokeParent());
        }

        permanentCondition = Optional.empty();
    }

    /**
     * triggers the condition effect.
     * 
     * @param trigger the {@link BattleEvent} when the condition should trigger
     * @param opponent the opponent of the {@link Pokemon} that has the condition
     */
    public void triggerCondition(final BattleEvent trigger, final Pokemon opponent) {
        for (final Condition cond : volatileCond.values()) {
            if (cond.getBattleEvent() == trigger) {
                cond.getBehaviour().trigger(getPokeParent(), opponent);
            }
        }

        for (final String string : toRemove) {
            removeCondition(string);
        }
        toRemove.clear();

        if (permanentCondition.isPresent() && permanentCondition.get().getBattleEvent() == trigger) {
            permanentCondition.get().getBehaviour().trigger(getPokeParent(), opponent);
        }
    }

    /**
     * @param damage the damageInstance to modify
     * @param isUser if the damage is dealt by the user or the enemy
     */
    public void modifyDamage(final MoveDamageInstance damage, final boolean isUser) {
        for (final Condition cond : volatileCond.values()) {
            cond.getBehaviour().modifyMove(damage, isUser);
        }

        if (permanentCondition.isPresent()) {
            permanentCondition.get().getBehaviour().modifyMove(damage, isUser);
        }
    }

    /**
     * @param conditionToRemove the condition that wants to remove itself while triggering
     */
    public void addToRemoveList(final String conditionToRemove) {
        toRemove.add(conditionToRemove);
    }

    /**
     * Sets base value for 'transient' fields of this object.
     * 
     * @return The de-serialized instance of {@link Damage}.
     */
    public Object readResolve() {
        this.permanentCondition = Optional.empty();
        return this;
    }

    /**
     * @return {@inheritDocs}
     */
    @Override
    public ConditionComponent copyOf() {
        final ConditionComponent clone = new ConditionComponent();
        clone.permanentCondition = this.permanentCondition;
        clone.volatileCond.putAll(this.volatileCond);
        return clone;
    }

    /**
     * {@inheritDoc}
     * 
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override 
    public boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ConditionComponent other = (ConditionComponent) obj;
        return this.permanentCondition.equals(other.permanentCondition)
                && this.volatileCond.equals(other.volatileCond);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override 
    public int hashCode() {
        return Objects.hash(
            this.permanentCondition,
            this.volatileCond
        );
    }
}
