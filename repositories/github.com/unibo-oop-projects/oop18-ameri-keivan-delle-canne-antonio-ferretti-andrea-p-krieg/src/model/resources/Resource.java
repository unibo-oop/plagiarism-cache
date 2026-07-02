package model.resources;

import java.util.Optional;

/**
 * Models a Resource that can be obtained from its ResourceProducer and can be
 * used to produce other things.
 */
public interface Resource {
    /**
     * @return the name of the Resource
     */
    String getName();

    /**
     * @return the modifier for particular resources
     */
    Optional<Integer> getModifier();
}
