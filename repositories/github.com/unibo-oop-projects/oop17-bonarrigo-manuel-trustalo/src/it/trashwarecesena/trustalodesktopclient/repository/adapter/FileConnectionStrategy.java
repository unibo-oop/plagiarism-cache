package it.trashwarecesena.trustalodesktopclient.repository.adapter;

import java.io.File;
import java.io.IOException;

/**
 * Provides an abstraction over the methodology used to connect over a
 * file-based persistence storage.
 * 
 * @author Manuel Bonarrigo
 *
 */
public interface FileConnectionStrategy {

    /**
     * Open a 'connection' over a {@link File}.
     * 
     * @return the File reference to be manipulated.
     * @throws IOException
     *             if there is any disfunctionality in the creation or management of
     *             the resource
     */
    File createConnection() throws IOException;
}
