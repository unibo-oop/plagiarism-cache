package net.pokemonbt.model.pokemon;

import java.io.Serial;

/**
 * Special behaviour for the evasion stat.
 */
public class PokeEvasionValue extends PokeStatValue {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final int EVASION_FRACTION = 3;

    /**
     * @param baseValue {@inheritDoc}
     */
    public PokeEvasionValue(final int baseValue) {
        super(baseValue);
    }

    /**
     * @return {@inheritDoc}
     */
    @Override
    protected int getFraction() {
        return EVASION_FRACTION;
    }
}
