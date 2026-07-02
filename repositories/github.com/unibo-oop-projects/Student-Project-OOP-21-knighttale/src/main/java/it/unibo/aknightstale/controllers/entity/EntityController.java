package it.unibo.aknightstale.controllers.entity;

import it.unibo.aknightstale.models.entity.Entity;
import it.unibo.aknightstale.views.entity.EntityView;

/**
 * The interface Entity controller.
 *
 * @param <M> the type parameter
 * @param <V> the type parameter
 */
public interface EntityController<M extends Entity, V extends EntityView> {
    /**
     * Gets the model of this entity.
     * 
     * @return the model.
     */
    M getModel();

    /**
     * Gets the view of this entity.
     * 
     * @return the view.
     */
    V getView();


    /**
     * Adapt position to screen size.
     *
     * @param traslX the trasl x
     * @param traslY the trasl y
     */
    void adaptPositionToScreenSize(double traslX, double traslY);
}
