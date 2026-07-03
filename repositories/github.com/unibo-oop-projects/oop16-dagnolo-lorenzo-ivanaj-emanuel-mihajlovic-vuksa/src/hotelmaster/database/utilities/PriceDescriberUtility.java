package hotelmaster.database.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.pricing.PriceDescriber;

/**
 * Utilities for Price Describer.
 * 
 * @param <T>
 *            the {@link PriceDescriber}
 */
public abstract class PriceDescriberUtility<T extends PriceDescriber> extends Utility<T> {

    private final QueryManager manager = new QueryManagerImpl();

    /**
     * Search the primary key associated with this type.
     * 
     * @param t
     *            the {@link PriceDescriber} mapped with the database
     * @param tableName
     *            the table name of the {@link PriceDescriber} mapped with the
     *            database
     * @param keyName
     *            the primary key to be searched
     * @return the primary key of this {@link PriceDescriber} within the
     *         database
     */
    protected int findPrimaryKey(final T t, final String tableName, final String keyName) {
        final String query = "SELECT " + keyName + " FROM " + tableName + " WHERE descrizione = ?";
        ResultSet rs;
        int id = 0;
        try {
            rs = manager.prepareQuery(query).string(1, t.getDescription()).selectPrepared();

            if (rs.next()) {
                id = rs.getInt(1);
            } else {
                throw new IllegalArgumentException("This type doesn't exists");
            }
            rs.close();
            manager.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Get the id associated with this {@link PriceDescriber}. 
     * @param t the
     * price describer 
     * @return the primary key
     */
    abstract int getId(T t);

}
