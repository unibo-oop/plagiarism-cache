package hotelmaster.database.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.pricing.StayTypePriceDescriber;

/**
 * Utilities for stay types.
 */
public class StayTypeUtilities extends PriceDescriberUtility<StayTypePriceDescriber> {

    private final QueryManager manager;

    /**
     * 
     */
    public StayTypeUtilities() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public int getId(final StayTypePriceDescriber t) {
        return this.findPrimaryKey(t, "Pensione", "idPensione");
    }

    @Override
    public Set<StayTypePriceDescriber> getAll() {
        final String query = "SELECT descrizione, prezzo FROM Pensione";
        ResultSet rs;
        Set<StayTypePriceDescriber> stays = new HashSet<>();
        try {
            rs = manager.createQuery().selectNotPrepared(query);
            while (rs.next()) {
                stays.add(new StayTypePriceDescriber(rs.getString(1), rs.getDouble(2)));
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (stays.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(stays));
    }

}
