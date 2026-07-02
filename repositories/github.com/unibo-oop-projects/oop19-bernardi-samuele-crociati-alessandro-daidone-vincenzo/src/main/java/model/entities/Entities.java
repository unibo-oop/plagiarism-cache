package model.entities;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * Functional class for entity related stuff.
 *
 */
public final class Entities {

    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_NAME_POS = 0;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_MAXHP_POS = 1;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_CURRENTHP_POS = 2;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_ATTACK_POS = 3;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_DEFENSE_POS = 4;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_ENTITYTYPE_POS = 5;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_ENTITYSTATUS_POS = 6;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_MOVEMENTTYPE_POS = 7;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_ATTACKTYPE_POS = 8;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_ATTACKSTATUS_POS = 9;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_MOVEMENTSTATUS_POS = 10;
    /**
     * Specifies the position in the list of info of the requested property.
     */
    public static final int INFO_POSITION_POS = 11;

    private Entities() {
    }

    /**
     * Returns all of the types of attack present in the enumerator.
     * 
     * @return The list of all the enumerator values AttackType can have
     */
    public static List<AttackType> getAllAttackTypes() {
        return Stream.of(AttackType.SWORD, AttackType.POLEARM, AttackType.BOW, AttackType.NONE)
                .collect(Collectors.toList());
    }

    /**
     * Returns all of the types of movement present in the enumerator.
     * 
     * @return The list of all the enumerator values MovementType can have
     */
    public static List<MovementType> getAllMovementTypes() {
        return Stream.of(MovementType.FEET, MovementType.FLYING, MovementType.NONE).collect(Collectors.toList());
    }

}
