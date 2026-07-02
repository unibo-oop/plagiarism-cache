package it.trashwarecesena.trustalodesktopclient.repository.adapter;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides an abstraction over the methodology used to connect over a database.
 * 
 * @author Manuel Bonarrigo
 *
 */
public interface DatabaseConnectionStrategy {
    /**
     * Open a {@link Connection} to a database.
     * 
     * @return the Connection reference to be manipulated.
     * @throws SQLException
     *             if the connection is not established for any reason.
     */
    Connection createConnection() throws SQLException;

}
