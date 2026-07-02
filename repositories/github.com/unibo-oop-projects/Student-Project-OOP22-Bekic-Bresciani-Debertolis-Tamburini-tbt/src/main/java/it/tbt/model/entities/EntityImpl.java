package it.tbt.model.entities;

import java.util.Objects;

/**
 * Generic entity implementation.
 */
public abstract class EntityImpl implements Entity {
    private final String name;

    /**
     * Default constructor.
     * @param name
     */
    protected EntityImpl(final String name) {
        this.name = name;
    }

    /**
     * Return the entity name.
     * @return the entity name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Entity entity) {
        return this.getName().compareTo(entity.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EntityImpl entity = (EntityImpl) o;
        return Objects.equals(name, entity.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
