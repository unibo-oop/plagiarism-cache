package hotelmaster.database.admin;

import hotelmaster.exceptions.PriceDescriberRemovalException;
import hotelmaster.exceptions.UnmodifiablePriceDescriberException;
import hotelmaster.pricing.PriceDescriber;
/**
 * Price describer operations (Administrator level).
 */
public interface PriceUtilities {
    /**
     * Create a new PriceDescriber.
     * @param price the price to be created
     */
    void create(PriceDescriber price);
    /**
     * Remove an existing price describer.
     * @param price the price to be removed
     * @throws PriceDescriberRemovalException if this type cannot be removed
     */
    void delete(PriceDescriber price) throws PriceDescriberRemovalException;
    /**
     * Update the price of an existing price describer.
     * @param price the price to be updated
     * @throws UnmodifiablePriceDescriberException if this type cannot be modified
     */
    void update(PriceDescriber price) throws UnmodifiablePriceDescriberException;
}
