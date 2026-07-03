package hotelmaster.database.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.pricing.SeasonPriceDescriber;
import hotelmaster.utility.time.FixedPeriod;

/**
 * Utilities for season prices.
 */
public class SeasonUtilities extends PriceDescriberUtility<SeasonPriceDescriber> {

    private final QueryManager manager;

    /**
     * 
     */
    public SeasonUtilities() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    int getId(final SeasonPriceDescriber t) {
        return this.findPrimaryKey(t, "Stagione", "idStagione");
    }

    @Override
    public Set<SeasonPriceDescriber> getAll() {
        final String query = "SELECT descrizione, dataInizio, dataFine, prezzo FROM Stagione";
        Set<SeasonPriceDescriber> seasons = new HashSet<>();
        try {
            ResultSet rs = manager.createQuery().selectNotPrepared(query);
            while (rs.next()) {
                seasons.add(new SeasonPriceDescriber(rs.getString(1), rs.getDouble(4),
                        FixedPeriod.of(Utility.toLocalDate(rs.getString(2)), Utility.toLocalDate(rs.getString(3)))));

            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (seasons.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(seasons));
    }

}
