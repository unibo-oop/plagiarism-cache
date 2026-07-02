package it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.repository.adapter.DatabaseConnectionStrategy;
import it.trashwarecesena.trustalodesktopclient.repository.security.UserPassLogin;
import it.trashwarecesena.trustalodesktopclient.repository.utils.PersistenceLocation;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A concrete implementation for a {@link DatabaseConnectionStrategy}.
 * 
 * @author Manuel Bonarrigo
 *
 */

public class ConcreteDatabaseConnectionStrategy implements DatabaseConnectionStrategy {

    private final PersistenceLocation location;
    private final UserPassLogin login;

    /**
     * Instantiate a ConcreteDatabaseConnectionStrategy to a location, with
     * credentials to be provided.
     * 
     * @param location
     *            the {@link PersistenceLocation} the strategy connects to.
     * @param login
     *            the credentials used by the strategy to gain access to the
     *            specified resource.
     * @see {@link UserPassLogin}
     */
    public ConcreteDatabaseConnectionStrategy(final PersistenceLocation location, final UserPassLogin login) {
        super();
        Objects.requireNonNull(location, "The PersistenceLocation" + ErrorString.CUSTOM_NULL);
        Objects.requireNonNull(login, "The UserPassLogin" + ErrorString.CUSTOM_NULL);

        this.location = location;
        this.login = login;
    }

    @Override
    public final Connection createConnection() throws SQLException {
        return DriverManager.getConnection(location.getLocation(), login.getUser().getUsername(),
                login.getPassword().getPassword());
    }

}
