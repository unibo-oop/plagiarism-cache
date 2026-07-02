package it.trashwarecesena.trustalodesktopclient.model.requests.concreteness;

import java.util.Objects;
import java.util.Optional;

import it.trashwarecesena.trustalodesktopclient.model.requests.RequestProgress;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * An implementation of the {@link RequestProgress} interface.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class RequestProgressImpl implements RequestProgress {

    private final String name;
    private final Optional<String> description;

    /**
     * Constructs a RequestProgress over the given name.
     * 
     * @param name
     *            the name to give to the progress.
     */
    public RequestProgressImpl(final String name) {
        this(name, null);
    }

    /**
     * Constructs a RequestProgress over the given informations.
     * 
     * @param name
     *            the name to give to the progress.
     * @param description
     *            a description of the state of progress
     */
    public RequestProgressImpl(final String name, final String description) {
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
        final RequestProgressImpl other = (RequestProgressImpl) obj;
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
        return "RequestProgressImpl [name=" + name + ", description=" + description.orElse(null) + "]";
    }

}
