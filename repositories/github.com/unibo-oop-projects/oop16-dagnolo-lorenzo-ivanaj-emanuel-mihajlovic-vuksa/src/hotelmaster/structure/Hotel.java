package hotelmaster.structure;

import java.util.Map;
import java.util.Set;

import hotelmaster.pricing.PriceDescriber;
import hotelmaster.reservations.Client;
import hotelmaster.reservations.DocumentType;
import hotelmaster.reservations.Stay;

/**
 * The hotel containing all of its {@link PriceDescriber}s, {@link Room}s,
 * {@link Stay}s and {@link Client}s.
 */
public interface Hotel {

    /**
     * Returns the floors in the hotel and the highest room number in each
     * floor.
     * 
     * @return the floors in an immutable map
     */
    Map<Integer, Integer> getFloorView();

    /**
     * Returns the types of documents avaliable.
     * 
     * @return the documents in an immutable set
     */
    Set<DocumentType> getDocuments();

    /**
     * Returns the PriceDescribers of a certain given type. This Set may not be
     * modified.
     * 
     * @param <T>
     *            the type of the price describer
     * @param priceType
     *            the type of the price describers to be returned
     * @return the PriceDescribers in an unmodifiable set
     */
    <T extends PriceDescriber> Set<T> getPriceView(Class<T> priceType);

    /**
     * Returns whether this hotel has a given PriceDescriber.
     * 
     * @param <T>
     *            the type of the price describer
     * @param price
     *            the PriceDescriber
     * @return the existence of the PriceDescriber
     */
    <T extends PriceDescriber> boolean hasPriceDescriber(T price);

    /**
     * Returns all the rooms in the hotel. This Set may not be modified.
     * 
     * @return the unmodifiable set of rooms
     */
    Set<Room> getRoomView();

    /**
     * Returns all the stays in the hotel. This Set may not be modified.
     * 
     * @return the unmodifiable set of stays
     */
    Set<Stay> getStayView();

    /**
     * Returns all the clients which are or have been in the hotel. This Set may
     * not be modified.
     * 
     * @return the unmodifiable set of clients
     */
    Set<Client> getClientView();

    /**
     * Returns the singleton instance of the hotel, accordingly loaded.
     * 
     * @return the new Hotel instance
     */
    static Hotel instance() {
        return ModifiableHotel.instance();
    }
}
