package model.map;

import java.util.Optional;

import model.objects.structures.Structure;
import model.objects.terrains.Terrain;
import model.objects.unit.Unit;

/**
 * This interface models each case of the game map.
 * package protected.
 */
interface Case {
    /**
     * 
     * @return the Unit on the case if present
     */
    Optional<Unit> getUnit();

    /**
     * @param unit the unit that tries to step on the case
     * @return whether the passed unit can step on the case
     */
    boolean canStep(Unit unit);

    /**
     * @return the terrain of the case
     */
    Terrain getTerrain();

    /**
     * Builds the selected structure on the terrain.
     * 
     * @param structure the structure to be built
     */
    void setStructure(Structure structure);

    /**
     * @return the structure associated with this terrain if present
     */
    Optional<Structure> getStructure();

    /**
     * Removes the structure associated with the terrain.
     * @throws IllegalStateException if there is no structure on the case
     */
    void removeStructure();

    /**
     * when stepping on a case, the unit conquers any structure on it if present and
     * mounts on a vehicle if a vehicle factory is present.
     * 
     * @param unit the unit that steps on the case
     * 
     * @throws IllegalArgumentException if the Unit can't step on the case
     * 
     * @throws IllegalStateException    if the case is already occupied by another
     *                                  Unit
     */
    void setUnit(Unit unit);

    /**
     * removes the unit from the case in position cords.
     * 
     * @throws IllegalStateException if there is no Unit in the case
     */
    void removeUnit();

}
