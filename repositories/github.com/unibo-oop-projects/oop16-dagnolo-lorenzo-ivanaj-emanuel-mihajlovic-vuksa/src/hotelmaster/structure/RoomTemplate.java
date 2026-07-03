package hotelmaster.structure;

import java.util.Optional;
import java.util.Set;

import hotelmaster.exceptions.MissingEntityException;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;

/**
 * A template room, representing an ID-less room. To be used when adding rooms
 * to a hotel.
 */
public interface RoomTemplate {

    /**
     * Set the type of the room.
     * 
     * @param roomType
     *            the type of the room
     * @return this instance of RoomTemplate
     * @throws MissingEntityException
     *             the roomType does not exist in the hotel
     */
    RoomTemplate setRoomType(RoomTypePriceDescriber roomType) throws MissingEntityException;

    /**
     * Add an extra to this room.
     * 
     * @param roomExtra
     *            the extra for the room
     * @return this instance of RoomTemplate
     * @throws MissingEntityException
     *             the roomExtra does not exist in the hotel
     * @throws IllegalArgumentException
     *             roomExtra has already been added to the extras
     */
    RoomTemplate addRoomExtra(RoomExtraPriceDescriber roomExtra)
            throws MissingEntityException, IllegalArgumentException;

    /**
     * Returns the RoomType, if it has been set.
     * 
     * @return an Optional containing the RoomType
     */
    Optional<RoomTypePriceDescriber> getRoomType();

    /**
     * Returns all the RoomExtra that have been added.
     * 
     * @return a set of RoomExtra
     */
    Set<RoomExtraPriceDescriber> getRoomExtras();

    /**
     * Instances a new RoomTemplate.
     * 
     * @return the new instance
     */
    static RoomTemplate create() {
        return new RoomTemplateImpl();
    }
}
