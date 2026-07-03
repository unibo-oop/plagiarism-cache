package hotelmaster.exceptions;

import hotelmaster.structure.HotelEntity;

/**
 * A certain type-entity, such as a PriceDescriber or a Document type, does not
 * exist in the hotel.
 */
public class MissingEntityException extends HotelMasterException {

    /**
     * 
     */
    private static final long serialVersionUID = 6673823214496423534L;

    /**
     * @param entity
     *            the missing entity
     */
    public MissingEntityException(final Class<? extends HotelEntity> entity) {
        super("The given " + entity.getName() + " is not in the hotel");
    }

}
