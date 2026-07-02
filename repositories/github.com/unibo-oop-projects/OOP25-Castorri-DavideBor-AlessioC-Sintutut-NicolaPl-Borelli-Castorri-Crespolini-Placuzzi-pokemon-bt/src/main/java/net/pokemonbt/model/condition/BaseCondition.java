package net.pokemonbt.model.condition;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

import net.pokemonbt.model.battle.BattleEvent;
import net.pokemonbt.model.condition.components.ConditionTypeCheck;
import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * 
 */
public final class BaseCondition implements Condition {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String condID;
    private final String condName;
    private final String condDescription;
    private final ConditionBehaviour behaviour;
    private final boolean permanent;
    private final ConditionTypeCheck immunities;
    private final BattleEvent battleEvent;

    /**
     * @param condID the ID of the condition 
     * @param behaviour the behaviour of the condiotion 
     * @param eventTrigger when the condition gets triggered
     * @param permanent if the condition is volitile or permanent
     * @param imm the types that are immune from the condition
     * @param condName the name of the condition
     * @param condDescription the description of the condition
     */
    public BaseCondition(
            final String condID,
            final ConditionBehaviour behaviour,
            final BattleEvent eventTrigger,
            final boolean permanent,
            final List<PokeType> imm,
            final String condName,
            final String condDescription
        ) {
            this.condID = condID;
            this.behaviour = behaviour;
            this.permanent = permanent;
            this.immunities = new ConditionTypeCheck(imm);
            this.condDescription = condDescription;
            this.condName = condName;
            this.battleEvent = eventTrigger;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean isPermanent() {
        return permanent;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean canBeApplied(final Pokemon pokemon) {
        return immunities.canBeApplied(pokemon);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getCondID() {
        return condID;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public ConditionBehaviour getBehaviour() {
        return behaviour;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getName() {
        return condName;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return condDescription;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public BattleEvent getBattleEvent() {
        return this.battleEvent;
    }

    /**
     * @return a string representation of the condition
     */
    @Override
    public String toString() {
        final String newLine = "\n";
        return "Condition: ".concat(condName).concat(newLine)
        .concat("Description: ").concat(condDescription).concat(newLine)
        .concat("Is Permanent: ").concat(Boolean.toString(permanent)).concat(newLine);
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(
            this.condID,
            this.permanent
        );
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
        final BaseCondition other = (BaseCondition) obj;
        return this.condID.equals(other.condID)
                && this.permanent == other.permanent;
    }
}
