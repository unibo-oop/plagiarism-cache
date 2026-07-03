package it.lttply.model.domain.managers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface provides a structure to build a generic database manager.
 * Methods which permit to select informations from the database are not
 * provided, this is due to the {@link ResultSet} unsafety.
 */
public interface SQLiteBasic {

    /**
     * @return the {@link Connection} for the database.
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    Connection connect() throws SQLException;

    /**
     * This method initializes the whole environment: if the database specified
     * in the constructor already exists and is valid for this application, the
     * entire procedure is simply ignored; if instead there is no database or it
     * is not valid, this method will remove every invalid setting and will
     * re-build the database (with the same name) with the default tables.
     * 
     */
    void initialize();

    /**
     * This method provides an interface to make inserting operations.
     * <i>Suggestion: use it for INSERT, DELETE, EDIT, CREATE and other similar
     * operations.</i>
     * 
     * @param query
     *            the query to be launched
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     */
    void setInsertRemove(String query) throws SQLException;

    /**
     * @return the physical location of the database on secondary memory.
     */
    String getDBPath();

}