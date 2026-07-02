package it.trashwarecesena.trustalodesktopclient.model.devices.concreteness;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.devices.DeviceWorkProgress;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * Implementation of the {@link DeviceWorkProgress} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class DeviceWorkProgressImpl implements DeviceWorkProgress {

    private final String name;
    private final Optional<String> description;

    /**
     * Instantiate a DeviceWorkProgressImpl with the given name.
     * 
     * @param name
     *            a {@link String} with the desired name.
     * @throws NullPointerException
     *             if the name is found to be {@code null}
     * @throws IllegalArgumentException
     *             if the name is found to be <i>empty</i>
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     */
    public DeviceWorkProgressImpl(final String name) {
        this(name, null);
    }

    /**
     * Instantiate a DeviceWorkProgressImpl with the given name and description.
     * 
     * @param name
     *            a {@link String} with the desired name.
     * @param description
     *            a {@link String} with the description of the state.
     * @throws NullPointerException
     *             if the name is found to be {@code null}
     * @throws IllegalArgumentException
     *             if the name or description is found to be <i>empty</i>
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     */
    public DeviceWorkProgressImpl(final String name, final String description) {
        super();
        this.name = ExtendedObjects.requireNonEmpty(Objects.requireNonNull(name, ErrorString.STRING_NULL),
                ErrorString.EMPTY_STRING);
        this.description = Optional.ofNullable(ExtendedObjects.requireNonEmpty(description, ErrorString.EMPTY_STRING));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Optional<String> getDescription() {
        return this.description;
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
        final DeviceWorkProgressImpl other = (DeviceWorkProgressImpl) obj;
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
        return "DeviceWorkProgressImpl [name=" + name + ", description=" + description.orElse(null) + "]";
    }
}
