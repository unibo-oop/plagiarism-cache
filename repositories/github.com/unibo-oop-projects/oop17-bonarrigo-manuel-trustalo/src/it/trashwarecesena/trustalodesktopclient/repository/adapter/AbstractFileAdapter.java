package it.trashwarecesena.trustalodesktopclient.repository.adapter;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConnectionResourceImpl;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConnectionResource;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * Abstract extension of {@link AbstractPersistenceAdapter} over a generic
 * file-based persistent storage.
 * <p>
 * Even if the {@link PersistenceAdapter} interface is already fully respected,
 * some key elements are still missing, and they can only be provided by an
 * implementor class which knows over what kind of file the mapper is built on,
 * and what strategy is needed to retrieve information from it.
 * 
 * @author Manuel Bonarrigo
 *
 */
public abstract class AbstractFileAdapter extends AbstractPersistenceAdapter {

    private Optional<File> file;
    private final FileConnectionStrategy strategy;

    /**
     * Constructs a FilePersistenceAdapter which will connect over the provided
     * {@link FileConnectionStrategy}.
     * 
     * @param name
     *            the name to give to the PersistenceAdapter
     * @param strategy
     *            a {@link FileConnectionStrategy} that will rule the connection to
     *            the file-based persistent storage.
     * @throws IllegalArgumentException
     *             if the strategy or the mapper being given as parameters are null
     * @throws IOException
     *             if the strategy is being provided a wrong filepath to create the
     *             {@link File} required
     */
    public AbstractFileAdapter(final String name, final FileConnectionStrategy strategy)
            throws IllegalArgumentException, IOException {
        super(name);
        Objects.requireNonNull(strategy, "A strategy" + ErrorString.CUSTOM_NULL);
        this.strategy = strategy;
        connect();
    }

    @Override
    public final ConnectionResource<File> getConnection() {
        return new ConnectionResourceImpl<>(
                file.orElseThrow(() -> new IllegalStateException("No file available to connect to")));
    }

    private void connect() throws IOException {
        file = Optional.ofNullable(strategy.createConnection());
    }

}
