package it.trashwarecesena.trustalodesktopclient.model.people.concreteness;

import java.util.Objects;

import it.trashwarecesena.trustalodesktopclient.model.people.PhysicalPerson;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorker;
import it.trashwarecesena.trustalodesktopclient.model.people.TrashwareWorkerCategory;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A concrete implementation of the {@link TrashwareWorker} interface..
 * <p>
 * 
 * Notice that equality between two TrashwareWorker is enforced between
 * comparison between internal PhysicalPerson instances, and that PhysicalPerson
 * is an {@link it.trashwarecesena.trustalodesktopclient.model.Identifiable
 * Identifiable} entity.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class TrashwareWorkerImpl implements TrashwareWorker {

    private final PhysicalPerson person;
    private final TrashwareWorkerCategory category;
    private final boolean enrolled;

    /**
     * Constructs a {@link TrashwareWorker} over the specified person, performing
     * the job expressed by the category and expressing his or her actual
     * enrollment.
     * 
     * @param person
     *            a {@link PhysicalPerson} which expresses the personal known
     *            informations of this instance of a TrashwareWorker
     * @param category
     *            the {@link TrashwareWorkerCategory} this worker is enrolled for.
     * @param enrolled
     *            a boolean expressing if this worker is still working for the
     *            Trashware project
     * @throws NullPointerException
     *             if any parameter is found to be {@code null}
     */
    public TrashwareWorkerImpl(final PhysicalPerson person, final TrashwareWorkerCategory category,
            final boolean enrolled) {
        this.person = Objects.requireNonNull(person, "A person" + ErrorString.CUSTOM_NULL);
        this.category = Objects.requireNonNull(category, "A category" + ErrorString.CUSTOM_NULL);
        this.enrolled = enrolled;
    }

    @Override
    public PhysicalPerson getPerson() {
        return this.person;
    }

    @Override
    public TrashwareWorkerCategory getCategory() {
        return this.category;
    }

    @Override
    public boolean isCurrentlyWorking() {
        return this.enrolled;
    }

    @Override
    public int compareTo(final TrashwareWorker o) {
        return this.person.compareTo(o.getPerson());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((person == null) ? 0 : person.hashCode());
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
        final TrashwareWorkerImpl other = (TrashwareWorkerImpl) obj;
        if (person == null) {
            if (other.person != null) {
                return false;
            }
        } else if (!person.equals(other.person)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TrashwareWorkerImpl [person=" + person + ", category=" + category + ", enrolled=" + enrolled + "]";
    }

}
