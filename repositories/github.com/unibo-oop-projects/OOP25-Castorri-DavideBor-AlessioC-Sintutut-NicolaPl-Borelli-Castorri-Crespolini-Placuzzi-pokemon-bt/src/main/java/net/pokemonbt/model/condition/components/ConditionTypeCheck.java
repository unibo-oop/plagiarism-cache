package net.pokemonbt.model.condition.components;

import java.io.Serial;
import java.util.List;

import net.pokemonbt.model.pokemon.PokeType;
import net.pokemonbt.model.pokemon.Pokemon;

/**
 * Checks if the {@link Condition} is possible to be applied 
 * on the {@link Pokemon}.
 */
public final class ConditionTypeCheck implements ConditionCheck {

    @Serial
    private static final long serialVersionUID = 1L;
    private final List<PokeType> types;

    /**
     * @param types list of {@link PokeType} that are immune 
     */
    public ConditionTypeCheck(
        final List<PokeType> types
    ) {
        this.types = List.copyOf(types);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    public boolean canBeApplied(final Pokemon target) {
        return types.isEmpty() 
                || !(this.types.contains(target.getStatComponent().getPrimaryType())
                || this.types.contains(target.getStatComponent().getSecondaryType())); 
    }
}
