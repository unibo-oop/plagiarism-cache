package it.trashwarecesena.trustalodesktopclient.repository.utils;

import java.util.Optional;

/**
 * Abstraction over the connection made to a remote resource.
 * 
 * @author Manuel Bonarrigo
 */
public interface PersistenceLocation {

    /**
     * Retrieve a String depicting the resource location.
     * 
     * @return a String which identifies the resource.
     */
    String getLocation();

    /**
     * Gets the port to contact the remote service onto, if any was specified.
     * 
     * @return an {@link Optional} containing an {@link Integer} representing the
     *         port of a remote service.
     */
    Optional<Integer> getPort();

}
