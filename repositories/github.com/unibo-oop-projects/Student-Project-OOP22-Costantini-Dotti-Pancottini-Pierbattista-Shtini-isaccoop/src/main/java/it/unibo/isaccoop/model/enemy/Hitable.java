package it.unibo.isaccoop.model.enemy;

/**
 * Generic Hitable subject.
 *
 * @param <E> entity type to be passed to onHit method
 * */
public interface Hitable<E> {

    /**
     * Method to be called when the culpable is hit.
     *
     * @param entity entity object in order to handle collision with another entity
     * */
    void onHit(E entity);

}
