package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.Optional;

import com.google.gson.JsonObject;

import net.pokemonbt.model.condition.ConditionBehaviour;
import net.pokemonbt.model.pokemon.PokeStatType;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * When a {@link Pokemon} is {@link Seeded} the will get damage and heal the
 * enemy.
 * When a {@link Pokemon} is {@link Seeded} the will get damage and heal the
 * enemy.
 */
public final class Seeded extends AbstractConditionDecorator {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int damagePercentage;

    /**
     * @param behaviour {@inheritDoc}
     * @param damage the percentage of the damage to deal based on Max Hp
     */
    public Seeded(final ConditionBehaviour behaviour, final Optional<JsonObject> damage) {
        super(behaviour);

        if (damage.isEmpty()) {
        throw new IllegalArgumentException("damage cannot be an empty Optional.");
        }
        this.damagePercentage = damage.get().get("damage").getAsInt();
    }

    /**
     * @param behaviour the behaviour to copy
     * @param toCopy the instance of {@link Seeded} to take values from
     */
    public Seeded(final ConditionBehaviour behaviour, final Seeded toCopy) {
        super(behaviour);
        this.damagePercentage = toCopy.damagePercentage;
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean trigger(final Pokemon pokemon, final Pokemon opponent) {

        final int damageHeal = 
        pokemon.getStatComponent().getHP()
        <= pokemon.getStatComponent().getStat(PokeStatType.HP_MAX) * damagePercentage / 100 
        ? pokemon.getStatComponent().getHP() 
        : pokemon.getStatComponent().getStat(PokeStatType.HP_MAX) * damagePercentage / 100;

        pokemon
        .getStatComponent()
        .removeHealth(damageHeal);

        opponent.getStatComponent()
        .increaseHealth(damageHeal);
        return super.trigger(pokemon, opponent);
    }
}
