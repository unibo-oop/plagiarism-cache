package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.devices.Vendor;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * Implementation of the {@link Vendor} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class VendorImpl implements Vendor {

    private final String name;

    /**
     * Instantiate a VendorImpl with the given name.
     * 
     * @param name
     *            a {@link String} with the desired name for the Vendor
     * @throws NullPointerException
     *             if the name is found to be {@code null}
     * @throws IllegalArgumentException
     *             if the name is found to be <i>empty</i>
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     */
    public VendorImpl(final String name) {
        this.name = ExtendedObjects.requireNonEmpty(Objects.requireNonNull(name, ErrorString.STRING_NULL),
                ErrorString.EMPTY_STRING);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(final Vendor o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        final VendorImpl other = (VendorImpl) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VendorImpl [name=" + name + "]";
    }

}
