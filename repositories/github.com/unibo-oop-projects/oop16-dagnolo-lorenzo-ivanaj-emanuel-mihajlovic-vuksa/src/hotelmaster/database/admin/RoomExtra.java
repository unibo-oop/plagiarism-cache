package hotelmaster.database.admin;

import java.sql.SQLException;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.exceptions.PriceDescriberRemovalException;
import hotelmaster.exceptions.UnmodifiablePriceDescriberException;
import hotelmaster.pricing.PriceDescriber;

/**
 * Manages the operations on room extras (administrator level).
 */
public class RoomExtra extends BasicPriceDescriberOperation {

    private final QueryManager manager;
    /**
     * 
     */
    public RoomExtra() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public void create(final PriceDescriber extra) throws IllegalArgumentException {
        String query = "INSERT INTO ExtraCamera (descrizione, prezzo) VALUES (?,?)";
        try {
            manager.prepareQuery(query).string(1, extra.getDescription())
                                       .price(2, extra.getPrice())
                                       .update();
        } catch (SQLException e) {
            throw new IllegalArgumentException("The extra already exists", e);
        }
    }

    @Override
    public void removeType(final PriceDescriber extra) throws PriceDescriberRemovalException {
       this.remove(extra, "ExtraCamera");
    }

    @Override
    public void modifyPrice(final PriceDescriber extra) throws UnmodifiablePriceDescriberException {
        this.modify(extra, "ExtraCamera");
    }

}
