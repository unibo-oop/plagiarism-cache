package hotelmaster.pricing;

import hotelmaster.structure.HotelCollection;
import hotelmaster.utility.collections.TypeSetMap;

/**
 * A manager for a number of prices which allows setting a price's value.
 */
public interface PriceCollection extends HotelCollection<PriceDescriber>, TypeSetMap<PriceDescriber> {

    /**
     * Sets a price to a new given value.
     * 
     * @param price
     *            the price to be set
     * @param newValue
     *            the new value
     * @return true if the collection has been modified
     */
    boolean setPrice(PriceDescriber price, double newValue);

    /**
     * Creates a new PriceManager.
     * 
     * @return the new instance
     */
    static PriceCollection create() {
        return new PriceCollectionImpl();
    }
}
