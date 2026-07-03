package hotelmaster.database.admin;

import java.sql.SQLException;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.exceptions.PriceDescriberRemovalException;
import hotelmaster.exceptions.UnmodifiablePriceDescriberException;
import hotelmaster.pricing.PriceDescriber;

/**
 * Basic class for utilities.
 * 
 */
public abstract class BasicPriceDescriberOperation {

    private final QueryManager manager;

    /**
     * 
     */
    public BasicPriceDescriberOperation() {
        this.manager = new QueryManagerImpl();
    }

    /**
     * Create a new {@link PriceDescriber}.
     * 
     * @param t
     *            the price describer to be created
     */
    abstract void create(PriceDescriber t);

    /**
     * Remove a certain price describer from the database.
     * 
     * @param type
     *            the price describer object mapped with the database
     * @param tableName
     *            the table name mapped with this price describer
     * @throws PriceDescriberRemovalException
     *             if this price describer cannot be removed
     */
    protected void remove(final PriceDescriber type, final String tableName) throws PriceDescriberRemovalException {
        final String query = "DELETE FROM " + tableName + " WHERE descrizione = ?";
        try {
            manager.prepareQuery(query).string(1, type.getDescription()).delete();
        } catch (SQLException e) {
            throw new PriceDescriberRemovalException("The person price cannot be removed", e);
        }
    }

    /**
     * Modify a price describer.
     * 
     * @param type
     *            the price describer
     * @param tableName
     *            the name of the table mapped with this price describer
     * @throws UnmodifiablePriceDescriberException if the price cannot be removed
     */
    protected void modify(final PriceDescriber type, final String tableName)
            throws UnmodifiablePriceDescriberException {
        final String query = "UPDATE " + tableName + " SET prezzo = ? WHERE descrizione = ?";
        try {
            manager.prepareQuery(query).price(1, type.getPrice()).string(2, type.getDescription()).update();
        } catch (SQLException e) {
            throw new UnmodifiablePriceDescriberException("The price cannot be modified", e);
        }
    }

    /**
     * Modify a price describer.
     * 
     * @param t
     *            the price describer
     */
    abstract void modifyPrice(PriceDescriber t) throws UnmodifiablePriceDescriberException;

    /**
     * Remove a certain price describer
     * 
     * @param t
     *            the price describer to be removed
     * @throws PriceDescriberRemovalException
     *             if this type cannot be removed
     */
    abstract void removeType(PriceDescriber t) throws PriceDescriberRemovalException;
}
