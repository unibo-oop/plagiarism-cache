package hotelmaster.database.admin;

import java.sql.SQLException;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.exceptions.PriceDescriberRemovalException;
import hotelmaster.exceptions.UnmodifiablePriceDescriberException;
import hotelmaster.pricing.PriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;

/**
 * Manages the operations with the types of stay extra (administrator level).
 */
public class StayExtra extends BasicPriceDescriberOperation {

    private final QueryManager manager;
    /**
     * 
     */
    public StayExtra() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public void create(final PriceDescriber type) throws IllegalArgumentException {
        final StayExtraPriceDescriber extra = (StayExtraPriceDescriber) type;
        String query = "INSERT INTO Supplemento (descrizione, prezzo, aPersona) VALUES (?,?,?)";
        try {
            manager.prepareQuery(query).string(1, extra.getDescription())
                               .price(2, extra.getPrice())
                               .integer(3, extra.isPerPerson() ? 1 : 0)
                               .update();
        } catch (SQLException e) {
            throw new IllegalArgumentException("The stay extra already exists", e);
        }
    }

    @Override
    public void removeType(final PriceDescriber extra) throws PriceDescriberRemovalException {
       this.remove(extra, "Supplemento");
    }

    @Override
    public void modifyPrice(final PriceDescriber extra) throws UnmodifiablePriceDescriberException  {
        this.modify(extra, "Supplemento");
    }

}
