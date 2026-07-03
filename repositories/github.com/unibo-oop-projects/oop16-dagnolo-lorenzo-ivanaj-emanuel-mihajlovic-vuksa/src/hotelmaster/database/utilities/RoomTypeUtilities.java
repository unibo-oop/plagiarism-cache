package hotelmaster.database.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.pricing.RoomTypePriceDescriber;

/**
 * Utilities for room types.
 */
public class RoomTypeUtilities extends PriceDescriberUtility<RoomTypePriceDescriber> {

    private final QueryManagerImpl manager;
    /**
     * 
     */
    public RoomTypeUtilities() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public Set<RoomTypePriceDescriber> getAll()  { 
        String query = "SELECT descrizione,prezzo,prezzoAggiuntivo,maxPosti FROM TipoCamera";
        Set<RoomTypePriceDescriber> types = new HashSet<>();
        ResultSet rs;
        try {
            rs = manager.createQuery().selectNotPrepared(query);
            while (rs.next()) {
                types.add(new RoomTypePriceDescriber(rs.getString(1), rs.getDouble(2),
                        rs.getDouble(3), rs.getInt(4)));
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (types.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(types));
    }
    @Override
    public int getId(final RoomTypePriceDescriber t) {
        return this.findPrimaryKey(t, "TipoCamera", "idTipoCamera");
    }

}


