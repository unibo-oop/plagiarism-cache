package hotelmaster.db.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * Manages the interaction with the database when working with data.
 */
public interface QueryManager {

    /**
     * Insert a integer value in the {@link PreparedStatement}.
     * 
     * @param index
     *            the index of argument in the query
     * @param value
     *            the value to be inserted
     * @return the class itself
     */
    QueryManager integer(int index, int value);

    /**
     * Insert a String from a LocalDate in the {@link PreparedStatement}.
     * 
     * @param index
     *            the index
     * @param date
     *            the {@link LocalDate}
     * @return the class itself
     * @throws SQLException
     *             if a database access error occurs
     */
    QueryManager date(int index, LocalDate date);

    /**
     * Insert a price in the {@link PreparedStatement}.
     * 
     * @param index
     *            the index of argument in the query
     * @param value
     *            the value to be inserted
     * @return the class itself
     */
    QueryManager price(int index, double value);

    /**
     * Insert a description in the {@link PreparedStatement}.
     * 
     * @param index
     *            the index of argument in the query
     * @param value
     *            the value to be inserted
     * @return the class itself
     */
    QueryManager string(int index, String value);

    /**
     * Execute the INSERT or UPDATE query into the database.
     * 
     * @throws SQLException
     *             if a database error occurs
     */
    void update() throws SQLException;

    /**
     * Performs a SELECT operation when using a {@link PreparedStatement}.
     * 
     * @return a ResultSet
     */
    ResultSet selectPrepared();

    /**
     * Performs a SELECT operation when using a {@link Statement}.
     * 
     * @param query
     *            the query to be executed
     * @return a ResultSet
     */
    ResultSet selectNotPrepared(String query);

    /**
     * Prepare the query to be created by the {@link PreparedStatement}.
     * 
     * @param query
     *            the query to be executed
     * @return the class itself
     */
    QueryManager prepareQuery(String query) throws SQLException;

    /**
     * Create the Statement.
     * 
     * @return the class itself
     */
    QueryManager createQuery();

    /**
     * Performs a DELETE operation when using a {@link PreparedStatement}.
     * 
     * @throws SQLException
     *             if a database error occurs
     */
    void delete() throws SQLException;

    /**
     * Close the Statement and the PreparedStatement.
     */
    void close();

    void createTable(String query);

}