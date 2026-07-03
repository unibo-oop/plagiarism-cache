package hotelmaster.database.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.pricing.RoomExtraPriceDescriber;

/**
 * Utilities for room extras.
 */
public class RoomExtraUtilities extends PriceDescriberUtility<RoomExtraPriceDescriber> {

    private final QueryManager manager;

    /**
     * 
     */
    public RoomExtraUtilities() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public Set<RoomExtraPriceDescriber> getAll() {
        String query = "SELECT descrizione,prezzo FROM ExtraCamera";
        Set<RoomExtraPriceDescriber> extras = new HashSet<>();
        ResultSet rs;
        try {
            rs = manager.createQuery().selectNotPrepared(query);
            while (rs.next()) {
                extras.add(new RoomExtraPriceDescriber(rs.getString(1), rs.getDouble(2)));
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (extras.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(extras));
    }

    @Override
    public int getId(final RoomExtraPriceDescriber t) {
        return this.findPrimaryKey(t, "ExtraCamera", "idExtra");
    }

}
