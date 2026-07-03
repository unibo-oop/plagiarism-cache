package hotelmaster.db.controller;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * 
 * Manages the connectivity with the database.
 *
 */
public interface DatabaseConnection {

    /**
     * @return the connection to the database
     */
    Connection getConnection();

    /**
     * Closes the connection with the database.
     * @throws SQLException if a database access error occurs
     */
    void closeConnection();

}
