package model.minigames.perilouspath;

import java.util.Optional;
import java.util.Set;

import utility.Pair;

/**
 * Represents a fragmentation {@link Mine}.
 */
public interface FragmentationMine extends Mine {

    /**
     * Gets the fragments position.
     * 
     * @return
     *      a set of positions of the scraps if the mine is exploded
     */
    Optional<Set<Pair<Integer, Integer>>> getScraps();
}
