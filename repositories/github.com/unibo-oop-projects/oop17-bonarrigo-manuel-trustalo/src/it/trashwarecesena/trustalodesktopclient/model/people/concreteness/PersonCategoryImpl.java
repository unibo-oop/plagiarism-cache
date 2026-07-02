package it.trashwarecesena.trustalodesktopclient.model.people.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.people.PersonCategory;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * A simple implementation of the {@link PersonCategory} interface. It is backed
 * up by a String.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class PersonCategoryImpl implements PersonCategory {

    private final String name;

    /**
     * Constructs a JuridicalPersonCategoryImpl with the given name.
     * 
     * @param name
     *            A {@link String} with the name to be given to the
     *            JuridicalPersonCategoryImpl.
     * @throws NullPointerException
     *             if the name is found to be {@code null}
     * @throws IllegalArgumentException
     *             if the name is found to be <i>empty</i>
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     */
    public PersonCategoryImpl(final String name) {
        super();
        this.name = ExtendedObjects.requireNonEmpty(Objects.requireNonNull(name, ErrorString.STRING_NULL),
                ErrorString.EMPTY_STRING);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(final PersonCategory o) {
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
        final PersonCategoryImpl other = (PersonCategoryImpl) obj;
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
        return "PersonCategoryImpl [name=" + name + "]";
    }

}
