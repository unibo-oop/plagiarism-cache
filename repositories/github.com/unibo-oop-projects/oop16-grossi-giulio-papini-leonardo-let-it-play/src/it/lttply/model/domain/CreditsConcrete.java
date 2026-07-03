package it.lttply.model.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * This class provides a basic container for credits. It manages creator and
 * cast (actors).
 */
public class CreditsConcrete implements Credits {

    private final Optional<Person> creator;
    private final Set<Person> cast;

    /**
     * @param creator
     *            the media's creator
     * @param cast
     *            a {@link Collection} of {@link Person} who starred in the
     *            media
     */
    public CreditsConcrete(final Person creator, final Collection<Person> cast) {
        this.creator = Optional.ofNullable(creator);
        this.cast = new HashSet<>(cast);
    }

    @Override
    public Person getCreator() {
        if (this.creator.isPresent()) {
            if (this.creator.get().getPicture().isPresent()) {
                return new PersonConcrete(this.creator.get().getID(), this.creator.get().getName(),
                        this.creator.get().getPicture().get());
            } else {
                return new PersonConcrete(this.creator.get().getID(), this.creator.get().getName(), null);
            }
        } else {
            return null;
        }
    }

    @Override
    public Set<Person> getCast() {
        return Collections.unmodifiableSet(this.cast);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

}
