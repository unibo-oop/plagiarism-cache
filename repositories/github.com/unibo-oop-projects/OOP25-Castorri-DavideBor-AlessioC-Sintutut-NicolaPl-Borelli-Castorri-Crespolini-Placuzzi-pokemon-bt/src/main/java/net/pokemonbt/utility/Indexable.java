package net.pokemonbt.utility;

import java.io.Serializable;

/**
 * A simple interface for the handling of an object's ID.
 */
@FunctionalInterface
public interface Indexable extends Serializable {
    /**
     * @return A simple {@link String} that differenciate an object from the other.
     */
    String getID();
}
