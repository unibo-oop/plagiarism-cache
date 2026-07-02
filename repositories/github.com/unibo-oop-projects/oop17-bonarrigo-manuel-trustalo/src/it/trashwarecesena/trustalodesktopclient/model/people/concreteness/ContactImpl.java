package it.trashwarecesena.trustalodesktopclient.model.people.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.people.Contact;
import it.trashwarecesena.trustalodesktopclient.model.people.ContactCategory;
import it.trashwarecesena.trustalodesktopclient.model.people.Person;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;
import it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects;

/**
 * Implementation of the {@link Contact} interface. This class is comparable by
 * the means of the value contained.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class ContactImpl implements Contact {

    private final ContactCategory category;
    private final Person owner;
    private final String value;

    /**
     * Instantiate a ContactImpl of the chosen category, with a given owner and with
     * the specified value.
     * 
     * @param category
     *            the {@link ContactCategory} which express what kind of Contact
     *            this will be.
     * @param owner
     *            a {@link Person} which will get the ownership of this ContactImpl.
     *            It is <b>mandatory</b> to understand that being the Person
     *            interface part of the
     *            {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
     *            Identifiable} family, strict rules do exists about the flow of the
     *            information.
     * @param value
     *            a {@link String} holding the resource to contact the Person
     * @throws NullPointerException
     *             if any of the parameters are found to be {@code null}
     * @throws IllegalArgumentException
     *             if the value parameter is found to be <i>empty</i>
     * @see {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
     *      Identifiable}
     * @see {@link it.trashwarecesena.trustalodesktopclient.utils.ExtendedObjects#requireNonEmpty(String)
     *      ExtendedObjects.requireNonEmpty(String, String)}
     */
    public ContactImpl(final ContactCategory category, final Person owner, final String value) {
        super();
        this.category = Objects.requireNonNull(category, "A ContactCategory" + ErrorString.CUSTOM_NULL);
        this.owner = Objects.requireNonNull(owner, "A Person" + ErrorString.CUSTOM_NULL);
        this.value = ExtendedObjects.requireNonEmpty(Objects.requireNonNull(value, ErrorString.STRING_NULL),
                ErrorString.EMPTY_STRING);
    }

    @Override
    public ContactCategory getCategory() {
        return this.category;
    }

    @Override
    public Person getOwner() {
        return this.owner;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public int compareTo(final Contact c) {
        return this.value.compareTo(c.getValue());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        final ContactImpl other = (ContactImpl) obj;
        if (category == null) {
            if (other.category != null) {
                return false;
            }
        } else if (!category.equals(other.category)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContactImpl [category=" + category + ", owner=" + owner + ", value=" + value + "]";
    }
}
