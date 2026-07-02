
package it.trashwarecesena.trustalodesktopclient.repository.utils;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * An implementation of {@link PersistenceLocation} specifically built for a
 * database related client.
 * 
 * @author Manuel Bonarrigo
 */
public final class DatabaseLocation implements PersistenceLocation {

    private final String location;
    private final Optional<Integer> port;

    /**
     * Constructs a DatabaseLocation without taking in account the presence of a
     * port.
     * 
     * @param location
     *            a String representing the database location identifier.
     */
    public DatabaseLocation(final String location) {
        super();
        Objects.requireNonNull(location, ErrorString.STRING_NULL);
        this.location = location;
        this.port = Optional.empty();
    }

    /**
     * Constructs a DatabaseLocation over the specified location and port.
     * 
     * @param location
     *            the identifier of the location of the database.
     * @param port
     *            the port to which connect to talk to the database.
     */
    public DatabaseLocation(final String location, final int port) {
        super();
        Objects.requireNonNull(location, ErrorString.STRING_NULL);
        this.location = location;
        this.port = Optional.of(port);
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public Optional<Integer> getPort() {
        return this.port;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((port == null) ? 0 : port.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DatabaseLocation other = (DatabaseLocation) obj;
        if (location == null) {
            if (other.location != null) {
                return false;
            }
        } else if (!location.equals(other.location)) {
            return false;
        }
        if (port == null) {
            if (other.port != null) {
                return false;
            }
        } else if (!port.equals(other.port)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseLocation [location=" + location + ", port=" + port + "]";
    }

}
