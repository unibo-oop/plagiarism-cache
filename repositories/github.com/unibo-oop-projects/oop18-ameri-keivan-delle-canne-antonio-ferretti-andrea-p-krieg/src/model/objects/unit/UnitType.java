package model.objects.unit;

/**
 * The UnitType enumeration represent the specific type that an unit can assume.
 * The types have been designed so as not to affect the extensibility of new
 * units, in fact it will suffice that they extend one of these types.
 */
public enum UnitType {

    /**
     * Is a specific type of the GenericUnitType type BASIC. It represent the BASIC unit that fight at close range.
     */
    CLOSE_BASIC(GenericUnitType.BASIC),

    /**
     * Is a specific type of the GenericUnitType type BASIC. It represent the BASIC unit that fight from a distance.
     */
    DISTANCE_BASIC(GenericUnitType.BASIC),

    /**
     * Is a specific type of the GenericUnitType type NORMAL. It represent the NORMAL unit that fight at close range.
     */
    CLOSE_NORMAL(GenericUnitType.NORMAL),

    /**
     * Is a specific type of the GenericUnitType type NORMAL. It represent the NORMAL unit that fight from a distance.
     */
    DISTANCE_NORMAL(GenericUnitType.NORMAL),

    /**
     * Is a specific type of the GenericUnitType type HERO. It represent the HERO unit that fight at close range.
     */
    HERO_CLOSE_FIGHTER(GenericUnitType.HERO),

    /**
     * Is a specific type of the GenericUnitType type HERO. It represent the HERO unit that fight from a distance.
     */
    HERO_DISTANCE_FIGHTER(GenericUnitType.HERO),

    /**
     * Is a specific type of the GenericUnitType type VEHICLE. It represent the VEHICLE that are terrain vehicle. 
     */
    TERRAIN_VEHICLE(GenericUnitType.VEHICLE),

    /**
     * Is a specific type of the GenericUnitType type VEHICLE. It represent the VEHICLE that are water vehicle. 
     */
    WATER_VEHICLE(GenericUnitType.VEHICLE),

    /**
     * Is a specific type of the GenericUnitType type VEHICLE. It represent the VEHICLE that are air vehicle. 
     */
    AIR_VEHICLE(GenericUnitType.VEHICLE);

    private GenericUnitType genericType;

    UnitType(final GenericUnitType genericType) {
        this.genericType = genericType;
    }

    /**
     * This method could be used to get the generic type of an unit type.
     * @return the generic unit type.
     */
    public GenericUnitType getGenericUnitType() {
        return this.genericType;
    }
}
