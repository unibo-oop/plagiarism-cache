package it.trashwarecesena.trustalodesktopclient.repository.fragmented;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * The concrete implementation of {@link ConnectionResource} providing the
 * heterogeneity behavior requested by the interface.
 * 
 * @author Manuel Bonarrigo
 * 
 * @param <X>
 *            the actual type representing the technology used to pursue the
 *            connection
 */
public final class ConnectionResourceImpl<X> implements ConnectionResource<X> {

    private final X connection;

    /**
     * Constructs a ConcreteFragmentedConnection based on the connection resource
     * passed in input.
     * 
     * @param connection
     *            a connection resource
     * @see {@link ConnectionResource}, {@link java.io.File File}
     */
    public ConnectionResourceImpl(final X connection) {
        super();
        this.connection = Objects.requireNonNull(connection,
                "The Object representing a connection" + ErrorString.NO_NULL);
    }

    @Override
    public X getConnectionResource() {
        return this.connection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<X> getTechnologyType() {
        return (Class<X>) connection.getClass();
    }

}
