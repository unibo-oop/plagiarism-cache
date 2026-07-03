package hotelmaster.reservations;

import java.util.Map;

import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.structure.ModifiableRoom;
import hotelmaster.structure.Room;

/**
 * An occupation with getters of its modifiable fields.
 */
public interface ModifiableOccupation extends Occupation {

    /**
     * Returns the stay of this occupation as a modifiable stay.
     * 
     * @return the modifiable stay
     */
    @Override
    ModifiableStay getStay();

    /**
     * Returns the room of this occupation as a modifiable room.
     * 
     * @return the modifiable room
     */
    @Override
    ModifiableRoom getRoom();

    /**
     * Sets the people in the stay from the given map.
     * 
     * @param people
     *            the new people in the stay
     */
    void setPeople(Map<PersonPriceDescriber, Integer> people);

    /**
     * Instances a new ModifiableOccupation with the given parameters.
     * 
     * @param stay
     *            the stay of the occupation
     * @param room
     *            the room of the occupation
     * @param people
     *            the people of the occupation
     * @return the instance
     */
    static ModifiableOccupation create(final Stay stay, final Room room,
            final Map<PersonPriceDescriber, Integer> people) {
        return new OccupationImpl((ModifiableStay) stay, (ModifiableRoom) room, people);
    }
}
