package net.pokemonbt.model.pokemon.components;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.pokemonbt.model.pokemon.PokeComponent;
import net.pokemonbt.model.pokemon.Pokemon;

import java.io.Serial;
import java.util.Objects;

/**
 * Base class for all {@link Pokemon} components. It handles
 * the basic connection between components and the pokemon
 * they "connect" to.
 */
public abstract class AbstractPokeComponent implements PokeComponent {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String SPOTBUGS_EI = "EI_EXPOSE_REP";
    private static final String SPOTBUGS_EI_COMPONENT_MESS = "This value need to be a reference to the original"
            + "component as the architecture needs to call its methods.";

    private Pokemon parent;

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = SPOTBUGS_EI, justification = SPOTBUGS_EI_COMPONENT_MESS)
    public Pokemon getPokeParent() {
        return this.parent;
    }

    /**
     * {@inheritDoc}
     *
     * @param newParent {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = SPOTBUGS_EI, justification = SPOTBUGS_EI_COMPONENT_MESS)
    public void setPokeParent(final Pokemon newParent) {
        Objects.requireNonNull(newParent);
        this.parent = newParent;
    }
}
