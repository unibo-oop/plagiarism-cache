package net.pokemonbt.model.pokemon;

import java.io.Serial;
import java.io.Serializable;

/**
 * A component wraps a specific pokemon logic in a neat separate class.
 */
public interface PokeComponent extends Serializable, Cloneable {
    @Serial
    long serialVersionUID = 1L;

    /**
     * Gets the pokemon associated with this component.
     *
     * @return The associated pokemon.
     */
    Pokemon getPokeParent();

    /**
     * Sets a new parent to this component.
     *
     * @param parent The new parent to associate to this component.
     */
    void setPokeParent(Pokemon parent);
}
