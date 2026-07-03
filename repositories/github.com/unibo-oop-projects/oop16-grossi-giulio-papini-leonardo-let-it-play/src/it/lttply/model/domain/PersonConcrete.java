package it.lttply.model.domain;

import java.util.Optional;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * This class provides a basic container of informations of a single person. It
 * manages name and profile picture.
 */
public class PersonConcrete implements Person {

    private final String id;
    private final String name;
    private final Optional<Picture> picture;

    /**
     * Constructs a new {@link Person}.
     * 
     * @param id
     *            the person's id
     * @param name
     *            the person's name
     * @param picture
     *            the optional person's profile image
     */
    public PersonConcrete(final String id, final String name, final Picture picture) {
        this.id = id;
        this.name = name;
        this.picture = Optional.ofNullable(picture);
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Optional<Picture> getPicture() {
        return this.picture;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());
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
        final PersonConcrete other = (PersonConcrete) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (picture == null) {
            if (other.picture != null) {
                return false;
            }
        } else if (!picture.equals(other.picture)) {
            return false;
        }
        return true;
    }

}
