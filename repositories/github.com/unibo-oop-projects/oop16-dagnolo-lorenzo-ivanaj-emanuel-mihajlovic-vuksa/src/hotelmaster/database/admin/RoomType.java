package hotelmaster.database.admin;

import java.sql.SQLException;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.exceptions.PriceDescriberRemovalException;
import hotelmaster.exceptions.UnmodifiablePriceDescriberException;
import hotelmaster.pricing.PriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;

/**
 * Manages the operations with room types (administrator level).
 */
public class RoomType extends BasicPriceDescriberOperation {

    private final QueryManager manager;

    /**
     * 
     */
    public RoomType() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public void create(final PriceDescriber type) throws IllegalArgumentException {
        final String query = "INSERT INTO TipoCamera (prezzo, descrizione, maxPosti,prezzoAggiuntivo) VALUES (?,?,?,?)";
        try {
            final RoomTypePriceDescriber roomType = (RoomTypePriceDescriber) type; 

            manager.prepareQuery(query).price(1, roomType.getPrice()).string(2, roomType.getDescription())
                    .integer(3, roomType.getMaxPeople()).price(4, roomType.getMissingPrice()).update();
        } catch (SQLException e) {
            throw new IllegalArgumentException("The type of room already exists", e);
        }
    }

    @Override
    public void removeType(final PriceDescriber type) throws PriceDescriberRemovalException {
        this.remove(type, "TipoCamera");
    }

    @Override
    public void modifyPrice(final PriceDescriber type) throws UnmodifiablePriceDescriberException {
        this.modify(type, "TipoCamera");
    }

}
