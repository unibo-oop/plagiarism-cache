package it.trashwarecesena.trustalodesktopclient.repository.test.adapter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import it.trashwarecesena.trustalodesktopclient.repository.adapter.DatabaseConnectionStrategy;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness.ConcreteDatabaseConnectionStrategy;
import it.trashwarecesena.trustalodesktopclient.repository.security.SimpleUserPassLogin;
import it.trashwarecesena.trustalodesktopclient.repository.security.StringPassword;
import it.trashwarecesena.trustalodesktopclient.repository.security.StringUser;
import it.trashwarecesena.trustalodesktopclient.repository.security.UserPassLogin;
import it.trashwarecesena.trustalodesktopclient.repository.utils.DatabaseLocation;

/**
 * A set of test checking the {@link ConcreteDatabaseConnectionStrategy}
 * behaviour.
 * <p>
 * Testing revolves around the ability of opening a connection to a database
 * granted the correctness of the login values, and the re-throwing of an
 * {@link SQLException} in whatsoever case this is not being possible. The latter
 * case is explored by the means of a correct login request to a not operating
 * database and a bad login request over an operating database.
 * 
 * @author Manuel Bonarrigo
 */
public class ConcreteDatabaseConnectionStrategyTest {

    private static final String VALID_LOCATION = "jdbc:mysql://localhost:3306/Trustalo";
    private static final String VALID_USER = "root";
    private static final String VALID_PASSWORD = "";

    private static final String ERRONEOUS_LOCATION = "jdbc:mysql://inexistentPath:3306/inexistent_database";
    private static final String ERRONEOUS_USER = "wrong";
    private static final String ERRONEOUS_PASSWORD = "pass";

    /**
     * A test performed over a set of correct parameters.
     */
    @Test
    public void validConnectionTest() {
        final DatabaseLocation location = new DatabaseLocation(VALID_LOCATION);
        final UserPassLogin access = new SimpleUserPassLogin(new StringUser(VALID_USER),
                new StringPassword(VALID_PASSWORD));
        final DatabaseConnectionStrategy strategy = new ConcreteDatabaseConnectionStrategy(location, access);
        try (Connection connection = strategy.createConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * A test performed with a good login and a not working database.
     */
    @Test
    public void validLoginInvalidDatabaseConnectionTest() {
        final DatabaseLocation location = new DatabaseLocation(ERRONEOUS_LOCATION);
        final UserPassLogin access = new SimpleUserPassLogin(new StringUser(ERRONEOUS_USER),
                new StringPassword(ERRONEOUS_PASSWORD));
        final DatabaseConnectionStrategy strategy = new ConcreteDatabaseConnectionStrategy(location, access);
        assertThrows(SQLException.class, () -> {
            strategy.createConnection();
        });
    }

    /**
     * A test performed with a bad login and a working database.
     */
    @Test
    public void invalidLoginValidDatabaseConnectionTest() {
        final DatabaseLocation location = new DatabaseLocation(VALID_LOCATION);
        final UserPassLogin access = new SimpleUserPassLogin(new StringUser(ERRONEOUS_USER),
                new StringPassword(ERRONEOUS_PASSWORD));
        final DatabaseConnectionStrategy strategy = new ConcreteDatabaseConnectionStrategy(location, access);

        assertThrows(SQLException.class, () -> {
            strategy.createConnection();
        });
    }

}
