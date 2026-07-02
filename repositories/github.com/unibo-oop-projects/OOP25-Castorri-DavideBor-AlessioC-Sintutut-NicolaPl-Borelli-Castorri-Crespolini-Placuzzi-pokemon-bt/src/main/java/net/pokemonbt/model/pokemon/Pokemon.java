package net.pokemonbt.model.pokemon;

import net.pokemonbt.model.battle.DamageInstance;
import net.pokemonbt.model.pokemon.components.StatComponent;
import net.pokemonbt.utility.Unique;
import net.pokemonbt.model.pokemon.components.ConditionComponent;
import net.pokemonbt.model.pokemon.components.ItemComponent;
import net.pokemonbt.model.pokemon.components.MoveComponent;

/**
 * Base interface for all Pokemons.
 */
public interface Pokemon extends Unique, Cloneable {

    /**
     * @return The {@link Pokemon}'s display name.
     */
    String getName();

    /**
     * Changes the {@link TeamType} of the pokemon.
     *
     * @param teamType The new {@link TeamType} to set.
     */
    void setTeamType(TeamType teamType);

    /**
     * @return The {@link Pokemon}'s {@link TeamType}.
     */
    TeamType getTeamType();

    /**
     * Apply a single instance of damage to this pokemon.
     *
     * @param dmg The damage instance representing the hit to apply.
     */
    void takeDamage(DamageInstance dmg);

    /**
     * Applies a single instance of healing to this pokemon.
     * The instance of healing is represented by an integer.
     *
     * @param amount The amount of hp to heal by.
     */
    void heal(int amount);

    /**
     * Gets the pokemon's {@link MoveComponent}.
     *
     * @return The reference to the component.
     */
    MoveComponent getMoveComponent();

    /**
     * Gets the pokemon's {@link StatComponent}.
     *
     * @return The reference to the component.
     */
    StatComponent getStatComponent();

    /**
     * Gets the pokemon's {@link ConditionComponent}.
     *
     * @return The reference to the component.
     */
    ConditionComponent getConditionComponent();

    /**
     * Gets the pokemon's {@link ItemComponent}.
     *
     * @return The reference to the component.
     */
    ItemComponent getItemComponent();
}
