package it.trashwarecesena.trustalodesktopclient.repository.adapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConnectionResourceImpl;
import it.trashwarecesena.trustalodesktopclient.repository.fragmented.ConnectionResource;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * Abstract extension of {@link AbstractPersistenceAdapter} over a generic REST
 * database, implying it is needed a web request to obtain a result. To achieve
 * approximately the same functionality with offline resources see
 * {@link AbstractFileAdapter}
 * <p>
 * The {@link PersistenceAdapter} interface is already fully respected.
 * 
 * @author Manuel Bonarrigo
 *
 */
public abstract class AbstractRestfulAdapter extends AbstractPersistenceAdapter {

    private final URL url;

    /**
     * Constructs a {@link PersistenceAdapter} over the resource passed as an input.
     * 
     * @param name
     *            the name to be given to this particular PersistenceAdapter.
     * @param url
     *            the identifier of the database to express a connection to.
     * @throws MalformedURLException
     *             if the <i>url</i> parameter does not comply to the {@link URL}
     *             specification.
     * @throws IllegalArgumentException
     *             if the <i>url</i> parameter is meant to identify a resource
     *             placed offline.
     */
    public AbstractRestfulAdapter(final String name, final URL url)
            throws MalformedURLException, IllegalArgumentException {
        super(name);
        Objects.requireNonNull(url, "The url" + ErrorString.CUSTOM_NULL);
        if (url.getProtocol().equals("file")) {
            throw new IllegalArgumentException("The URL must be valid for a web based interaction");
        }
        this.url = url;
    }

    @Override
    public final ConnectionResource<?> getConnection() {
        return new ConnectionResourceImpl<URL>(url);
    }

}
