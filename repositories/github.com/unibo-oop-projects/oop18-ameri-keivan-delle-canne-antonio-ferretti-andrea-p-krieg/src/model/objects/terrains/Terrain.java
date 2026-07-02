package model.objects.terrains;

import java.util.Set;

import model.abilities.Ability;
import model.objects.GameObject;

/**
 * A terrain can have on it a structure or nothing. In some terrains structures
 * can't be built.
 */
public interface Terrain extends GameObject {

    /**
     * @return the abilities required to step on this terrain
     */
    Set<Ability> getRequiredAbilities();

    /**
     * @return the unique name identifier of this terrain
     */
    String getId();

}
