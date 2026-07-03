package hotelmaster.database.admin;

import java.sql.SQLException;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.exceptions.PriceDescriberRemovalException;
import hotelmaster.exceptions.UnmodifiablePriceDescriberException;
import hotelmaster.pricing.PriceDescriber;

/**
 * Manages the operations with the types of stay (administrator level).
 */
public class StayType extends BasicPriceDescriberOperation {

    private final QueryManager manager;
    /**
     * 
     */
    public StayType() { 
        this.manager = new QueryManagerImpl();
    }

    @Override
    public void create(final PriceDescriber stay) throws IllegalArgumentException {
        final String query = "INSERT INTO Pensione (descrizione, prezzo) VALUES (?,?)";
        try {
            manager.prepareQuery(query).string(1, stay.getDescription())
                               .price(2, stay.getPrice())
                               .update();
        } catch (SQLException e) {
            throw new IllegalArgumentException("The stay type already exists", e);
        }
    }

    @Override
    public void removeType(final PriceDescriber type) throws PriceDescriberRemovalException {
        this.remove(type, "Pensione");
    }

    @Override
    public void modifyPrice(final PriceDescriber type) throws UnmodifiablePriceDescriberException {
        this.modify(type, "Pensione");
    }

}
