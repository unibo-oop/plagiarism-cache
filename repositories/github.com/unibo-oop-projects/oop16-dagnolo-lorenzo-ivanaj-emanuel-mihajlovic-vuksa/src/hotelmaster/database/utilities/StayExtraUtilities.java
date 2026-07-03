package hotelmaster.database.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.pricing.StayExtraPriceDescriber;

/**
 * Utilities for stay extras.
 */
public class StayExtraUtilities extends PriceDescriberUtility<StayExtraPriceDescriber> {

    private final QueryManager manager;

    /**
     * 
     */
    public StayExtraUtilities() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public Set<StayExtraPriceDescriber> getAll()  {
        final String query = "SELECT descrizione,prezzo,aPersona FROM Supplemento";
        Set<StayExtraPriceDescriber> extras = new HashSet<>();
        ResultSet rs;
        try {
            rs = manager.createQuery().selectNotPrepared(query);
            while (rs.next()) {
                extras.add(
                        new StayExtraPriceDescriber(rs.getString(1), rs.getDouble(2), rs.getBoolean(3)));
            }
            rs.close();
            manager.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (extras.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(extras));
    }

    @Override
    public int getId(final StayExtraPriceDescriber extra)  {
        return this.findPrimaryKey(extra, "Supplemento", "idSupplemento");
    }

}
