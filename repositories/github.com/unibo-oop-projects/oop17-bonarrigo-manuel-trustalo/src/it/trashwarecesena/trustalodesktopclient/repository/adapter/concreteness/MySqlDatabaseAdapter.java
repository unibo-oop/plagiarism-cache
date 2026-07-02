package it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness;

import it.trashwarecesena.trustalodesktopclient.repository.adapter.AbstractDatabaseAdapter;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.DatabaseConnectionStrategy;

/**
 * A MySQL related implementation of a
 * {@link it.trashwarecesena.trustalodesktopclient.repository.adapter.PersistenceAdapter
 * PersistenceAdapter}.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class MySqlDatabaseAdapter extends AbstractDatabaseAdapter {
    /**
     * Constructs a MySQLDatabaseAdapter over the given
     * {@link DatabaseConnectionStrategy}.
     * <p>
     * Note that the class isn't able to tell if the objects it's being built on are
     * MySql related, so the client must be careful to not pass the wrong
     * parameters.
     * 
     * @param name
     *            the arbitrary name to identify the adapter with.
     * @param strategy
     *            the strategy chosen to connect to the database
     * @throws IllegalArgumentException
     *             if the strategy is null, the superclass constructor will cause
     *             this exception to be raised
     */
    public MySqlDatabaseAdapter(final String name, final DatabaseConnectionStrategy strategy)
            throws IllegalArgumentException {
        super(name, strategy);
    }

}
