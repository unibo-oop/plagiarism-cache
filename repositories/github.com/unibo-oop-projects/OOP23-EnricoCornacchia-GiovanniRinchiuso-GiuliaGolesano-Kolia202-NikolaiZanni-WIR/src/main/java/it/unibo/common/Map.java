package it.unibo.common;

/**
 * Class for a Map.
 * @param <E> the entity
 * @param <P> the position
 */
public class Map<E, P> {
    private final E entity;
    private final P pos;
    /**
     * Constructor.
     * @param entity the entity
     * @param pos the position
     */
    public Map(final E entity, final P pos) {
        this.entity = entity;
        this.pos = pos;
    }
    /**
     * Getter of the entity.
     * 
     * @return the entity
     */
    public final E getEntity() {
        return this.entity;
    }
    /**
     * Getter of the position.
     * 
     * @return the position
     */
    public final P getPos() {
        return this.pos;
    }
}
