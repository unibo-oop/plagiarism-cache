package model.races;

import model.Cost;
import model.objects.unit.UnitType;

/**
 * The Race interface represent a race. A race assigns unique types of boosts to
 * the type. The boosts will then be applied to a player's unit of a specific
 * race. Boosts are foreseen that concern attack, hp, range of movement, range
 * of attack, possible attacks and cost of creating a unit
 */

public interface Race {

    /**
     * This method could be used for get the race's name.
     * 
     * @return race's name.
     */
    String getRaceName();

    /**
     * This method could be used for get the strength boost of a specific UnitType.
     * 
     * @param unitType is the unit's type.
     * @return the strenght boost.
     */
    int getStrBoost(UnitType unitType);

    /**
     * This method could be used for get the hp boost of a specific UnitType.
     * 
     * @param unitType is the unit's type.
     * @return the hp boost.
     */
    int getHpBoost(UnitType unitType);

    /**
     * This method could be used for get the movement range boost of a specific UnitType.
     * 
     * @param unitType is the unit's type.
     * @return the movement range boost.
     */
    int getMovRangeBoost(UnitType unitType);

    /**
     * This method could be used for get the attack range boost of a specific UnitType.
     * 
     * @param unitType is the unit's type.
     * @return the attack range boost.
     */
    int getAttRangeBoost(UnitType unitType);

    /**
     * This method could be used for get the possible attack boost of a specific UnitType.
     * 
     * @param unitType is the unit's type.
     * @return the possible attack boost.
     */
    int getPossibleAttBoost(UnitType unitType);

    /**
     * This method could be used for get the creation cost boost of a specific UnitType.
     * 
     * @param unitType is the unit's type.
     * @return the creation cost boost.
     */
    Cost getCostBoost(UnitType unitType);

}
