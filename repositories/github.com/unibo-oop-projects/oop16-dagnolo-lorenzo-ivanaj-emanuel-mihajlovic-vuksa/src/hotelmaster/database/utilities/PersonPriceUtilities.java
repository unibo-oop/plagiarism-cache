package hotelmaster.database.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.pricing.PersonPriceDescriber;

/**
 * Utilities for person prices.
 */
public class PersonPriceUtilities extends PriceDescriberUtility<PersonPriceDescriber> {

    private final QueryManager manager;

    /**
     * 
     */
    public PersonPriceUtilities() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public Set<PersonPriceDescriber> getAll() {
        final String query = "SELECT descrizione, prezzo FROM TipoPersona";
        ResultSet rs;
        Set<PersonPriceDescriber> prices = new HashSet<>();
        try {
            rs = manager.createQuery().selectNotPrepared(query);

            while (rs.next()) {
                prices.add(new PersonPriceDescriber(rs.getString(1), rs.getDouble(2)));
            }
            rs.close();
            manager.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (prices.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(prices));
    }

    @Override
    public int getId(final PersonPriceDescriber t) {
        return this.findPrimaryKey(t, "TipoPersona", "idTipoPersona");
    }

}
