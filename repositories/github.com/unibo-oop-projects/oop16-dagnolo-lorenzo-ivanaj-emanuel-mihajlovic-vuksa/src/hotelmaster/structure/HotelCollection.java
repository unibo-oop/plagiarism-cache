package hotelmaster.structure;

import java.util.Collection;

/**
 * A collection of elements in the hotel which allows no duplicate or null
 * elements. The elements saved to the data source whenever they are added,
 * removed or set.
 * 
 * @param <E>
 *            the type of the element
 */
public interface HotelCollection<E extends HotelEntity> extends Collection<E> {

}
