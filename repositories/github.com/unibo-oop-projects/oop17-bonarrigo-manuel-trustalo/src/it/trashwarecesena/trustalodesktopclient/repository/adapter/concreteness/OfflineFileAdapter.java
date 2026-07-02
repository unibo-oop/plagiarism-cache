package it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness;

import java.io.IOException;

import it.trashwarecesena.trustalodesktopclient.repository.adapter.AbstractFileAdapter;
import it.trashwarecesena.trustalodesktopclient.repository.adapter.FileConnectionStrategy;

/**
 * Concrete implementation of a
 * {@link it.trashwarecesena.trustalodesktopclient.repository.adapter.PersistenceAdapter
 * PersistenceAdapter} over a generic file-based persistent storage.
 * 
 * @author Manuel Bonarrigo
 */
public class OfflineFileAdapter extends AbstractFileAdapter {

    /**
     * Constructs an OfflinePersistenceAdapter with the given name, and using the
     * given "connection" strategy.
     * 
     * @param name
     *            the name to be given to this instance of the adapter
     * @param strategy
     *            the strategy to open a {@link File} with.
     * @throws IllegalArgumentException
     *             if the strategy or the name are null, such an exception is
     *             raised.
     * @throws IOException
     *             if the strategy is being provided a wrong filepath to create the
     *             {@link File} required
     */
    public OfflineFileAdapter(final String name, final FileConnectionStrategy strategy)
            throws IllegalArgumentException, IOException {
        super(name, strategy);
    }
}
