package model.map;

import model.objects.structures.Structure;
import model.objects.unit.Unit;
import util.Coordinates;

/**
 * 
 * The game map. It's composed of cases each having a terrain as a base. On
 * cases can be built structures, which can also be destroyed. Cases can contain
 * unit, that can move on them.
 *
 */
public interface ModifiableGameMap extends ObservableGameMap {

    /**
     * Builds the selected structure on the terrain at the given cords.
     * 
     * @param cords     the cords of the case to be modified
     * 
     * @param structure the structure to be built
     */
    void setStructure(Coordinates cords, Structure structure);

    /**
     * Removes the structure specific for the terrain.
     * 
     * @param cords the cords of the case of the structure to be removed
     * 
     * @throws IllegalArgumentException if there isn't a structure in this case
     */
    void removeStructure(Coordinates cords);

    /**
     * @param cords the cords of the case in which the unit wants to step
     * @param unit  the unit that tries to step on the case
     * @return whether the passed unit can step on the case
     */
    boolean canStep(Coordinates cords, Unit unit);

    /**
     * Make the unit step on a case.
     * When stepping on a case, the unit conquers any structure on it if present and
     * steps on a vehicle if a vehicle producer is there.
     * 
     * @param cords the cords of the case in which the structure has to be conquered
     * 
     * @param unit  to set on the case
     * 
     * @throws IllegalArgumentException if the Unit can't step on the case or the case is already occupied by another Unit
     */
    void setUnit(Coordinates cords, Unit unit);

    /**
     * removes the unit from the case in position cords.
     * 
     * @param cords the cords of the case in which to remove the unit
     * @throws IllegalArgument  Exception if there is no Unit in the case
     */
    void removeUnit(Coordinates cords);

}
