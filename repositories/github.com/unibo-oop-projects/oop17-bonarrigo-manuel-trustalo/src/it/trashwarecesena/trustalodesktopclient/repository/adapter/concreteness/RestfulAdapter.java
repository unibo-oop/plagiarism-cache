package it.trashwarecesena.trustalodesktopclient.repository.adapter.concreteness;

import java.net.MalformedURLException;
import java.net.URL;

import it.trashwarecesena.trustalodesktopclient.repository.adapter.AbstractRestfulAdapter;

/**
 * A concrete implementation of a
 * {@link it.trashwarecesena.trustalodesktopclient.repository.adapter.PersistenceAdapter
 * PersistenceAdapter} meant to be used to obtain results from a REST resource
 * holder.
 * 
 * @see {@link AbstractRestfulAdapter}
 * @author Manuel Bonarrigo
 */
public final class RestfulAdapter extends AbstractRestfulAdapter {

    /**
     * Constructs a {@link PersistenceAdapter} over the resource passed as an input.
     * 
     * @param name
     *            the name to be given to this particular PersistenceAdapter.
     * @param url
     *            the identifier of the resource to express a connection to.
     * @throws MalformedURLException
     *             if the <i>url</i> parameter does not comply to the {@link URL}
     *             specification.
     */
    public RestfulAdapter(final String name, final URL url) throws MalformedURLException {
        super(name, url);
    }

}
