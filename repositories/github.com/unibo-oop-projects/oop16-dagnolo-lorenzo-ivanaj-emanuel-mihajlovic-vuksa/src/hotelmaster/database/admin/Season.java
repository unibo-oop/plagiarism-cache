package hotelmaster.database.admin;

import java.sql.SQLException;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.exceptions.PriceDescriberRemovalException;
import hotelmaster.exceptions.UnmodifiablePriceDescriberException;
import hotelmaster.pricing.PriceDescriber;
import hotelmaster.pricing.SeasonPriceDescriber;
/**
 * Manages the seasons (admin level).
 *
 */
public class Season extends BasicPriceDescriberOperation {

    private final QueryManager manager;
    /**
     * 
     */
    public Season() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    void create(final PriceDescriber price) throws IllegalArgumentException {
        final SeasonPriceDescriber season = (SeasonPriceDescriber) price;
        final String query = "INSERT INTO Stagione (descrizione, dataInizio, dataFine, prezzo) VALUES " + "(?,?,?,?)";
        try {
            manager.prepareQuery(query).string(1, season.getDescription())
                                       .date(2, season.getPeriod().getBeginning())
                                       .date(3, season.getPeriod().getEnd())
                                       .price(4, season.getPrice())
                                       .update();
        } catch (SQLException e) {
            throw new IllegalArgumentException("This season cannot be created", e);
        }
    }

    @Override
    void modifyPrice(final PriceDescriber t) throws UnmodifiablePriceDescriberException {
        this.modify(t, "Stagione");
    }

    @Override
    void removeType(final PriceDescriber t) throws PriceDescriberRemovalException {
        this.remove(t, "Stagione");
    }

}
