package hotelmaster.structure;

import java.util.Set;

import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;

/**
 * A room whose occupations can be modified according to certain rules.
 */
public interface ModifiableRoom extends Room {

    /**
     * Returns the extras of this room.
     * 
     * @return the extras
     */
    Set<RoomExtraPriceDescriber> getExtras();

    /**
     * Sets the type of the room.
     * 
     * @param type
     *            the new type. It must exist in the room's hotel
     */
    void setType(RoomTypePriceDescriber type);

    /**
     * Returns a new instance of room, with the ID set to the appropriate floor
     * and number-on-floor.
     * 
     * @param floor
     *            the floor on which the room is
     * @param numberOnFloor
     *            the number of the room on its floor
     * @return the instance
     */
    static ModifiableRoom create(final int floor, final int numberOnFloor) {
        return new RoomImpl(floor, numberOnFloor);
    }
}
