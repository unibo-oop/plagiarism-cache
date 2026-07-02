package model.objects.unit;

/**
 * The GenericUnitType enumeration represent all the generic type that an unit can assume.
 *
 */
public enum GenericUnitType {
    /**
     * BASIC represent the basic units. The basic units are the units that are unlock when the game start.
     */
    BASIC,
    /**
     * NORMAL represent the normal units that can be unlock during the game.
     */
     NORMAL, 
     /**
      * HERO represent the hero units. The hero units can be unlock during the game.
      */
     HERO, 
     /**
      * VEHICLE, represent the vehicle in game. A vehicle is consider as an unit.
      */
     VEHICLE;
}
