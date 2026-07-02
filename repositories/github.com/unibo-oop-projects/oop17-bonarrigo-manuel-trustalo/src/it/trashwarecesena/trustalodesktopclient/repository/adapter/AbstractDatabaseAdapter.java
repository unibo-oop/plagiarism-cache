package it.trashwarecesena.trustalodesktopclient.repository.adapter;

import java.sql.Connection;
import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConnectionResource;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConnectionResourceImpl;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * Abstract extension of {@link AbstractPersistenceAdapter} over a generic
 * database.
 * <p>
 * Even if the {@link PersistenceAdapter} interface is already fully respected,
 * some key elements are still missing, and they can only be provided by an
 * implementor class which knows over what kind of database the mapper is built
 * on, and what strategy is needed to connect to it.
 * 
 * @author Manuel Bonarrigo
 */
public abstract class AbstractDatabaseAdapter extends AbstractPersistenceAdapter {

    private Optional<Connection> connection;
    private final DatabaseConnectionStrategy strategy;

    /**
     * Constructs a DatabasePersistenceAdapter which will connect over the provided
     * {@link DatabaseConnectionStrategy}.
     * 
     * @param name
     *            the name to give to the PersistenceAdapter
     * @param strategy
     *            a {@link DatabaseConnectionStrategy} that will rule the connection
     *            to the database
     * @throws IllegalArgumentException
     *             if the strategy being given as parameter is null
     */
    public AbstractDatabaseAdapter(final String name, final DatabaseConnectionStrategy strategy)
            throws IllegalArgumentException {
        super(name);
        Objects.requireNonNull(strategy, "A strategy" + ErrorString.CUSTOM_NULL);
        this.strategy = strategy;
        connect();
    }

    @Override
    public final ConnectionResource<Connection> getConnection() {
        return new ConnectionResourceImpl<>(
                connection.orElseThrow(() -> new IllegalStateException("No connection available")));
    }

    private void connect() {
        try {
            this.connection = Optional.ofNullable(strategy.createConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
